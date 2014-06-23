package com.projetweb.dao.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.projetweb.PMF;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.Stop;
import com.projetweb.bean.Trajet;
import com.projetweb.bean.TrajetBus;
import com.projetweb.bean.TravelMode;
import com.projetweb.bean.Waypoint;
import com.projetweb.dao.StopDAO;
import com.projetweb.dao.TrajetBusDAO;
import com.projetweb.bean.Shape;
import com.projetweb.helper.ConstantesHelper;
import com.projetweb.helper.TarifsParkingsHelper;
import com.projetweb.helper.UtilsHelper;

@Repository
public class TrajetBusDAOImpl implements TrajetBusDAO {

	private PersistenceManager pm = PMF.get().getPersistenceManager();

	private String pathFichierTrajetBus;

	private String[] lignesTram;
	
	private String[] trajetsOK;
	/**
	 * Gestionnaire Stops en base
	 */
	@Autowired
	private StopDAO stopDAO;

	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(TrajetBusDAOImpl.class
			.getName());

	/**
	 * Initialisation de la BDD avec les TrajetBus
	 */
	@Override
	public void initBDD() {

		LOG.info("TrajetBusDAOImpl::init chargement des trajetBus en base de donnée");
		// Nettoyage de la BDD
		this.deleteAll();
		List<String> trajetsOKList = Arrays.asList(trajetsOK);
		/* Récupération de la liste des TrajetBus */
		Gson gson = new Gson();

		InputStream shapesStream = TrajetBusDAOImpl.class
				.getResourceAsStream(pathFichierTrajetBus);
		String shapesString = UtilsHelper
				.getStringFromInputStream(shapesStream);

		Shape[] arrayShapes = null;
		try {
			arrayShapes = (Shape[]) gson.fromJson(shapesString, Shape[].class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					"Erreur lors de la conversion JSON vers TrajetBus[]", e);
		}

		String currentShapeId = "";
		TrajetBus trajetTmp = null;
		Shape lastShape = null;
		int nbStops=0;
		for (Shape shape : arrayShapes) {
			if (trajetsOKList.contains(shape.getShape_id())){
				if (currentShapeId.isEmpty()
						|| !currentShapeId.equals(shape.getShape_id())) {
					if (trajetTmp != null && trajetTmp.getListeStops().size() > 0) {
						trajetTmp.setDepart(trajetTmp.getListeStops().get(0)
								.getStop_name());
						trajetTmp.setTerminus(trajetTmp.getListeStops()
								.get(trajetTmp.getListeStops().size() - 1)
								.getStop_name());
	
						trajetTmp.setNbArrets(nbStops);
						this.store(trajetTmp);
						nbStops=0;
						lastShape = null;
					}
					currentShapeId = shape.getShape_id();
					trajetTmp = new TrajetBus();
					trajetTmp.setTrajetId(currentShapeId);
					trajetTmp.setListeStops(new ArrayList<Stop>());
					trajetTmp.setComplet(true);
	
					// On enlève les 4 chiffres derrière le numéro de ligne dans
					// l'id Shape
					String ligne = currentShapeId.substring(0,
							currentShapeId.length() - 4);
					trajetTmp.setLigne(ligne);
				}
	
				if (lastShape == null
						|| (!lastShape.getShape_pt_lat().equals(shape.getShape_pt_lat()) && !lastShape
								.getShape_pt_lon().equals(shape.getShape_pt_lon()))) {
					double latitudeShape = shape.getShape_pt_lat();
					double longitudeShape = shape.getShape_pt_lon();
	
					try {
						Stop stop = stopDAO.findStopByCoord(new Coordonnee(
								latitudeShape, longitudeShape));
						if (stop == null) {
							LOG.severe("TrajetBusDAOImpl::initBDD aucun stop correspondant à ces coordonnées "
									+ latitudeShape + "/" + longitudeShape);
							trajetTmp.setComplet(false);
						} else {
							trajetTmp.getListeStops().add(stop);
							nbStops++;
						}
					} catch (Exception e) {
						LOG.log(Level.SEVERE,
								"TrajetBusDAOImpl::initBDD Erreur lors de la recherche du stop correspondant à ces coordonnées "
										+ latitudeShape + "/" + longitudeShape, e);
					}
				}
	
				// On sauvegarde le dernier Shape traité
				lastShape = shape;
			}
			
		}

		LOG.info("TrajetBusDAOImpl::initBDD TrajetBus chargés avec succès");

	}

	@Override
	@Transactional
	public TrajetBus store(TrajetBus trajetBus) {
		return pm.makePersistent(trajetBus);
	}

	@Override
	@Transactional
	public void storeAll(TrajetBus[] trajetBusArray) {
		try {
			for (TrajetBus trajetBus : trajetBusArray) {
				this.store(trajetBus);
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					"Erreur lors de l'insertion en base de données", e);
		}
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<TrajetBus> getAll() {
		return (List<TrajetBus>) pm.newQuery(TrajetBus.class).execute();
	}

	@Override
	@Transactional
	public void deleteAll() {
		try {
			List<TrajetBus> allTrajetBus = this.getAll();
			if (allTrajetBus.size() > 0) {
				pm.deletePersistentAll(allTrajetBus);
			}
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public TrajetBus findTrajet(String ligne, String terminus) {
		List<TrajetBus> returnTrajetBus = new ArrayList<TrajetBus>();
		try {
			Query q = pm.newQuery(TrajetBus.class);
			//Mettre la première lettre en majuscule parce que la Tan a eu la bonne idée d'en mettre aléatoirement quelle bonne idée
			terminus = terminus.replaceFirst(".",(terminus.charAt(0)+"").toUpperCase());
			q.setFilter("ligne == ligneParam && terminus == terminusParam && complet==true");
			q.declareParameters("String ligneParam, String terminusParam");
			returnTrajetBus = (List<TrajetBus>) q.execute(ligne, terminus);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Problème lors de la récupération du trajet", e);
		}
		return returnTrajetBus.size() == 0 ? null : returnTrajetBus.get(0);
	}

	@Override
	public List<Waypoint> getWaypoints(String numLigne, String terminus,
			String stopDepart, String stopArrive) {
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		//On récupère la ligne
		TrajetBus ligne = this.findTrajet(numLigne, terminus);
		
		boolean entreArrets = false;
		//On défile tant qu'on est pas à l'arrêt de départ
		for (Stop stop : ligne.getListeStops()){
			//Si on est entre les arrets départ et arrivee alors on enregistre le Waypoint
			if(entreArrets){
				TravelMode travelMode = Arrays.asList(lignesTram).contains(numLigne) ? TravelMode.TRAM : TravelMode.BUS;
				Waypoint point = new Waypoint(new Coordonnee(stop.getStop_lat(), stop.getStop_lon()), stop.getStop_name(), "Description", numLigne, travelMode);
				waypoints.add(point);
			}
			//Si le stop actuel correspond au stop de départ alors on est sur le bon segment de ligne
			if(stop.getStop_name().toUpperCase().equals(stopDepart.toUpperCase())){
				entreArrets= true;
			}
			//Si le stop actuel correspond au stop d'arrivée alors on quitte
			if(stop.getStop_name().toUpperCase().equals(stopArrive.toUpperCase())){
				break;
			}
			
		}
		return waypoints;
	}

	/**
	 * @return the pathFichierTrajetBus
	 */
	public String getPathFichierTrajetBus() {
		return pathFichierTrajetBus;
	}

	/**
	 * @param pathFichierTrajetBus
	 *            the pathFichierTrajetBus to set
	 */
	public void setPathFichierTrajetBus(String pathFichierTrajetBus) {
		this.pathFichierTrajetBus = pathFichierTrajetBus;
	}

	/**
	 * @return the lignesTram
	 */
	public String[] getLignesTram() {
		return lignesTram;
	}

	/**
	 * @param lignesTram the lignesTram to set
	 */
	public void setLignesTram(String[] lignesTram) {
		this.lignesTram = lignesTram;
	}

	/**
	 * @return the trajetsOK
	 */
	public String[] getTrajetsOK() {
		return trajetsOK;
	}

	/**
	 * @param trajetsOK the trajetsOK to set
	 */
	public void setTrajetsOK(String[] trajetsOK) {
		this.trajetsOK = trajetsOK;
	}

}
