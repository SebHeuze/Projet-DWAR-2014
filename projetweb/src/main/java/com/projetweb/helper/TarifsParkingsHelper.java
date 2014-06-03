package com.projetweb.helper;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.projetweb.bean.Equipement;
import com.projetweb.bean.TarifParking;

public class TarifsParkingsHelper {

	public static HashMap<Integer, HashMap<String, TreeSet<TarifParking>>> listeTarifsParkings;
	public static HashMap<String, Equipement> listeEquipements;


	public static String pathFichierTarifsParkings;
	
	public static String pathFichierListeEquipements;
	
	
 	@Autowired
	ServletContext servletContext;
	
 	/**
 	 * Initialisation du helper avec les tarifs parkings et les équipements
 	 */
 	public void init(){
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
 		
 		InputStream  equipementsStream = servletContext.getResourceAsStream(pathFichierListeEquipements);
 		String EquipementsString = UtilsHelper.getStringFromInputStream(equipementsStream);
 		
 		Equipement[] arrayEquipements  = (Equipement[]) gson.fromJson(EquipementsString, Equipement[].class);
 		
 		for (Equipement equipement : arrayEquipements){
 			listeEquipements.put(equipement.getGeo().getName(), equipement);
 		}
 	}
 	
 	/**
 	 * Calculer le cout du stationnement
 	 * @param debut
 	 * @param fin
 	 * @return
 	 */
	public float calculerCoutParking(Date debut, Date fin){
		
		
		return 0;
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
