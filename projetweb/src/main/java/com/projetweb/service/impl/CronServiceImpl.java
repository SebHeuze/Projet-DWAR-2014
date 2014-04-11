/**
 * 
 */
package com.projetweb.service.impl;

import static com.projetweb.helper.HttpRequestHelper.getHttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.context.ServletContextAware;

import com.google.gson.Gson;
import com.projetweb.bean.Equipement;
import com.projetweb.bean.GeocodeGoogleResponse;
import com.projetweb.bean.ListeEquipementsAPIReponse;
import com.projetweb.bean.TarifParking;
import com.projetweb.dao.AdresseDAO;
import com.projetweb.dao.EquipementDAO;
import com.projetweb.dao.TarifParkingDAO;
import com.projetweb.helper.UtilsHelper;
import com.projetweb.service.CronService;

/**
 * @author SEB
 *
 */
public class CronServiceImpl implements CronService,  ServletContextAware {

	/**
	 * URL Open Data Equipements
	 */
	private String urlEquipementMobilite;
	
	/**
	 * PATH tarifsparking.json
	 */
	private String pathTarifParkingFile;
	
	/**
	 * Contexte du servlet
	 */
	private @Autowired ServletContext servletContext;
	
	/**
	 * Gestionnaire TarifParking en base
	 */
	@Autowired
	private TarifParkingDAO tarifParkingDAO;
	
	/**
	 * Gestionnaire Equipement en base
	 */
	@Autowired
	private EquipementDAO equipementDAO;
	
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
		for (Equipement tmpEqui : equipementAPIReponse.getData()){
			tmpEqui.update();
		}
		
		LOG.info("CronServiceImpl::updateParkings Stockage BDD Equipements");
		equipementDAO.deleteAll();
		equipementDAO.storeAll(equipementAPIReponse.getData());
		
		LOG.info("CronServiceImpl::updateParkings Chargement des tarifs");
		String resultTarifs = "";
		InputStream input = servletContext.getResourceAsStream(pathTarifParkingFile);
		resultTarifs = UtilsHelper.getStringFromInputStream(input);
		
		LOG.info("CronServiceImpl::updateParkings Transformation JSON");
		TarifParking[] tarifParking = (TarifParking[]) gson.fromJson(resultTarifs, TarifParking[].class);
		
		LOG.info("CronServiceImpl::updateParkings Stockage BDD Tarifs Parking");
		tarifParkingDAO.deleteAll();
		tarifParkingDAO.storeAll(tarifParking);
		
		
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

	/**
	 * @param pathTarifParkingFile the pathTarifParkingFile to set
	 */
	public void setPathTarifParkingFile(String pathTarifParkingFile) {
		this.pathTarifParkingFile = pathTarifParkingFile;
	}

	@Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }

}
