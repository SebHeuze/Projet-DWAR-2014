package com.projetweb.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UrlEncodedContent;
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
		
		try {  
			UrlFetchTransport HTTP_TRANSPORT = new UrlFetchTransport();
			HttpRequestFactory httpRequestFactory = HTTP_TRANSPORT.createRequestFactory();
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("nom", adresse);
			HttpRequest req;
			
			req = httpRequestFactory.buildPostRequest(new GenericUrl(tanUrl + serviceAdresse), new UrlEncodedContent(paramsMap));
			
			req.setFollowRedirects(true);
			HttpResponse response = req.execute();
	            
	        
			if(response.getStatusCode() == 200) {
				InputStream content = response.getContent();
				//Read the server response and attempt to parse it as JSON
				Reader reader = new InputStreamReader(content);
				
				AddressTanResponse[] listeReponse = (AddressTanResponse[]) gson.fromJson(reader, AddressTanResponse[].class);
				AddressTanResponse reponseTan = listeReponse[0];
				
				Adresse tmpAdresse = new Adresse();
				
				tmpAdresse.setNom(reponseTan.getLieux().get(0).getNom());
				
				adressesList.add(tmpAdresse);
			} else {
				System.out.println(response.getStatusCode());
				//TODO: Logs
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
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
