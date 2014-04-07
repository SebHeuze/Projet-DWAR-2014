package com.projetweb.web;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.projetweb.bean.Adresse;
import com.projetweb.service.AdresseService;

 
@Controller
@RequestMapping("/adresse")
public class AdresseController {
 
	
	/**
	 * Service d'appel TAN
	 */
	@Autowired
	private AdresseService adresseService;
	
	
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
	public @ResponseBody List<Adresse> findAdress(@RequestParam String adresse, HttpServletRequest req, HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		LOG.info("AdresseController::findAdress Début appel controlleur");
		List<Adresse> listeAdresse = adresseService.findAdressesWithCoord(adresse);
		
		return listeAdresse;
	}
	
	/**
	 * Récupérer les détails du trajet BUS et Voiture
	 * @param adresse
	 * @return la liste d'adresse au format JSON
	 */
	@RequestMapping(value="/itineraire", method = RequestMethod.POST)
	public @ResponseBody List<Adresse> findItineraire(@RequestParam String idAdresseDepart, @RequestParam String idAdresseArrivee, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date dateItineraire, @RequestParam float coutCarburant, @RequestParam float consommationVoiture) {
		LOG.info("AdresseController::findItineraire Début appel controlleur");
		List<Adresse> listeAdresse = adresseService.findItineraire(idAdresseDepart, idAdresseArrivee, dateItineraire, coutCarburant, consommationVoiture);
		
		return listeAdresse;
	}


 
 
}