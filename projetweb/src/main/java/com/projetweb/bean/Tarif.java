package com.projetweb.bean;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Tarif {

	@Expose
	private Double temps;
	@Expose
	private Double prix;
	@Expose
	private String type;

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}