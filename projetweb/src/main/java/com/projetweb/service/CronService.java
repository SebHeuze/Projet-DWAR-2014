package com.projetweb.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;

@Service
public interface CronService {
	
	
	/**
	 * Cron qui se charge de mettre à jour les parkings
	 * @return retour batch
	 */
	boolean updateParkings();

	
}
