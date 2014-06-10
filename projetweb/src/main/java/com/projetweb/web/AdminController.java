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
import com.projetweb.bean.BusVsVoiture;
import com.projetweb.service.AdminService;
import com.projetweb.service.AdresseService;

 
@Controller
@RequestMapping("/admin")
public class AdminController {
 
	
	/**
	 * Service d'appel TAN
	 */
	@Autowired
	private AdminService adminService;
	
	
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(AdminController.class.getName());

	/**
	 * Initialiser la base de donnée en chargeant les fichiers de config
	 */
	@RequestMapping(value="/init-bdd", method = RequestMethod.GET)
	public @ResponseBody String initBDD() {
		LOG.info("AdminController::initBDD Début appel controlleur");
		adminService.initBDD();
		
		return "Sucess";
	}
	
 
 
}