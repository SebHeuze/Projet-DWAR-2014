package com.projetweb.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import static com.projetweb.helper.ConstantesHelper.HEURE_FORMAT_TAN;
import static com.projetweb.helper.ConstantesHelper.MS_IN_MINUTE;
import static com.projetweb.helper.ConstantesHelper.S_IN_MINUTE;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.BusVsVoiture;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.DistanceGoogleResponse;
import com.projetweb.bean.ItineraireTanResponse;
import com.projetweb.dao.AdresseDAO;
import com.projetweb.dao.StopDAO;
import com.projetweb.dao.TrajetBusDAO;
import com.projetweb.helper.TarifsParkingsHelper;
import com.projetweb.service.AdminService;
import com.projetweb.service.AdresseService;
import com.projetweb.service.GoogleWebService;
import com.projetweb.service.TanWebService;

public class AdminServiceImpl implements AdminService{
	

	/**
	 * Gestionnaire Trajets en base
	 */
	@Autowired
	private TrajetBusDAO trajetBusDAO;
	
	/**
	 * Gestionnaire Stops en base
	 */
	@Autowired
	private StopDAO stopDAO;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(AdminServiceImpl.class.getName());

	@Override
	public void initBDD() {
		stopDAO.initBDD();
		trajetBusDAO.initBDD();		
	}



}