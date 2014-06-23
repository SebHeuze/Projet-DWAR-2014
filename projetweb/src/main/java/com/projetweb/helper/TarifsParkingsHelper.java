package com.projetweb.helper;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.InternalResourceView;

import com.google.appengine.api.log.LogService.LogLevel;
import com.google.gson.Gson;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.Equipement;
import com.projetweb.bean.ListeEquipementsAPIReponse;
import com.projetweb.bean.Parking;
import com.projetweb.bean.Tarif;
import com.projetweb.bean.TarifParking;

public class TarifsParkingsHelper {

	private static HashMap<Integer, HashMap<String, TreeSet<TarifParking>>> listeTarifsParkings;
	private static HashMap<String, Equipement> listeEquipements;


	private static String pathFichierTarifsParkings;
	private static String pathFichierListeEquipements;
	private static String categoriesParkings;
	
	private static List<String> listeCategoriesParkings;
	
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(TarifsParkingsHelper.class.getName());
	
 	/**
 	 * Initialisation du helper avec les tarifs parkings et les équipements
 	 */
 	public static void init(){
 		/* Initialisation de la liste des catégories parkings */
 		if(categoriesParkings != null && !categoriesParkings.isEmpty()) {
 			listeCategoriesParkings = Arrays.asList(categoriesParkings.split(","));
		}
 		
 		/* Initialisation des tarifs parkings */
 		Gson gson = new Gson();
 		listeTarifsParkings = new HashMap<Integer, HashMap<String, TreeSet<TarifParking>>>();
 		listeEquipements = new HashMap<String, Equipement>();
 		
 		InputStream  tarifsStream = TarifsParkingsHelper.class.getResourceAsStream(pathFichierTarifsParkings);
 		String tarifsString = UtilsHelper.getStringFromInputStream(tarifsStream);
 		
 		TarifParking[] arrayTarifsParkings = null;
 		try{
 			arrayTarifsParkings  = (TarifParking[]) gson.fromJson(tarifsString, TarifParking[].class);
 		} catch (Exception e){
 			LOG.log(Level.SEVERE, "Erreur lors de la conversion JSON vers TarifParkings[]", e);
 		}
 		
 		for (TarifParking tarifP : arrayTarifsParkings){
 			if (!listeTarifsParkings.containsKey(tarifP.getCategorie())){
 				HashMap<String, TreeSet<TarifParking>> tarifsCategorie = new HashMap<String, TreeSet<TarifParking>>();
 				listeTarifsParkings.put(tarifP.getCategorie(), tarifsCategorie);
 			}
 			if (!listeTarifsParkings.get(tarifP.getCategorie()).containsKey(tarifP.getConcerne())){
 				TreeSet<TarifParking> tarifsConcerne = new TreeSet<TarifParking>();
 				listeTarifsParkings.get(tarifP.getCategorie()).put(tarifP.getConcerne(), tarifsConcerne );
 			}
 			listeTarifsParkings.get(tarifP.getCategorie()).get(tarifP.getConcerne()).add(tarifP);
 		}
 		
 		/* Initialisation des équipements (filtrés par categorie)*/
 		
 		InputStream  equipementsStream = TarifsParkingsHelper.class.getResourceAsStream(pathFichierListeEquipements);
 		String EquipementsString = UtilsHelper.getStringFromInputStream(equipementsStream);
 		
 		ListeEquipementsAPIReponse listeEquipementsAPIReponse = null;
 		try{
 			listeEquipementsAPIReponse  = (ListeEquipementsAPIReponse) gson.fromJson(EquipementsString, ListeEquipementsAPIReponse.class);
 		} catch (Exception e) {
 			LOG.log(Level.SEVERE, "Erreur lors de la conversion JSON vers Equipement[]", e);
 		}
 		
 		
 		for (Equipement equipement : listeEquipementsAPIReponse.getData()){
 			if(listeCategoriesParkings.contains(String.valueOf(equipement.getCategorie()))){
 				equipement.update();
 				listeEquipements.put(equipement.getGeo().getName(), equipement);
 			}
 		}
 	}
 	
 	/**
 	 * Calculer le cout du stationnement
 	 * @param debut
 	 * @param fin
 	 * @return
 	 */
	public static Parking calculerCoutParking(Date debut, Date fin, Coordonnee maPosition){
		Equipement parkingProche = trouverParkingLePlusProche(maPosition);
		Parking parking = new Parking();
		parking.setCoord(parkingProche.getCoordonnees());
		parking.setNom(parkingProche.getGeo().getName());
		parking.setCout(prixParking(debut, fin, parkingProche));
		return parking;
	}

	/**
	 * Calculer le cout de stationnement
	 * @param parkingProche
	 * @return
	 */
	private static float prixParking(Date debut, Date fin, Equipement parkingProche) {
		TreeSet<TarifParking> tarifsParking;
		float coutParking = 0;
		
		if(!listeTarifsParkings.get(parkingProche.getCategorie()).containsKey(parkingProche.getGeo().getName())){
			tarifsParking = listeTarifsParkings.get(parkingProche.getCategorie()).get("default");
		} else {
			tarifsParking = listeTarifsParkings.get(parkingProche.getCategorie()).get(parkingProche.getGeo().getName());
		}
		
		/* Date ou en est rendu le traitement */

		Calendar cal = Calendar.getInstance();
		Date dateTraitement = debut;
		cal.setTime(dateTraitement);
		double timeBetweenHours = 0;
		double tempsStationnementRestant = 0;
		double nombreHeureStationnementCreneau = 0;
		boolean continuer = true;
		
		
		while (continuer){
			/* Initialisation de l'iterateur */
			Iterator<TarifParking> iterator = tarifsParking.iterator();
			TarifParking tarifActuel = iterator.next();
			
			/* les tarifs par jour */
			while(!UtilsHelper.betweenDay(tarifActuel.getJour_debut(), tarifActuel.getJour_fin(), cal.get(Calendar.DAY_OF_WEEK)-1)){
				tarifActuel = iterator.next();
			}
			
			/* on parcours ensuite les tarifs par heure */
			double heureExacte = ((double) cal.get(Calendar.HOUR_OF_DAY))+((double)cal.get(Calendar.MINUTE)/ConstantesHelper.MINUTE_IN_HOUR);
			while(!UtilsHelper.betweenHour(tarifActuel.getHeure_debut(), tarifActuel.getHeure_fin(), heureExacte)){
				tarifActuel = iterator.next();
			}
			
			/* On a maintenant le tarif applicable à cette heure */
			timeBetweenHours = UtilsHelper.substract_hour(tarifActuel.getHeure_fin(), tarifActuel.getHeure_debut());
			tempsStationnementRestant = ((double)(fin.getTime()-dateTraitement.getTime())/ConstantesHelper.MS_IN_HOUR);
			
			if(tempsStationnementRestant<timeBetweenHours){
				/* Si on part avant le changement de tarif, on cherche le bon tarif et on sort */
				nombreHeureStationnementCreneau = tempsStationnementRestant;
				continuer = false;

			} else {
				/* Sinon on prend le tarif max pour cette plage horaire et on passe au suivant */
				/* Nombre d'heures avant le creneau suivant */
				nombreHeureStationnementCreneau = UtilsHelper.substract_hour(tarifActuel.getHeure_fin(),cal.get(Calendar.HOUR_OF_DAY)+(cal.get(Calendar.MINUTE)/ConstantesHelper.MINUTE_IN_HOUR));

			}
			
			for(Tarif tarif : tarifActuel.getTarifs()){
				if(tarif.getTemps()>=nombreHeureStationnementCreneau){
					coutParking += tarif.getPrix();
					break;
				} else if (tarif.getType().equals("linear") || tarif.getType().equals("closed")) {
					int nbCreneaux = (int) Math.ceil(nombreHeureStationnementCreneau/tarif.getTemps());
					coutParking += nbCreneaux * tarif.getPrix();
					break;
				} else if (tarif.getType().equals("max")) {
					coutParking += tarif.getPrix();
					break;
				}
			}
			
			//On avance sur le prochain créneau tarif.
			cal.add(Calendar.HOUR, (int) nombreHeureStationnementCreneau);
			cal.add(Calendar.MINUTE,(int) ((nombreHeureStationnementCreneau - (int)nombreHeureStationnementCreneau)*ConstantesHelper.MINUTE_IN_HOUR));
			dateTraitement = cal.getTime();
			
		}
		
		//Résolution problème de précision java, ou quand 2.3 + 2.4 ne font pas 4.7, narmol
		return (float) (Math.round(coutParking*100.0)/100.0);
	}

	/**
	 * Trouver le parking le plus proche
	 * @param maPosition
	 * @return
	 */
	private static Equipement trouverParkingLePlusProche(Coordonnee maPosition) {
		Equipement parkingProche = null;
		double distanceTmp;
		double distance = -1;
		
		for (Equipement parking: listeEquipements.values()){
			distanceTmp = UtilsHelper.getDistance(maPosition, parking.getCoordonnees());
			if (distance == -1 || distanceTmp < distance){
				distance = distanceTmp;
				parkingProche=parking;
			}
		}
		return parkingProche;
	}

	/**
	 * @return the pathFichierListeEquipements
	 */
	public static String getPathFichierListeEquipements() {
		return pathFichierListeEquipements;
	}

	/**
	 * @param pathFichierListeEquipements the pathFichierListeEquipements to set
	 */
	public static void setPathFichierListeEquipements(
			String pathFichierListeEquipements) {
		TarifsParkingsHelper.pathFichierListeEquipements = pathFichierListeEquipements;
	}

	/**
	 * @return the pathFichierTarifsParkings
	 */
	public static String getPathFichierTarifsParkings() {
		return pathFichierTarifsParkings;
	}

	/**
	 * @param pathFichierTarifsParkings the pathFichierTarifsParkings to set
	 */
	public static void setPathFichierTarifsParkings(String pathFichierTarifsParkings) {
		TarifsParkingsHelper.pathFichierTarifsParkings = pathFichierTarifsParkings;
	}

	/**
	 * @return the listeCategoriesParkings
	 */
	public static List<String> getListeCategoriesParkings() {
		return listeCategoriesParkings;
	}

	/**
	 * @param listeCategoriesParkings the listeCategoriesParkings to set
	 */
	public static void setListeCategoriesParkings(
			List<String> listeCategoriesParkings) {
		TarifsParkingsHelper.listeCategoriesParkings = listeCategoriesParkings;
	}

	/**
	 * @return the categoriesParkings
	 */
	public static String getCategoriesParkings() {
		return categoriesParkings;
	}

	/**
	 * @param categoriesParkings the categoriesParkings to set
	 */
	public static void setCategoriesParkings(String categoriesParkings) {
		TarifsParkingsHelper.categoriesParkings = categoriesParkings;
	}
	
	
	
}
