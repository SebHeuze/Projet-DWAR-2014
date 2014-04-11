package com.projetweb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@PersistenceCapable
public class Equipement {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@SerializedName("id")
	private Long id;
	
	@Expose
	@SerializedName("geo")
	private Geo geo;
	
	@Persistent
	private String name;
	
	@Expose
	@SerializedName("_l")
	private List<Double> latLng = new ArrayList<Double>();

	@Persistent(serialized = "true")
	private Coordonnee coordonnees;
	
	@SerializedName("LIBTHEME")
	@Persistent
	@Expose
	private String libTheme;
	
	@SerializedName("CODE_POSTAL")
	@Persistent
	@Expose
	private Integer codePostal;
	
	@SerializedName("WEB")
	@Persistent
	@Expose
	private String web;
	
	@SerializedName("STATUT")
	@Expose
	private Object statut;
	
	@SerializedName("TELEPHONE")
	@Persistent
	@Expose
	private String telephone;
	
	@SerializedName("THEME")
	@Persistent
	@Expose
	private Integer theme;
	
	@SerializedName("COMMUNE")
	@Persistent
	@Expose
	private String commune;
	
	@SerializedName("LIBCATEGORIE")
	@Persistent
	@Expose
	private String libCategorie;
	
	@SerializedName("ADRESSE")
	@Persistent
	@Expose
	private String adresse;
	
	@SerializedName("LIBTYPE")
	@Persistent
	@Expose
	private String libType;
	
	@Expose
	@SerializedName("_IDOBJ")
	@Persistent
	private Integer idobj;
	
	@SerializedName("TYPE")
	@Persistent
	@Expose
	private Integer type;
	
	@SerializedName("CATEGORIE")
	@Persistent
	@Expose
	private Integer categorie;
	
	
	public Equipement(){
		
	}

	public void update() {
		this.name = geo.getName();
		this.coordonnees = new Coordonnee(latLng.get(0).floatValue(),latLng.get(1).floatValue());
	}
	
	
	/**
	 * @return the name
	 */
	public Geo getGeo() {
		return geo;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setGeo(Geo geo) {
		this.geo = geo;
		this.name = geo.getName();
	}
	
	/**
	 * @return the latLng
	 */
	public List<Double> getLatLng() {
		return latLng;
	}
	
	/**
	 * @param latLng the latLng to set
	 */
	public void setLatLng(List<Double> latLng) {
		this.latLng = latLng;
		this.coordonnees = new Coordonnee(latLng.get(0).floatValue(),latLng.get(1).floatValue());
	}
	
	/**
	 * @return the coordonnees
	 */
	public Coordonnee getCoordonnees() {
		return coordonnees;
	}
	
	/**
	 * @param coordonnees the coordonnees to set
	 */
	public void setCoordonnees(Coordonnee coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	/**
	 * @return the libTheme
	 */
	public String getLibTheme() {
		return libTheme;
	}
	
	/**
	 * @param libTheme the libTheme to set
	 */
	public void setLibTheme(String libTheme) {
		this.libTheme = libTheme;
	}
	
	/**
	 * @return the codePostal
	 */
	public Integer getCodePostal() {
		return codePostal;
	}
	
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}
	
	/**
	 * @param web the web to set
	 */
	public void setWeb(String web) {
		this.web = web;
	}
	
	/**
	 * @return the statut
	 */
	public Object getStatut() {
		return statut;
	}
	
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Object statut) {
		this.statut = statut;
	}
	
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @return the theme
	 */
	public Integer getTheme() {
		return theme;
	}
	
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(Integer theme) {
		this.theme = theme;
	}
	
	/**
	 * @return the commune
	 */
	public String getCommune() {
		return commune;
	}
	
	/**
	 * @param commune the commune to set
	 */
	public void setCommune(String commune) {
		this.commune = commune;
	}
	
	/**
	 * @return the libCategorie
	 */
	public String getLibCategorie() {
		return libCategorie;
	}
	
	/**
	 * @param libCategorie the libCategorie to set
	 */
	public void setLibCategorie(String libCategorie) {
		this.libCategorie = libCategorie;
	}
	
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * @return the libType
	 */
	public String getLibType() {
		return libType;
	}
	
	/**
	 * @param libType the libType to set
	 */
	public void setLibType(String libType) {
		this.libType = libType;
	}
	
	/**
	 * @return the idobj
	 */
	public Integer getIdobj() {
		return idobj;
	}
	
	/**
	 * @param idobj the idobj to set
	 */
	public void setIdobj(Integer idobj) {
		this.idobj = idobj;
	}
	
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return the categorie
	 */
	public Integer getCategorie() {
		return categorie;
	}
	
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

}