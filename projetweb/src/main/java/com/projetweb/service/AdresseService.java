package com.projetweb.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetweb.bean.Adresse;
import com.projetweb.bean.BusVsVoiture;

@Service
public interface AdresseService {
	
	
	/**
	 * Trouve les adresses connues par la TAN ainsi que les coordonnées associées
	 * @param adresse
	 * @return Coordonnee les coordonnées
	 */
	List<Adresse> findAdressesWithCoord(String adresse);

	/**
	 * Trouve les itinéraires Bus et Voiture
	 * @param idAdresseDepart L'id TAN de l'adresse de départ
	 * @param idAdresseArrivee L'id TAN de l'adresse d'arrivee
	 * @param consommationVoiture 
	 * @param coutCarburant 
	 * @return Informations Itinéraire
	 */
	BusVsVoiture findItineraire(String idAdresseDepart,String idAdresseArrivee, Date dateDepart, Date dateRetour, String typeVoiture, String carburant, boolean abonnementTan);
	
}
