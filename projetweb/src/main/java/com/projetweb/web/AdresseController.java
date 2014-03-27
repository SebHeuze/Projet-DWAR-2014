package com.projetweb.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.service.GoogleWebService;
import com.projetweb.service.TanWebService;
 
@Controller
@RequestMapping("/adresse")
public class AdresseController {
 
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
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(AdresseController.class.getName());

	/**
	 * Récupérer des adresses en fonction de l'entrée utilisateur (référencées par la TAN)
	 * @param adresse
	 * @return la liste d'adresse au format JSON
	 */
	@RequestMapping(value="/find", method = RequestMethod.POST)
	public @ResponseBody List<Adresse> findAdress(@RequestParam String adresse) {
		LOG.info("AdresseController::findAdress Début appel controlleur");
		List<Adresse> listeAdresse = tanWebService.findAdresses(adresse);
		for(Adresse uneAdresse : listeAdresse){
			Coordonnee coord = googleWebService.findCoordonnees(uneAdresse.toString());
			uneAdresse.setCoord(coord);
		}
		return listeAdresse;
	}
 
 
}