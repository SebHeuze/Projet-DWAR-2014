package com.projetweb.bean;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Shape {

@Expose
private String shape_id;
@Expose
private Double shape_pt_lat;
@Expose
private Double shape_pt_lon;
@Expose
private Integer shape_pt_sequence;

public String getShape_id() {
return shape_id;
}

public void setShape_id(String shape_id) {
this.shape_id = shape_id;
}

public Double getShape_pt_lat() {
return shape_pt_lat;
}

public void setShape_pt_lat(Double shape_pt_lat) {
this.shape_pt_lat = shape_pt_lat;
}

public Double getShape_pt_lon() {
return shape_pt_lon;
}

public void setShape_pt_lon(Double shape_pt_lon) {
this.shape_pt_lon = shape_pt_lon;
}

public Integer getShape_pt_sequence() {
return shape_pt_sequence;
}

public void setShape_pt_sequence(Integer shape_pt_sequence) {
this.shape_pt_sequence = shape_pt_sequence;
}

}