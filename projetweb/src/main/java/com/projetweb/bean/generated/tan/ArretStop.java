package com.projetweb.bean.generated.tan;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class ArretStop {

@Expose
private String libelle;
@Expose
private Boolean accessible;
@Expose
private Boolean danger;

public String getLibelle() {
return libelle;
}

public void setLibelle(String libelle) {
this.libelle = libelle;
}

public Boolean getAccessible() {
return accessible;
}

public void setAccessible(Boolean accessible) {
this.accessible = accessible;
}

public Boolean getDanger() {
return danger;
}

public void setDanger(Boolean danger) {
this.danger = danger;
}

}