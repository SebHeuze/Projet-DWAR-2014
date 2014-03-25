package com.projetweb.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AddressTanResponse {
	
	@SerializedName("typeLieu")
	private String typeLieu;
	
	@SerializedName("lieux")
	private List<Adresse> lieux;
	
	
	
	/**
	 * Constructeur
	 */
	public AddressTanResponse() {
	}
	
	/**
	 * @return the typeLieu
	 */
	public String getTypeLieu() {
		return typeLieu;
	}


	/**
	 * @param typeLieu the typeLieu to set
	 */
	public void setTypeLieu(String typeLieu) {
		this.typeLieu = typeLieu;
	}


	/**
	 * @return the lieux
	 */
	public List<Adresse> getLieux() {
		return lieux;
	}


	/**
	 * @param lieux the lieux to set
	 */
	public void setLieux(List<Adresse> lieux) {
		this.lieux = lieux;
	}

	
	
}
