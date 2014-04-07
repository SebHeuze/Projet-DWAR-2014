package com.projetweb.service.impl;

import java.util.Date;
import java.util.List;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.DistanceGoogleResponse;
import com.projetweb.bean.ItineraireTanResponse;
import com.projetweb.dao.AdresseDAO;
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
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(AdresseServiceImpl.class.getName());

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
	public List<Adresse> findItineraire(String idAdresseDepart,	String idAdresseArrivee, Date dateItineraire, float prixCarburant, float consommationVoiture) {
		//On récupère les deux adresses en base
		LOG.info("AdresseServiceImpl::findItineraire récupération des adresses en base");
		Adresse adresseDepart = adresseDAO.getAdresseById(idAdresseDepart);
		Adresse adresseArrivee = adresseDAO.getAdresseById(idAdresseArrivee);
		
		//Récupération itinéraire tan
		ItineraireTanResponse[] itineraireTanResponse = tanWebService.itineraire(adresseDepart, adresseArrivee, dateItineraire);
		
		//Récupération itinéraire google
		DistanceGoogleResponse distanceGoogleResponse = googleWebService.getItineraire(adresseDepart, adresseArrivee);
		return null;
	}


}