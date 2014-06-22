package com.projetweb.dao;

import java.util.List;

import com.google.appengine.api.users.User;
import com.projetweb.bean.Favoris;

public interface FavorisDAO {
    /**
     * Créer un nouveau Favoris
     */
	Favoris store(Favoris favoris);

    
    /**
     * Retourne la liste de tous les Favoris
     */
    List<Favoris> getAll();

    /**
     * Stock tous les Favoris
     */
    void storeAll(Favoris[] favorisArray);
    
    /**
     * Stock tous les Favoris
     */
    void storeAll(List<Favoris> favorisList);
    
    /**
     * Supprime tous les Favoris
     */
    public void deleteAll();


    /**
     * Vérifie si un favoris existe
     * @param favoris
     * @return
     */
	boolean exist(Favoris favoris);

	
	/**
	 * Récupérer tous les favoris d'un utilisateur
	 * @param user
	 * @return
	 */
	List<Favoris> getAllByUser(User user);
}