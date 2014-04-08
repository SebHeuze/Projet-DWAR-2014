/**
 * 
 */
package com.projetweb.service.impl;

import static com.projetweb.helper.HttpRequestHelper.getHttpRequest;

import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.projetweb.bean.GeocodeGoogleResponse;
import com.projetweb.bean.ListeEquipementsAPIReponse;
import com.projetweb.service.CronService;

/**
 * @author SEB
 *
 */
public class CronServiceImpl implements CronService {

	/**
	 * URL Open Data Equipements
	 */
	private String urlEquipementMobilite;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(CronServiceImpl.class.getName());
	
	@Override
	public boolean updateParkings() {
		Gson gson = new Gson();
		
		LOG.info("CronServiceImpl::updateParkings Requete API Equipements");
		Reader result = getHttpRequest(urlEquipementMobilite, new HashMap<String,String>());
		
		LOG.info("CronServiceImpl::updateParkings Transformation JSON");
		ListeEquipementsAPIReponse equipementAPIReponse = (ListeEquipementsAPIReponse) gson.fromJson(result, ListeEquipementsAPIReponse.class);
		
		return false;
	}

	/**
	 * @return the urlEquipementMobilite
	 */
	public String getUrlEquipementMobilite() {
		return urlEquipementMobilite;
	}

	/**
	 * @param urlEquipementMobilite the urlEquipementMobilite to set
	 */
	public void setUrlEquipementMobilite(String urlEquipementMobilite) {
		this.urlEquipementMobilite = urlEquipementMobilite;
	}

}
