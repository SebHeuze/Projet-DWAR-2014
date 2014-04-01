package com.projetweb.bean.generated.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Element {

@Expose
private Distance distance;
@Expose
private Duration duration;
@Expose
private String status;

public Distance getDistance() {
return distance;
}

public void setDistance(Distance distance) {
this.distance = distance;
}

public Duration getDuration() {
return duration;
}

public void setDuration(Duration duration) {
this.duration = duration;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}