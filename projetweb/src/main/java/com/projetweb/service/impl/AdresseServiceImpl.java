package com.projetweb.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import static com.projetweb.helper.ConstantesHelper.HEURE_FORMAT_TAN;
import static com.projetweb.helper.ConstantesHelper.MS_IN_MINUTE;
import static com.projetweb.helper.ConstantesHelper.S_IN_MINUTE;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.BusVsVoiture;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.DistanceGoogleResponse;
import com.projetweb.bean.ItineraireTanResponse;
import com.projetweb.bean.Stop;
import com.projetweb.bean.TravelMode;
import com.projetweb.bean.Waypoint;
import com.projetweb.bean.generated.tan.Etape;
import com.projetweb.dao.AdresseDAO;
import com.projetweb.dao.StopDAO;
import com.projetweb.dao.TrajetBusDAO;
import com.projetweb.helper.TarifsParkingsHelper;
import com.projetweb.service.AdresseService;
import com.projetweb.service.GoogleWebService;
import com.projetweb.service.TanWebService;

public class AdresseServiceImpl implements AdresseService{
	
	/**
	 * Service d'appel TAN
	 */
	@Autowired
	private TanWebService tanWebService;
	
	/**
	 * Service d'appel API Googke
	 */
	@Autowired
	private GoogleWebService googleWebService;
	
	/**
	 * Gestionnaire Adresses en base
	 */
	@Autowired
	private AdresseDAO adresseDAO;
	
	/**
	 * Gestionnaire Stops en base
	 */
	@Autowired
	private StopDAO stopDAO;
	
	/**
	 * Gestionnaire Trajets bus en base
	 */
	@Autowired
	private TrajetBusDAO trajetBusDAO;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(AdresseServiceImpl.class.getName());


	
	@Override
	public List<Adresse> findAdressesWithCoord(String adresse) {
		List<Adresse> listeAdresse = tanWebService.findAdresses(adresse);
		Adresse tmpAdresse;
		LOG.info("AdresseServiceImpl::findAdressesWithCoord récupération des coordonnées");
		for(Adresse uneAdresse : listeAdresse){
			//Si l'adresse n'existe pas en base de donnée, alors on va chercher les coordonnées via google, sinon on les récupère via l'adresse existante
			if((tmpAdresse = adresseDAO.getAdresseById(uneAdresse.getId())) == null){
				Coordonnee coord = googleWebService.findCoordonnees(uneAdresse.toString());
				uneAdresse.setCoord(coord);
				adresseDAO.store(uneAdresse);
			} else {
				uneAdresse.setCoord(tmpAdresse.getCoord());
			}
		}
		return listeAdresse;
	}

	@Override
	public BusVsVoiture findItineraire(String idAdresseDepart,String idAdresseArrivee, Date dateDepart, Date dateRetour, String typeVoiture, String carburant, boolean abonnementTan) {
		//On récupère les deux adresses en base
		LOG.info("AdresseServiceImpl::findItineraire récupération des adresses en base");
		Adresse adresseDepart = adresseDAO.getAdresseById(idAdresseDepart);
		Adresse adresseArrivee = adresseDAO.getAdresseById(idAdresseArrivee);
		
		
		//Récupération itinéraire départ tan
		ItineraireTanResponse[] itineraireDepartTanResponse = tanWebService.itineraire(adresseDepart, adresseArrivee, dateDepart);
		//Récupération itinéraire retour tan
		ItineraireTanResponse[] itineraireRetourTanResponse = tanWebService.itineraire(adresseArrivee, adresseDepart, dateRetour);
				
		//Récupération itinéraire départ google
		DistanceGoogleResponse distanceDepartGoogleResponse = googleWebService.getItineraire(adresseDepart, adresseArrivee);
		//Récupération itinéraire retour google
		DistanceGoogleResponse distanceRetourGoogleResponse = googleWebService.getItineraire(adresseArrivee, adresseDepart);
		
		Date heureDepartAller = new Date();
		Date heureArriveeAller = new Date();
		Date heureDepartRetour = new Date();
		Date heureArriveeRetour = new Date();
		
		try {
			heureDepartAller = HEURE_FORMAT_TAN.parse(itineraireDepartTanResponse[0].getHeureDepart());
			heureArriveeAller = HEURE_FORMAT_TAN.parse(itineraireDepartTanResponse[0].getHeureArrivee());
			heureDepartRetour = HEURE_FORMAT_TAN.parse(itineraireRetourTanResponse[0].getHeureDepart());
			heureArriveeRetour = HEURE_FORMAT_TAN.parse(itineraireRetourTanResponse[0].getHeureArrivee());
		} catch (ParseException e) {
			LOG.log(Level.SEVERE, "AdresseServiceImpl::findItineraire Erreur lors de la conversion de des heures de départ et d'arrivé", e);
		}
		
		//TODO : Faire le cout de l'essence également
		int tempsAller = (int) (distanceDepartGoogleResponse.getRows().get(0).getElements().get(0).getDuration().getValue()/S_IN_MINUTE);
		int tempsRetour = (int) (distanceRetourGoogleResponse.getRows().get(0).getElements().get(0).getDuration().getValue()/S_IN_MINUTE);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateDepart);
		
		//On ajoute le temps d'aller au parking à la date de départ pour le calcul
		cal.add(Calendar.MINUTE, tempsAller);
		float coutVoiture = TarifsParkingsHelper.calculerCoutParking(cal.getTime(), dateRetour, adresseArrivee.getCoord());
		
		//Initialisation de l'objet réponse
		BusVsVoiture busVsVoiture = new BusVsVoiture(adresseDepart, adresseArrivee);
		
		//////////////////////////////////////////
		//Récupération des points de passage Bus//
		//////////////////////////////////////////
		List<Waypoint> listeWaypointsBus = new ArrayList<Waypoint>();
		listeWaypointsBus.add(new Waypoint(adresseDepart.getCoord(),adresseDepart.getNom(),"Départ", null, null));
		
		for(Etape etape : itineraireDepartTanResponse[0].getEtapes()){
			Stop arretStop = stopDAO.findStopByName(etape.getArretStop().getLibelle());
			if(etape.getLigne()==null){
				//Alors on marche
				if (arretStop!=null){
					Waypoint waypoint = new Waypoint(new Coordonnee(arretStop.getStop_lat(), arretStop.getStop_lon()),arretStop.getStop_name(),"Description", null, TravelMode.MARCHE);
					listeWaypointsBus.add(waypoint);
				} else {
					LOG.log(Level.SEVERE, "AdresseServiceImpl::findItineraire Aucun arrêt correspondant");
				}
			} else {
				//On est déjà sur un arrêt

				String stopDepart;
				//Si on a jamais marché, on est à un arrêt, on le récupère
				if(listeWaypointsBus.size()==0){
					stopDepart = itineraireDepartTanResponse[0].getArretDepart().getLibelle();
				} else { //Sinon on récupère l'arrêt jusqu'a lequel on a marché soit le dernier enregistré
					stopDepart = listeWaypointsBus.get(listeWaypointsBus.size()-1).getTitre();
				}
				listeWaypointsBus.addAll(trajetBusDAO.getWaypoints(etape.getLigne().getNumLigne(), etape.getLigne().getTerminus(), stopDepart, arretStop.getStop_name()));
			}
			
		}
		
		//////////////////////////////////////////////
		//Récupération des points de passage Voiture//
		//////////////////////////////////////////////
		List<Waypoint> listeWaypointsVoiture = new ArrayList<Waypoint>();
		listeWaypointsVoiture.add(new Waypoint(adresseDepart.getCoord(),adresseDepart.getNom(), "Départ", null, TravelMode.VOITURE));
		listeWaypointsVoiture.add(new Waypoint(adresseArrivee.getCoord(),adresseArrivee.getNom(),"Départ", null, TravelMode.VOITURE));
		
		//TRAJET BUS
		busVsVoiture.getTrajetBus().setCout(tanWebService.calculCoutTrajet(dateDepart, dateRetour, abonnementTan));
		busVsVoiture.getTrajetBus().setTempsAller((int)((heureArriveeAller.getTime()-heureDepartAller.getTime())/MS_IN_MINUTE));
		busVsVoiture.getTrajetBus().setTempsRetour((int)((heureArriveeRetour.getTime()-heureDepartRetour.getTime())/MS_IN_MINUTE));
		busVsVoiture.getTrajetBus().setListeWaypoints(listeWaypointsBus);
		//TODO : Calculs des distances ? (Cron qui calcul les distances entre arrêts ?)
		busVsVoiture.getTrajetBus().setDistanceAller(0);
		busVsVoiture.getTrajetBus().setDistanceRetour(0);
		
		//TRAJET VOITURE
		busVsVoiture.getTrajetVoiture().setCout(coutVoiture);
		busVsVoiture.getTrajetVoiture().setTempsAller(tempsAller);
		busVsVoiture.getTrajetVoiture().setTempsRetour(tempsRetour);
		busVsVoiture.getTrajetVoiture().setListeWaypoints(listeWaypointsVoiture);
		busVsVoiture.getTrajetVoiture().setDistanceAller(distanceDepartGoogleResponse.getRows().get(0).getElements().get(0).getDistance().getValue());
		busVsVoiture.getTrajetVoiture().setDistanceRetour(distanceRetourGoogleResponse.getRows().get(0).getElements().get(0).getDistance().getValue());
		
		return busVsVoiture;
	}


}