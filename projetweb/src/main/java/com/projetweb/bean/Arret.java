package com.projetweb.bean;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Arret {
	@PrimaryKey
	private String id;

	@Persistent
	@SerializedName("nom")
	private String nom;
	
	@Persistent(serialized = "true")
	@SerializedName("coord")
	private Coordonnee coord;


	public Arret(String id, String nom, float coordx, float coordy) {
		this.id = id;
		this.nom = nom;
		this.coord = new Coordonnee(x, y);
	}

	public Key getKey() {
		return key;
	}

	public float getCoordX() {
		return coordX;
	}

	public float getCoordY() {
		return coordY;
	}

	
	public void setCoordX(float coordx) {
		this.coordX = coordx;
	}

	public void setCoordY(float coordy) {
		this.coordY = coordy;
	}

}