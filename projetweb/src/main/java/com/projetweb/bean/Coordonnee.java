/**
 * 
 */
package com.projetweb.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author SEB
 * Classe pour stocker des coordonnées
 */
public class Coordonnee implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Longitude :X
	 */
	@SerializedName("lng")
	private double longitude;
	
	/**
	 * Latitude : Y
	 */
	@SerializedName("lat")
	private double latitude;

	public Coordonnee(){
	}
	
	public Coordonnee(double latitude,double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString(){
		return latitude + "," + longitude;
	}
	
}
