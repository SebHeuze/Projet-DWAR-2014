package com.projetweb.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.users.User;
import com.projetweb.bean.Adresse;
import com.projetweb.bean.BusVsVoiture;
import com.projetweb.bean.Favoris;

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
	BusVsVoiture findItineraire(String idAdresseDepart,String idAdresseArrivee, Date dateDepart, Date dateRetour, String carburant, boolean abonnementTan);

	/**
	 * Ajoute un favoris
	 * @param depart
	 * @param arrivee
	 * @param user l'adresse mail de l'utilisateur
	 * @return le resultat (True si ça a marché, false sinon)
	 */
	boolean setFavoris(String depart, String arrivee, String user);

	/**
	 * récupérer tous les favoris d'un utilisateur
	 * @param user
	 * @return
	 */
	List<Favoris> getAllFavoris(User user);
	
}
