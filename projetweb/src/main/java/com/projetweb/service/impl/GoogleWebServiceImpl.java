package com.projetweb.service.impl;

import java.io.Reader;
import java.util.HashMap;

import static com.projetweb.helper.HttpRequestHelper.getHttpRequest;

import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.projetweb.bean.Adresse;
import com.projetweb.bean.Coordonnee;
import com.projetweb.bean.GeocodeGoogleResponse;
import com.projetweb.bean.generated.google.DistanceGoogleResponse;
import com.projetweb.service.GoogleWebService;

public class GoogleWebServiceImpl implements GoogleWebService{
	
	/**
	 * Adresse de l'API google MAP
	 */
	private String googleMapsAPIUrl;
	
	/**
	 * Adresse service geocode
	 */
	private String serviceGeocode;
	
	/**
	 * Adresse service distance
	 */
	private String serviceDistance;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(GoogleWebServiceImpl.class.getName());


	@Override
	public Coordonnee findCoordonnees(String adresse) {
		Gson gson = new Gson();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("address", adresse);
		paramsMap.put("sensor", "false");
		LOG.info("GoogleWebServiceImpl::findCoordonnees Appel Google avec l'adresse "+adresse);
		Reader result = getHttpRequest(googleMapsAPIUrl + serviceGeocode,paramsMap);

		LOG.info("GoogleWebServiceImpl::findCoordonnees Transformation JSON");
		GeocodeGoogleResponse googleResponse = (GeocodeGoogleResponse) gson.fromJson(result, GeocodeGoogleResponse.class);
		
		return googleResponse.getResults().get(0).getGeometry().getLocation();
	}	
	
	public DistanceGoogleResponse getItineraire(Adresse depart, Adresse arrivee) {
		Gson gson = new Gson();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("origins", depart.getCoord().toString());
		paramsMap.put("destinations", arrivee.getCoord().toString());
		LOG.info("GoogleWebServiceImpl::getItineraire Appel Google avec les coordonnees "+depart.getCoord().toString()+" et "+arrivee.getCoord().toString());
		Reader result = getHttpRequest(googleMapsAPIUrl + serviceDistance,paramsMap);

		LOG.info("GoogleWebServiceImpl::getItineraire Transformation JSON");
		DistanceGoogleResponse googleResponse = (DistanceGoogleResponse) gson.fromJson(result, DistanceGoogleResponse.class);
		
		return googleResponse;
	}	

	/**
	 * @param googleMapsAPIUrl the googleMapsAPIUrl to set
	 */
	public void setGoogleMapsAPIUrl(String googleMapsAPIUrl) {
		this.googleMapsAPIUrl = googleMapsAPIUrl;
	}


	/**
	 * @param serviceGeocode the serviceGeocode to set
	 */
	public void setServiceGeocode(String serviceGeocode) {
		this.serviceGeocode = serviceGeocode;
	}


	public String getServiceDistance() {
		return serviceDistance;
	}


	public void setServiceDistance(String serviceDistance) {
		this.serviceDistance = serviceDistance;
	}


	
	

}
