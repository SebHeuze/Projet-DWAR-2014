package com.projetweb.service.impl;

import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.projetweb.helper.ConstantesHelper.DATE_FORMAT_TAN;
import static com.projetweb.helper.HttpRequestHelper.postHttpRequest;

import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.projetweb.bean.AddressTanResponse;
import com.projetweb.bean.Adresse;
import com.projetweb.bean.ItineraireTanResponse;
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

	@Override
	public ItineraireTanResponse[] itineraire(Adresse adresseDepart, Adresse adresseArrivee, Date dateItineraire) {
		Gson gson = new Gson();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("depart", adresseDepart.getId());
		paramsMap.put("arrive", adresseArrivee.getId());
		paramsMap.put("type", "0");
		paramsMap.put("accessible", "0");
		paramsMap.put("temps", DATE_FORMAT_TAN.format(dateItineraire));
		paramsMap.put("retour", "0");
		
		LOG.info("TanWebServiceImpl::itineraire Appel TAN, Adresse depart : "+adresseDepart.toString()+ " Adresse arrivee : "+adresseArrivee.toString());
		Reader result = postHttpRequest(tanUrl + serviceItineraire,paramsMap);
		
		LOG.info("TanWebServiceImpl::itineraire Transformation JSON");
		ItineraireTanResponse[] itineraireTanResponse = (ItineraireTanResponse[]) gson.fromJson(result, ItineraireTanResponse[].class);
		
		return itineraireTanResponse;
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
