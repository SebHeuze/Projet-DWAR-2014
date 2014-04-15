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
	private float longitude;
	
	/**
	 * Latitude : Y
	 */
	@SerializedName("lat")
	private float latitude;

	public Coordonnee(){
	}
	
	public Coordonnee(float latitude,float longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString(){
		return latitude + "," + longitude;
	}
	
}
