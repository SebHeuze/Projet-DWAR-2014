package com.projetweb.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class ListeEquipementsAPIReponse {

@Expose
private String version;
@Expose
private Integer nb_results;
@Expose
private List<Equipement> data = new ArrayList<Equipement>();

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public Integer getNb_results() {
return nb_results;
}

public void setNb_results(Integer nb_results) {
this.nb_results = nb_results;
}

public List<Equipement> getData() {
return data;
}

public void setData(List<Equipement> data) {
this.data = data;
}

}