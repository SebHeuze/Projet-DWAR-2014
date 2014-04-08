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
import com.projetweb.service.CronService;

 
@Controller
@RequestMapping("/cron")
public class CronController {
 
	
	/**
	 * Service d'appel TAN
	 */
	@Autowired
	private CronService cronService;
	
	
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(CronController.class.getName());

	/**
	 * Récupérer des adresses en fonction de l'entrée utilisateur (référencées par la TAN)
	 * @param adresse
	 * @return la liste d'adresse au format JSON
	 */
	@RequestMapping(value="/update/parkings", method = RequestMethod.GET)
	public @ResponseBody String updateParkings() {
		
		LOG.info("CronController::updateParkings Début batch mise à jour parkings");
		cronService.updateParkings();
		
		return "";
	}
	



 
 
}