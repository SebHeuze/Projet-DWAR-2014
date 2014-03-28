package com.projetweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;

@Service
public interface AdresseService {
	
	
	/**
	 * Trouve les adresses connues par la TAN ainsi que les coordonnées associées
	 * @param adresse
	 * @return Coordonnee les coordonnées
	 */
	public List<Adresse> findAdressesWithCoord(String adresse);
	
}
