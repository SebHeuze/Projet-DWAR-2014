package com.projetweb.service.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.projet.helper.HttpRequestHelper.postHttpRequest;

import java.util.Map;

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
	
	@Override
	public List<Adresse> findAdresses(String adresse) {
		
		List<Adresse> adressesList = new ArrayList<Adresse>();
		
		Gson gson = new Gson();
		

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("nom", adresse);
		Reader result = postHttpRequest(tanUrl + serviceAdresse,paramsMap);
		
		AddressTanResponse[] listeReponse = (AddressTanResponse[]) gson.fromJson(result, AddressTanResponse[].class);
		AddressTanResponse reponseTan = listeReponse[0];
		
		Adresse tmpAdresse = new Adresse();
		
		tmpAdresse.setNom(reponseTan.getLieux().get(0).getNom());
		
		adressesList.add(tmpAdresse);
		
		return adressesList;
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
