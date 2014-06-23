package com.projetweb.bean;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gson.annotations.Expose;

@PersistenceCapable
public class TrajetBus {

	@PrimaryKey
	@Expose
	private String TrajetId;

	@Expose
	@Persistent
	private String ligne;
	
	@Expose
	@Persistent
	private boolean complet;
	
	@Expose
	@Persistent
	private int nbArrets;

	@Expose
	@Persistent
	private String depart;

	@Expose
	@Persistent
	private String terminus;

	@Expose
	@Persistent(serialized = "true")
	private List<Stop> listeStops;

	/**
	 * @return the trajetId
	 */
	public String getTrajetId() {
		return TrajetId;
	}

	/**
	 * @return the ligne
	 */
	public String getLigne() {
		return ligne;
	}

	/**
	 * @return the depart
	 */
	public String getDepart() {
		return depart;
	}

	/**
	 * @return the terminus
	 */
	public String getTerminus() {
		return terminus;
	}

	/**
	 * @return the listeStops
	 */
	public List<Stop> getListeStops() {
		return listeStops;
	}

	/**
	 * @param trajetId the trajetId to set
	 */
	public void setTrajetId(String trajetId) {
		TrajetId = trajetId;
	}

	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	/**
	 * @param depart the depart to set
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * @param terminus the terminus to set
	 */
	public void setTerminus(String terminus) {
		this.terminus = terminus;
	}

	/**
	 * @param listeStops the listeStops to set
	 */
	public void setListeStops(List<Stop> listeStops) {
		this.listeStops = listeStops;
	}

	/**
	 * @return the complet
	 */
	public boolean isComplet() {
		return complet;
	}

	/**
	 * @param complet the complet to set
	 */
	public void setComplet(boolean complet) {
		this.complet = complet;
	}

	/**
	 * @return the nbArrets
	 */
	public int isNbArrets() {
		return nbArrets;
	}

	/**
	 * @param nbArrets the nbArrets to set
	 */
	public void setNbArrets(int nbArrets) {
		this.nbArrets = nbArrets;
	}

	


}