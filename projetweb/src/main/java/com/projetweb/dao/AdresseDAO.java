package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.Adresse;

public interface AdresseDAO {
    /**
     * Créer une nouvelle adresse
     */
    Adresse store(Adresse adresse);

    
    /**
     * Retourne la liste de toutes les adresses
     */
    List<Adresse> getAll();
}