package com.projetweb.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@PersistenceCapable
public class Adresse {
	
	
	private Coordonnee coord;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@SerializedName("id")
	private String id;
	
	@Persistent
	@SerializedName("nom")
	private String nom;
	
	@Persistent
	@SerializedName("cp")
	private int cp;
	
	@Persistent
	@SerializedName("ville")
	private String ville;
	
	
	public Adresse() {
	}

	/**
	 * ToString
	 */
	public String toString(){
		return nom + " " + cp + " " + ville;
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


	/**
	 * @return the coord
	 */
	public Coordonnee getCoord() {
		return coord;
	}


	/**
	 * @param coord the coord to set
	 */
	public void setCoord(Coordonnee coord) {
		this.coord = coord;
	}

	
}