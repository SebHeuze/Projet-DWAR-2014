package com.projetweb.bean;

import javax.annotation.Generated;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.datanucleus.jpa.annotations.Extension;

import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
@PersistenceCapable
public class Stop implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1707409217819533702L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String stop_id;

	@Expose
	@Persistent
	private String stop_name;

	@Expose
	@Persistent
	private String stop_desc;

	@Expose
	@Persistent
	private Double stop_lat;

	@Expose
	@Persistent
	private Double stop_lon;

	@Expose
	@Persistent
	private String zone_id;

	@Expose
	@Persistent
	private String stop_url;

	@Expose
	@Persistent
	private String location_type;

	@Expose
	@Persistent
	private String parent_station;

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public String getStop_name() {
		return stop_name;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public String getStop_desc() {
		return stop_desc;
	}

	public void setStop_desc(String stop_desc) {
		this.stop_desc = stop_desc;
	}

	public Double getStop_lat() {
		return stop_lat;
	}

	public void setStop_lat(Double stop_lat) {
		this.stop_lat = stop_lat;
	}

	public Double getStop_lon() {
		return stop_lon;
	}

	public void setStop_lon(Double stop_lon) {
		this.stop_lon = stop_lon;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}

	public String getStop_url() {
		return stop_url;
	}

	public void setStop_url(String stop_url) {
		this.stop_url = stop_url;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getParent_station() {
		return parent_station;
	}

	public void setParent_station(String parent_station) {
		this.parent_station = parent_station;
	}
	

}