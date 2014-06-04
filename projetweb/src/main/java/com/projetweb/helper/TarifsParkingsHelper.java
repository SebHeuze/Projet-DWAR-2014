package com.projetweb.helper;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.Equipement;
import com.projetweb.bean.TarifParking;

public class TarifsParkingsHelper {

	private static HashMap<Integer, HashMap<String, TreeSet<TarifParking>>> listeTarifsParkings;
	private static HashMap<String, Equipement> listeEquipements;


	private static String pathFichierTarifsParkings;
	private static String pathFichierListeEquipements;
	private static String categoriesParkings;
	
	private static List<String> listeCategoriesParkings;
	
 	@Autowired
	private static ServletContext servletContext;
	
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
 		
 		InputStream  tarifsStream = servletContext.getResourceAsStream(pathFichierTarifsParkings);
 		String tarifsString = UtilsHelper.getStringFromInputStream(tarifsStream);
 		
 		TarifParking[] arrayTarifsParkings  = (TarifParking[]) gson.fromJson(tarifsString, TarifParking[].class);
 		
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
 		
 		InputStream  equipementsStream = servletContext.getResourceAsStream(pathFichierListeEquipements);
 		String EquipementsString = UtilsHelper.getStringFromInputStream(equipementsStream);
 		
 		Equipement[] arrayEquipements  = (Equipement[]) gson.fromJson(EquipementsString, Equipement[].class);
 		
 		for (Equipement equipement : arrayEquipements){
 			if(listeCategoriesParkings.contains(String.valueOf(equipement.getCategorie()))){
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
	public float calculerCoutParking(Date debut, Date fin, Coordonnee maPosition){
		Equipement parkingProche = this.trouverParkingLePlusProche(maPosition);
		
		return prixParking(debut, fin, parkingProche);
	}

	/**
	 * Calculer le cout de stationnement
	 * @param parkingProche
	 * @return
	 */
	private float prixParking(Date debut, Date fin, Equipement parkingProche) {
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
		
		Iterator<TarifParking> iterator = tarifsParking.iterator();
		TarifParking tarifActuel = iterator.next();
		
		while (dateTraitement.before(fin)){
			/* les tarifs par jour */
			while(!UtilsHelper.betweenDay(tarifActuel.getJour_debut(), tarifActuel.getJour_fin(), cal.get(Calendar.DAY_OF_MONTH))){
				tarifActuel = iterator.next();
			}
			
			/* on parcours ensuite les tarifs par heure */
			while(!UtilsHelper.betweenHour(tarifActuel.getHeure_debut(), tarifActuel.getHeure_fin(), cal.get(Calendar.HOUR_OF_DAY))){
				tarifActuel = iterator.next();
			}
			
			/* On a maintenant le tarif applicable à cette heure */
			timeBetweenHours = UtilsHelper.substract_hour(tarifActuel.getHeure_fin(), tarifActuel.getHeure_debut());
			
			if(((fin.getTime()-dateTraitement.getTime())/ConstantesHelper.MS_IN_HOUR)<timeBetweenHours){
				/* Si on part avant le changement de tarif, on cherche le bon tarif et on sort */
				while(true){
					tarifActuel = iterator.next();
				}
			} else {
				/* Sinon on prend le tarif max pour cette plage horaire et on passe au suivant */
			}
		}
		
		return coutParking;
	}

	/**
	 * Trouver le parking le plus proche
	 * @param maPosition
	 * @return
	 */
	private Equipement trouverParkingLePlusProche(Coordonnee maPosition) {
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
	
	
	
}
