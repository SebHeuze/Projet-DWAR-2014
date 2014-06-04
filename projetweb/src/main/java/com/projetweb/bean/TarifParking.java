package com.projetweb.bean;

import javax.annotation.Generated;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
@PersistenceCapable
public class TarifParking implements Comparable<TarifParking>{

@PrimaryKey
@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
@SerializedName("id")
private Long id;

@Expose
@Persistent
private Integer categorie;

@Expose
@Persistent
private String concerne;

@Expose
@Persistent
private Double temps;

@Expose
@Persistent
private Double prix;

@Expose
@Persistent
private Integer jour_debut;

@Expose
@Persistent
private Integer jour_fin;

@Expose
@Persistent
private Double heure_debut;

@Expose
@Persistent
private Double heure_fin;

@Expose
@Persistent
private String type;

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

public Double getHeure_debut() {
return heure_debut;
}

public void setHeure_debut(Double heure_debut) {
this.heure_debut = heure_debut;
}

public Double getHeure_fin() {
return heure_fin;
}

public void setHeure_fin(Double heure_fin) {
this.heure_fin = heure_fin;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

@Override
public int compareTo(TarifParking o) {
	return (int)((heure_debut + jour_debut*100) - (o.heure_debut + o.jour_debut*100));
}

}

