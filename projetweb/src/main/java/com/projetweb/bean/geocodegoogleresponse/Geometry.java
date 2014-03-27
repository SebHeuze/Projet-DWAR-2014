package com.projetweb.bean.geocodegoogleresponse;

import com.google.gson.annotations.Expose;
import com.projetweb.bean.Coordonnee;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Geometry {

@Expose
private Bounds bounds;
@Expose
private Coordonnee location;
@Expose
private String location_type;
@Expose
private Viewport viewport;

public Bounds getBounds() {
return bounds;
}

public void setBounds(Bounds bounds) {
this.bounds = bounds;
}

public Coordonnee getLocation() {
return location;
}

public void setLocation(Coordonnee location) {
this.location = location;
}

public String getLocation_type() {
return location_type;
}

public void setLocation_type(String location_type) {
this.location_type = location_type;
}

public Viewport getViewport() {
return viewport;
}

public void setViewport(Viewport viewport) {
this.viewport = viewport;
}

}