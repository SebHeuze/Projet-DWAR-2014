package com.projetweb.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
        	HttpClient client = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(tanUrl + serviceAdresse);
			
			// Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("nom", adresse));
	        postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
			HttpResponse response = client.execute(postRequest);
	
	        
	        StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				//Read the server response and attempt to parse it as JSON
				Reader reader = new InputStreamReader(content);
				
				AddressTanResponse[] listeReponse = (AddressTanResponse[]) gson.fromJson(reader, AddressTanResponse[].class);
				AddressTanResponse reponseTan = listeReponse[0];
				
				Adresse tmpAdresse = new Adresse();
				
				tmpAdresse.setNom(reponseTan.getLieux().get(0).getNom());
				
				adressesList.add(tmpAdresse);
			} else {
				System.out.println(statusLine.getStatusCode());
				//TODO: Logs
			}
	        
    	} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
