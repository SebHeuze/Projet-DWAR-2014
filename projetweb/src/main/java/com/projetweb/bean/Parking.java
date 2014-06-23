package com.projetweb.bean;

public class Parking {

	private Coordonnee coord;
	
	private String nom;
	
	private float cout;

	
	public Parking(){
		
	}
	public Parking(Coordonnee coord, String nom, float cout){
		this.coord = coord;
		this.nom = nom;
		this.cout = cout;
		
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
	
	
}
