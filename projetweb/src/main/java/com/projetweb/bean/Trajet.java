package com.projetweb.bean;

public class Trajet {

	/**
	 * Cout trajet en Euros
	 */
	private float cout;
	
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

	
}
