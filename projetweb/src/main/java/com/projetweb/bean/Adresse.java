package com.projetweb.bean;

import com.google.gson.annotations.SerializedName;


public class Adresse {
	
	
	private float coordX;
	private float coordY;
	
	@SerializedName("id")
	private String id;
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("cp")
	private int cp;
	
	@SerializedName("ville")
	private String ville;
	
	
	public Adresse() {
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the coordX
	 */
	public float getCoordX() {
		return coordX;
	}


	/**
	 * @param coordX the coordX to set
	 */
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}


	/**
	 * @return the coordY
	 */
	public float getCoordY() {
		return coordY;
	}


	/**
	 * @param coordY the coordY to set
	 */
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the cp
	 */
	public int getCp() {
		return cp;
	}


	/**
	 * @param cp the cp to set
	 */
	public void setCp(int cp) {
		this.cp = cp;
	}


	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}


	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	
}