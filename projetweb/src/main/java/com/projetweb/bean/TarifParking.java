package com.projetweb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class TarifParking implements Comparable<TarifParking>{

	@Expose
	private Integer categorie;
	@Expose
	private String concerne;
	@Expose
	private List<Tarif> tarifs = new ArrayList<Tarif>();
	@Expose
	private Integer jour_debut;
	@Expose
	private Integer jour_fin;
	@Expose
	private Integer heure_debut;
	@Expose
	private Double heure_fin;

	public Integer getCategorie() {
		return categorie;
	}

	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

	public String getConcerne() {
		return concerne;
	}

	public void setConcerne(String concerne) {
		this.concerne = concerne;
	}

	public List<Tarif> getTarifs() {
		return tarifs;
	}

	public void setTarifs(List<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

	public Integer getJour_debut() {
		return jour_debut;
	}

	public void setJour_debut(Integer jour_debut) {
		this.jour_debut = jour_debut;
	}

	public Integer getJour_fin() {
		return jour_fin;
	}

	public void setJour_fin(Integer jour_fin) {
		this.jour_fin = jour_fin;
	}

	public Integer getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(Integer heure_debut) {
		this.heure_debut = heure_debut;
	}

	public Double getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(Double heure_fin) {
		this.heure_fin = heure_fin;
	}

	@Override
	public int compareTo(TarifParking o) {
		return (int)((heure_debut + jour_debut*100) - (o.heure_debut + o.jour_debut*100));
	}

}