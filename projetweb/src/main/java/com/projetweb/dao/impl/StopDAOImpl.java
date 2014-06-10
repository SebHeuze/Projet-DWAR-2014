package com.projetweb.dao.impl;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.projetweb.PMF;
import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.Stop;
import com.projetweb.dao.StopDAO;
import com.projetweb.helper.TarifsParkingsHelper;
import com.projetweb.helper.UtilsHelper;

@Repository
public class StopDAOImpl implements StopDAO {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	private static String pathFichierStops;
	
	
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(StopDAOImpl.class.getName());
	
	
 	/**
 	 * Initialisation de la BDD avec les Stops
 	 */
	@Override
	public void initBDD(){
 		
 		LOG.info("StopDAOImpl::init chargement des stops en base de donnée");
		//Nettoyage de la BDD
 		this.deleteAll();
				
		
 		/* Récupération de la liste des stops */
 		Gson gson = new Gson();
 		
 		InputStream  stopsStream = StopDAOImpl.class.getResourceAsStream(pathFichierStops);
 		String stopsString = UtilsHelper.getStringFromInputStream(stopsStream);
 		
 		Stop[] arrayStops = null;
 		try{
 			arrayStops  = (Stop[]) gson.fromJson(stopsString, Stop[].class);
 		} catch (Exception e){
 			LOG.log(Level.SEVERE, "Erreur lors de la conversion JSON vers Stop[]", e);
 		}
 		
 		
 		this.storeAll(arrayStops);
 		
 		LOG.info("StopDAOImpl::init Stops chargés avec succès");
 		
 	}


    @Override
    @Transactional
    public Stop store(Stop stop) {
    	return pm.makePersistent(stop);
    }
    
    @Override
    @Transactional	
    public void storeAll(Stop[] stopsArray) {
    	try{
    		for (Stop stop : stopsArray){
    			this.store(stop);
    		}
	    } catch (Exception e) {
	    	LOG.log(Level.SEVERE, "Erreur lors de l'insertion en base de données", e);
	    }
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Stop> getAll() {
    	return (List<Stop>) pm.newQuery(Stop.class).execute();
    }
    
    @Override
    @Transactional
    public void deleteAll() {
    	try {
    		List<Stop> allStops = this.getAll();
    		if (allStops.size()>0){
    			pm.deletePersistentAll(allStops);
    		}
	    } catch (Exception e) {
	    }
    }
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
	public Stop findStopByCoord(Coordonnee coord) {
    	List<Stop> returnStopli = null;
    	try {
    		Query  q = pm.newQuery(Stop.class);
    		q.setFilter("stop_lat == stopLatParam && stop_lon == stopLonParam"); 
    		q.declareParameters("double stopLatParam, double stopLonParam");
    		returnStopli = (List<Stop>) q.execute(coord.getLatitude(),coord.getLongitude());
    	} catch (Exception e) {
        }
    	return returnStopli.size()==0?null:returnStopli.get(0);  
	}



	/**
	 * @return the pathFichierStops
	 */
	public static String getPathFichierStops() {
		return pathFichierStops;
	}


	/**
	 * @param pathFichierStops the pathFichierStops to set
	 */
	public static void setPathFichierStops(String pathFichierStops) {
		StopDAOImpl.pathFichierStops = pathFichierStops;
	}


	
    
}
