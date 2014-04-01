package com.projetweb.bean.generated.google;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Row {

@Expose
private List<Element> elements = new ArrayList<Element>();

public List<Element> getElements() {
return elements;
}

public void setElements(List<Element> elements) {
this.elements = elements;
}

}