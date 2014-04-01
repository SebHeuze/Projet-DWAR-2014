package com.projetweb.bean;

import java.util.ArrayList;
import java.util.List;



import com.google.gson.annotations.Expose;
import com.projetweb.bean.generated.tan.ArretDepart;
import com.projetweb.bean.generated.tan.Etape;

public class ItineraireTanResponse {

@Expose
private String adresseDepart;
@Expose
private String adresseArrivee;
@Expose
private String heureDepart;
@Expose
private String heureArrivee;
@Expose
private String jourDepart;
@Expose
private String jourArrivee;
@Expose
private String duree;
@Expose
private Integer correspondance;
@Expose
private ArretDepart arretDepart;
@Expose
private List<Etape> etapes = new ArrayList<Etape>();
@Expose
private String chaineDepart;
@Expose
private String chaineArrivee;
@Expose
private String favoriDepart;
@Expose
private String favoriDepartVille;
@Expose
private String favoriDepartCP;
@Expose
private String favoriArrivee;
@Expose
private String favoriArriveeVille;
@Expose
private String favoriArriveeCP;
@Expose
private Boolean accessible;

public String getAdresseDepart() {
return adresseDepart;
}

public void setAdresseDepart(String adresseDepart) {
this.adresseDepart = adresseDepart;
}

public String getAdresseArrivee() {
return adresseArrivee;
}

public void setAdresseArrivee(String adresseArrivee) {
this.adresseArrivee = adresseArrivee;
}

public String getHeureDepart() {
return heureDepart;
}

public void setHeureDepart(String heureDepart) {
this.heureDepart = heureDepart;
}

public String getHeureArrivee() {
return heureArrivee;
}

public void setHeureArrivee(String heureArrivee) {
this.heureArrivee = heureArrivee;
}

public String getJourDepart() {
return jourDepart;
}

public void setJourDepart(String jourDepart) {
this.jourDepart = jourDepart;
}

public String getJourArrivee() {
return jourArrivee;
}

public void setJourArrivee(String jourArrivee) {
this.jourArrivee = jourArrivee;
}

public String getDuree() {
return duree;
}

public void setDuree(String duree) {
this.duree = duree;
}

public Integer getCorrespondance() {
return correspondance;
}

public void setCorrespondance(Integer correspondance) {
this.correspondance = correspondance;
}

public ArretDepart getArretDepart() {
return arretDepart;
}

public void setArretDepart(ArretDepart arretDepart) {
this.arretDepart = arretDepart;
}

public List<Etape> getEtapes() {
return etapes;
}

public void setEtapes(List<Etape> etapes) {
this.etapes = etapes;
}

public String getChaineDepart() {
return chaineDepart;
}

public void setChaineDepart(String chaineDepart) {
this.chaineDepart = chaineDepart;
}

public String getChaineArrivee() {
return chaineArrivee;
}

public void setChaineArrivee(String chaineArrivee) {
this.chaineArrivee = chaineArrivee;
}

public String getFavoriDepart() {
return favoriDepart;
}

public void setFavoriDepart(String favoriDepart) {
this.favoriDepart = favoriDepart;
}

public String getFavoriDepartVille() {
return favoriDepartVille;
}

public void setFavoriDepartVille(String favoriDepartVille) {
this.favoriDepartVille = favoriDepartVille;
}

public String getFavoriDepartCP() {
return favoriDepartCP;
}

public void setFavoriDepartCP(String favoriDepartCP) {
this.favoriDepartCP = favoriDepartCP;
}

public String getFavoriArrivee() {
return favoriArrivee;
}

public void setFavoriArrivee(String favoriArrivee) {
this.favoriArrivee = favoriArrivee;
}

public String getFavoriArriveeVille() {
return favoriArriveeVille;
}

public void setFavoriArriveeVille(String favoriArriveeVille) {
this.favoriArriveeVille = favoriArriveeVille;
}

public String getFavoriArriveeCP() {
return favoriArriveeCP;
}

public void setFavoriArriveeCP(String favoriArriveeCP) {
this.favoriArriveeCP = favoriArriveeCP;
}

public Boolean getAccessible() {
return accessible;
}

public void setAccessible(Boolean accessible) {
this.accessible = accessible;
}

}