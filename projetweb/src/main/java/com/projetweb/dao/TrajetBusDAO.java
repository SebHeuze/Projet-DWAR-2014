package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.Stop;
import com.projetweb.bean.TrajetBus;
import com.projetweb.bean.Waypoint;

public interface TrajetBusDAO {
	
	/**
	 * Initialiser la base de donnée
	 */
	void initBDD();
	
    /**
     * Créer un nouveau trajet bus
     */
	TrajetBus store(TrajetBus trajetBus);

    /**
     * Retourne la liste de tous les  trajets
     */
    List<TrajetBus> getAll();

    /**
     * Stock tous les TrajetBus
     */
    void storeAll(TrajetBus[] trajetsBusArray);
    
    /**
     * Supprime tous les TrajetBus
     */
    public void deleteAll();

    /**
     * Récupérer les Points entre deux arrêts sur une ligne
     * @param numLigne
     * @param terminus
     * @param stopDepart
     * @param stop_name
     */
	List<Waypoint> getWaypoints(String numLigne, String terminus, String stopDepart,
			String stopArrive);
	
	/**
	 * Récupère une ligne dans la base de donnée
	 * @param ligne
	 * @param terminus
	 * @return
	 */
	TrajetBus findTrajet(String ligne, String terminus);

   
}