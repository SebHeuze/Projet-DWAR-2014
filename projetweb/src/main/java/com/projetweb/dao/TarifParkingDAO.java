package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.TarifParking;

public interface TarifParkingDAO {
    /**
     * Créer un nouveau TarifParking
     */
	TarifParking store(TarifParking tarifParking);

    
    /**
     * Retourne la liste de tous les tarifs
     */
    List<TarifParking> getAll();

    /**
     * Stock tous les Tarifs Parking
     */
    void storeAll(TarifParking[] tarifParkingArray);
    
    /**
     * Supprime tous les Tarifs Parking
     */
    public void deleteAll();
}