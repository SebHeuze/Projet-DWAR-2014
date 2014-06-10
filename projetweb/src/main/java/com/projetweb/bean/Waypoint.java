/**
 * 
 */
package com.projetweb.bean;

/**
 * @author SEB
 *
 */
public class Waypoint extends Coordonnee {

	/**
	 * Serialisation
	 */
	private static final long serialVersionUID = -8953080812782023773L;
	
	/**
	 * Titre
	 */
	private String titre;
	
	/**
	 * Informations sur le Waypoint 
	 */
	private String info;
	
	/**
	 * Constructeur
	 * @param coord
	 */
	public Waypoint(Coordonnee coord){
		super(coord.getLatitude(), coord.getLongitude());
	}
	
	/**
	 * COnstructeur
	 * @param latitude
	 * @param longitude
	 * @param title
	 * @param info
	 */
	public Waypoint(Coordonnee coord, String titre, String info){
		super(coord.getLatitude(), coord.getLongitude());
		this.info = info;
		this.titre = titre;
	}


	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}


	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}


	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}


	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}
