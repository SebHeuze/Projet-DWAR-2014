package com.projetweb.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.BusVsVoiture;

@Service
public interface AdminService {
	
	
	/**
	 * initialiser la base de donnée
	 */
	void initBDD();


}
