package com.projetweb.bean.generated.tan;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Etape {

@Expose
private Boolean marche;
@Expose
private ArretStop arretStop;
@Expose
private String heureDepart;
@Expose
private String heureArrivee;
@Expose
private String duree;
@Expose
private Ligne ligne;

public Boolean getMarche() {
return marche;
}

public void setMarche(Boolean marche) {
this.marche = marche;
}

public ArretStop getArretStop() {
return arretStop;
}

public void setArretStop(ArretStop arretStop) {
this.arretStop = arretStop;
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

public String getDuree() {
return duree;
}

public void setDuree(String duree) {
this.duree = duree;
}

public Ligne getLigne() {
return ligne;
}

public void setLigne(Ligne ligne) {
this.ligne = ligne;
}

}