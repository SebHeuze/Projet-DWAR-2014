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
	 * Ligne de bus/tram
	 */
	private String ligne;
	
	/**
	 * Voiture, bus, tram...
	 */
	private TravelMode travelMode;
	

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
	public Waypoint(Coordonnee coord, String titre, String info, String ligne, TravelMode travelMode){
		super(coord.getLatitude(), coord.getLongitude());
		this.info = info;
		this.titre = titre;
		this.travelMode = travelMode;
		this.ligne = ligne;
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

	/**
	 * @return the travelMode
	 */
	public TravelMode getTravelMode() {
		return travelMode;
	}

	/**
	 * @param travelMode the travelMode to set
	 */
	public void setTravelMode(TravelMode travelMode) {
		this.travelMode = travelMode;
	}
	
	/**
	 * @return the ligne
	 */
	public String getLigne() {
		return ligne;
	}

	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}


}
