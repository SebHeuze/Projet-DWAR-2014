package com.projetweb.bean.geocodegoogleresponse;

import com.google.gson.annotations.Expose;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Location {

@Expose
private float lat;
@Expose
private float lng;

public float getLat() {
return lat;
}

public void setLat(float lat) {
this.lat = lat;
}

public float getLng() {
return lng;
}

public void setLng(float lng) {
this.lng = lng;
}

}