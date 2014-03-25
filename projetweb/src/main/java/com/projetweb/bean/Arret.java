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
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private float coordX;

	@Persistent
	private float coordY;


	public Arret(float coordx, float coordy) {
		this.coordX = coordx;
		this.coordY = coordy;
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