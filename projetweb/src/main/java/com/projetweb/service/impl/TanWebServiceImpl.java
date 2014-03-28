package com.projetweb.service.impl;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import static com.projetweb.helper.HttpRequestHelper.postHttpRequest;

import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.projetweb.bean.AddressTanResponse;
import com.projetweb.bean.Adresse;
import com.projetweb.service.TanWebService;

public class TanWebServiceImpl implements TanWebService{
	
	/**
	 * Adresse du site de la tan
	 */
	private String tanUrl;
	
	/**
	 * Adresse service Adresse de l'API tan
	 */
	private String serviceAdresse;
	
	/**
	 * Adresse service itinéraire de l'API tan
	 */
	private String serviceItineraire;
	
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(TanWebServiceImpl.class.getName());
	
	@Override
	public List<Adresse> findAdresses(String adresse) {
		
		Gson gson = new Gson();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("nom", adresse);
		
		LOG.info("TanWebServiceImpl::findAdresses Appel TAN avec l'adresse "+adresse);
		Reader result = postHttpRequest(tanUrl + serviceAdresse,paramsMap);
		
		LOG.info("TanWebServiceImpl::findAdresses Transformation JSON");
		AddressTanResponse[] listeReponse = (AddressTanResponse[]) gson.fromJson(result, AddressTanResponse[].class);
		AddressTanResponse reponseTan = listeReponse[0];
		
		return reponseTan.getLieux();
	}

	/**
	 * @param tanUrl the tanUrl to set
	 */
	public void setTanUrl(String tanUrl) {
		this.tanUrl = tanUrl;
	}

	/**
	 * @param serviceAdresse the serviceAdresse to set
	 */
	public void setServiceAdresse(String serviceAdresse) {
		this.serviceAdresse = serviceAdresse;
	}

	/**
	 * @param serviceItineraire the serviceItineraire to set
	 */
	public void setServiceItineraire(String serviceItineraire) {
		this.serviceItineraire = serviceItineraire;
	}

}
