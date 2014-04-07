package com.projetweb.service;


import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.DistanceGoogleResponse;

@Service
public interface GoogleWebService {
	
	
	/**
	 * Trouve les coordonnées d'une adresse donnée
	 * @param adresse
	 * @return Coordonnee les coordonnées
	 */
	Coordonnee findCoordonnees(String adresse);
	
	/**
	 * Calcul l'itinéraire en voiture entre deux points
	 * @param depart
	 * @param arrivee
	 * @return DistanceGoogleResponse retour Google
	 */
	public DistanceGoogleResponse getItineraire(Adresse depart, Adresse arrivee);
}
