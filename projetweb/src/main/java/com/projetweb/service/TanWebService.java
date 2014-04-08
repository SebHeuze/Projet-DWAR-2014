package com.projetweb.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.ItineraireTanResponse;

@Service
public interface TanWebService {
	
	
	/**
	 * Trouve la liste des adresses connues ainsi que les références connues par la TAN
	 * @param adresse
	 * @return List<Adresse> la liste des adresses connues
	 */
	public List<Adresse> findAdresses(String adresse);

	/**
	 * Trouve l'itinéraire TAN
	 * @param adresseDepart
	 * @param adresseArrivee
	 * @return 
	 */
	public ItineraireTanResponse[] itineraire(Adresse adresseDepart, Adresse adresseArrivee, Date dateItineraire);
	
	/**
	 * Calcul du prix du trajet Bus
	 * @param dateDepart
	 * @param dateRetour
	 * @param aboTan
	 * @return
	 */
	public float calculCoutTrajet(Date dateDepart, Date dateRetour, boolean aboTan);
}
