package com.projetweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;

@Service
public interface GoogleWebService {
	
	
	/**
	 * Trouve les coordonnées d'une adresse donnée
	 * @param adresse
	 * @return Coordonnee les coordonnées
	 */
	public Coordonnee findCoordonnees(String adresse);
	
}
