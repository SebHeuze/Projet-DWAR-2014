package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Equipement;
import com.projetweb.bean.TarifParking;

public interface EquipementDAO {
    /**
     * Créer un nouveau Equipement
     */
	Equipement store(Equipement equipement);

    
    /**
     * Retourne la liste de tous les Equipement
     */
    List<Equipement> getAll();

    /**
     * Stock tous les Equipement
     */
    void storeAll(Equipement[] equipementArray);
    
    /**
     * Stock tous les Equipement
     */
    void storeAll(List<Equipement> equipementList);
    
    /**
     * Supprime tous les Equipement
     */
    public void deleteAll();
}