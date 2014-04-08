package com.projetweb.bean;

public class BusVsVoiture {

	/**
	 * Adresse départ
	 */
	private Adresse adresseDepart;
	
	/**
	 * Adresse Arrivée
	 */
	private Adresse adresseArrivee;
	
	/**
	 * Trajet voiture
	 */
	private Trajet trajetVoiture;

	/**
	 * Trajet bus
	 */
	private Trajet trajetBus;

	
	/**
	 * @param adresseDepart
	 * @param adresseArrivee
	 * @param trajetVoiture
	 * @param trajetBus
	 */
	public BusVsVoiture(Adresse adresseDepart, Adresse adresseArrivee) {
		super();
		this.adresseDepart = adresseDepart;
		this.adresseArrivee = adresseArrivee;
		this.trajetVoiture = new Trajet();
		this.trajetBus = new Trajet();
	}

	/**
	 * @return the adresseDepart
	 */
	public Adresse getAdresseDepart() {
		return adresseDepart;
	}

	/**
	 * @param adresseDepart the adresseDepart to set
	 */
	public void setAdresseDepart(Adresse adresseDepart) {
		this.adresseDepart = adresseDepart;
	}

	/**
	 * @return the adresseArrivee
	 */
	public Adresse getAdresseArrivee() {
		return adresseArrivee;
	}

	/**
	 * @param adresseArrivee the adresseArrivee to set
	 */
	public void setAdresseArrivee(Adresse adresseArrivee) {
		this.adresseArrivee = adresseArrivee;
	}

	/**
	 * @return the trajetVoiture
	 */
	public Trajet getTrajetVoiture() {
		return trajetVoiture;
	}

	/**
	 * @param trajetVoiture the trajetVoiture to set
	 */
	public void setTrajetVoiture(Trajet trajetVoiture) {
		this.trajetVoiture = trajetVoiture;
	}

	/**
	 * @return the trajetBus
	 */
	public Trajet getTrajetBus() {
		return trajetBus;
	}

	/**
	 * @param trajetBus the trajetBus to set
	 */
	public void setTrajetBus(Trajet trajetBus) {
		this.trajetBus = trajetBus;
	}
	
}
