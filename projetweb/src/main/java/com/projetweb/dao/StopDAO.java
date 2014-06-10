package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.Stop;
import com.projetweb.bean.TarifParking;

public interface StopDAO {
	
	/**
	 * Initialiser la base de donnée
	 */
	void initBDD();
	
    /**
     * Créer un nouveau TarifParking
     */
	Stop store(Stop stop);

    
    /**
     * Retourne la liste de tous les tarifs
     */
    List<Stop> getAll();

    /**
     * Stock tous les Tarifs Parking
     */
    void storeAll(Stop[] stopsArray);
    
    /**
     * Supprime tous les Tarifs Parking
     */
    public void deleteAll();
    
    /**
     * Trouver le stop par coordonnée
     */
    public Stop findStopByCoord(Coordonnee coord);
    
    /**
     * Récupère l'arrêt qui correspond à ce nom
     * @param name
     * @return
     */
	public Stop findStopByName(String name);
	
}