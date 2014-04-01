package com.projetweb.bean.generated.tan;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Ligne {

@Expose
private String numLigne;
@Expose
private Integer typeLigne;
@Expose
private String terminus;

public String getNumLigne() {
return numLigne;
}

public void setNumLigne(String numLigne) {
this.numLigne = numLigne;
}

public Integer getTypeLigne() {
return typeLigne;
}

public void setTypeLigne(Integer typeLigne) {
this.typeLigne = typeLigne;
}

public String getTerminus() {
return terminus;
}

public void setTerminus(String terminus) {
this.terminus = terminus;
}

}