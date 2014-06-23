package com.projetweb.bean;

import java.util.List;

public class Trajet {

	/**
	 * Cout trajet en Euros
	 */
	private float cout;
	
	/**
	 * le Parking pour la voiture
	 */
	private Parking parking;
	
	/**
	 * distance trajet en mètres à l'aller
	 */
	private float distanceAller;
	
	/**
	 * distance trajet en mètres au retour
	 */
	private float distanceRetour;
	
	/**
	 * Temps en minute pour faire le trajet aller
	 */
	private int tempsAller;
	
	/**
	 * Temps en minute pour faire le trajet retour
	 */
	private int tempsRetour;

	/**
	 * Liste de coordonnées
	 */
	private List<Waypoint> listeWaypoints;
	
	/**
	 * @return the cout
	 */
	public float getCout() {
		return cout;
	}

	/**
	 * @param cout the cout to set
	 */
	public void setCout(float cout) {
		this.cout = cout;
	}

	/**
	 * @return the distanceAller
	 */
	public float getDistanceAller() {
		return distanceAller;
	}

	/**
	 * @param distanceAller the distanceAller to set
	 */
	public void setDistanceAller(float distanceAller) {
		this.distanceAller = distanceAller;
	}

	/**
	 * @return the distanceRetour
	 */
	public float getDistanceRetour() {
		return distanceRetour;
	}

	/**
	 * @param distanceRetour the distanceRetour to set
	 */
	public void setDistanceRetour(float distanceRetour) {
		this.distanceRetour = distanceRetour;
	}

	/**
	 * @return the tempsAller
	 */
	public int getTempsAller() {
		return tempsAller;
	}

	/**
	 * @param tempsAller the tempsAller to set
	 */
	public void setTempsAller(int tempsAller) {
		this.tempsAller = tempsAller;
	}

	/**
	 * @return the tempsRetour
	 */
	public int getTempsRetour() {
		return tempsRetour;
	}

	/**
	 * @param tempsRetour the tempsRetour to set
	 */
	public void setTempsRetour(int tempsRetour) {
		this.tempsRetour = tempsRetour;
	}

	/**
	 * @return the listeWaypoints
	 */
	public List<Waypoint> getListeWaypoints() {
		return listeWaypoints;
	}

	/**
	 * @param listeWaypoints the listeWaypoints to set
	 */
	public void setListeWaypoints(List<Waypoint> listeWaypoints) {
		this.listeWaypoints = listeWaypoints;
	}

	/**
	 * @return the parking
	 */
	public Parking getParking() {
		return parking;
	}

	/**
	 * @param parking the parking to set
	 */
	public void setParking(Parking parking) {
		this.parking = parking;
	}

	
}
