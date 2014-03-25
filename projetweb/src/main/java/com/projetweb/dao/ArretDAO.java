package com.projetweb.dao;

import java.util.List;

import com.projetweb.bean.Arret;

public interface ArretDAO {
    /**
     * Créer un nouvel arrêt
     */
    public Arret store(Arret arret);

    
    /**
     * Retourne la liste de tous les arrêts
     */
    public List<Arret> getAll();
}