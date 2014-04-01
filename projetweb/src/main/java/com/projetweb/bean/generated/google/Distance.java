package com.projetweb.bean.generated.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Distance {

@Expose
private String text;
@Expose
private Integer value;

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

public Integer getValue() {
return value;
}

public void setValue(Integer value) {
this.value = value;
}

}