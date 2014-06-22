package com.projetweb.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@PersistenceCapable
public class Favoris {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@SerializedName("id")
	private Long id;
	
	@Persistent
	private String user;
	
	@Persistent
	private String depart;
	
	@Persistent
	private String arrivee;
	
	/**
	 * Constructeur
	 * @param depart
	 * @param arrivee
	 * @param user l'adresse mail de l'utilisateur
	 */
	public Favoris(String depart, String arrivee, String user){
		this.user= user;
		this.depart = depart;
		this.arrivee = arrivee;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the depart
	 */
	public String getDepart() {
		return depart;
	}

	/**
	 * @param depart the depart to set
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * @return the arrivee
	 */
	public String getArrivee() {
		return arrivee;
	}

	/**
	 * @param arrivee the arrivee to set
	 */
	public void setArrivee(String arrivee) {
		this.arrivee = arrivee;
	}
	
	/**
	 * To string (pour le JSLT)
	 */
	public String toString(){
		String splitDepart[] = this.getDepart().split("\\|");
		String splitArrivee[] = this.getArrivee().split("\\|");
		return splitDepart[5] + " " +splitDepart[2] + " => " + splitArrivee[5] + " "+ splitArrivee[2];
	}
}