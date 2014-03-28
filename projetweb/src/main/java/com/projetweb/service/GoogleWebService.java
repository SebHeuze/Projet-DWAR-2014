package com.projetweb.service;


import org.springframework.stereotype.Service;

import com.projetweb.bean.Coordonnee;

@Service
public interface GoogleWebService {
	
	
	/**
	 * Trouve les coordonnées d'une adresse donnée
	 * @param adresse
	 * @return Coordonnee les coordonnées
	 */
	Coordonnee findCoordonnees(String adresse);
	
}
