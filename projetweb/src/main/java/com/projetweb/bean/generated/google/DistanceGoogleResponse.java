package com.projetweb.bean.generated.google;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class DistanceGoogleResponse {

@Expose
private List<String> destination_addresses = new ArrayList<String>();
@Expose
private List<String> origin_addresses = new ArrayList<String>();
@Expose
private List<Row> rows = new ArrayList<Row>();
@Expose
private String status;

public List<String> getDestination_addresses() {
return destination_addresses;
}

public void setDestination_addresses(List<String> destination_addresses) {
this.destination_addresses = destination_addresses;
}

public List<String> getOrigin_addresses() {
return origin_addresses;
}

public void setOrigin_addresses(List<String> origin_addresses) {
this.origin_addresses = origin_addresses;
}

public List<Row> getRows() {
return rows;
}

public void setRows(List<Row> rows) {
this.rows = rows;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}