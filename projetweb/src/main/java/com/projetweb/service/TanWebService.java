package com.projetweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;

@Service
public interface TanWebService {
	
	
	/**
	 * Trouve la liste des adresses connues ainsi que les références connues par la TAN
	 * @param adresse
	 * @return List<Adresse> la liste des adresses connues
	 */
	public List<Adresse> findAdresses(String adresse);
	
}
