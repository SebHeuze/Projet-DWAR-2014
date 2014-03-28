package com.projetweb.service.impl;

import java.util.List;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.dao.AdresseDAO;
import com.projetweb.service.AdresseService;
import com.projetweb.service.GoogleWebService;
import com.projetweb.service.TanWebService;

public class AdresseServiceImpl implements AdresseService{
	
	/**
	 * Service d'appel TAN
	 */
	@Autowired
	private TanWebService tanWebService;
	
	/**
	 * Service d'appel API Googke
	 */
	@Autowired
	private GoogleWebService googleWebService;
	
	/**
	 * Gestionnaire Adresses en base
	 */
	@Autowired
	private AdresseDAO adresseDAO;
	
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(AdresseServiceImpl.class.getName());

	@Override
	public List<Adresse> findAdressesWithCoord(String adresse) {
		List<Adresse> listeAdresse = tanWebService.findAdresses(adresse);
		for(Adresse uneAdresse : listeAdresse){
			Coordonnee coord = googleWebService.findCoordonnees(uneAdresse.toString());
			uneAdresse.setCoord(coord);
			adresseDAO.store(uneAdresse);
		}
		return listeAdresse;
	}


	
	


	
	

}