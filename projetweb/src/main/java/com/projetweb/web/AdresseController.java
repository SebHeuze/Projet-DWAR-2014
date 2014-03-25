package com.projetweb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projetweb.bean.Adresse;
import com.projetweb.service.TanWebService;
 
@Controller
@RequestMapping("/adresse")
public class AdresseController {
 
	@Autowired
	private TanWebService tanWebService;
	
	@RequestMapping(value="/find", method = RequestMethod.POST)
	public @ResponseBody List<Adresse> findAdress(@RequestParam String adresse) {
		
		List<Adresse> listeAdresse = tanWebService.findAdresses(adresse);
		
		return listeAdresse;
	}
 
 
}