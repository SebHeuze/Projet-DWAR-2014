package com.projetweb.bean.generated;

import com.google.gson.annotations.Expose;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Bounds {

@Expose
private Northeast northeast;
@Expose
private Southwest southwest;

public Northeast getNortheast() {
return northeast;
}

public void setNortheast(Northeast northeast) {
this.northeast = northeast;
}

public Southwest getSouthwest() {
return southwest;
}

public void setSouthwest(Southwest southwest) {
this.southwest = southwest;
}

}