package com.projetweb.bean;

import java.util.ArrayList;

import com.projetweb.bean.generated.google.Result;

import java.util.List;

import com.google.gson.annotations.Expose;

public class GeocodeGoogleResponse {

@Expose
private List<Result> results = new ArrayList<Result>();
@Expose
private String status;

public List<Result> getResults() {
return results;
}

public void setResults(List<Result> results) {
this.results = results;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}
