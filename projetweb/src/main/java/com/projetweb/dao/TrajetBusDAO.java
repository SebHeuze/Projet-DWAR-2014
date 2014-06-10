package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.TrajetBus;

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
}