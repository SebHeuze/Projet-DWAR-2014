package com.projetweb.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.projetweb.bean.Favoris;
import com.projetweb.service.AdresseService;

 
@Controller
@RequestMapping("/")
public class MapController {
 
	/**
	 * Service Adresse
	 */
	@Autowired
	private AdresseService adresseService;
	
	@RequestMapping(value="/map", method = RequestMethod.GET)
	public @ResponseBody ModelAndView displayMap(HttpServletRequest req, HttpServletResponse resp) {
			
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		ModelAndView model = new ModelAndView("map");
		
		if (user != null) {
			List<Favoris> favoris = adresseService.getAllFavoris(user);
			
			model.addObject("liste_favoris", favoris);
			model.addObject("login_message", "Bonjour "+ user.getNickname()+" <a href='" 
					+ userService.createLogoutURL(req.getRequestURI())
					+ "'> Se Déconnecter</a>");
			model.addObject("display_fav", "");
			model.addObject("ajouter_favoris","<a href='javascript: void(0)'><img height=50 width=50 src='http://www.neomenage.fr/images/favoris1.59.png'/> Ajouter aux favoris </a>");
		} else {
			model.addObject("login_message", "<a href='"
					+ userService.createLoginURL(req.getRequestURI())
					+ "'> Se connecter avec Google </a>");
			model.addObject("display_fav", "none");
			model.addObject("ajouter_favoris","Vous devez être connecté pour pouvoir ajouter un favoris");

		}
				
		
		return model;
	}
	
 
 
}