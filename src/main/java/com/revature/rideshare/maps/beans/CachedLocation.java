package com.revature.rideshare.maps.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.google.maps.model.LatLng;

/**
 * A cached lookup for geocoding information, associating an address string with
 * geographic coordinates.
 */
@Component
@Entity
@Table(name = "")
public class CachedLocation {
	@Id
	@Column(name = "ADDRESS")
	String address;
	@Column(name = "LATITUDE")
	double latitude;
	@Column(name = "LONGITUDE")
	double longitude;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Location [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	public CachedLocation(String address, double latitude, double longitude) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public CachedLocation (String address, LatLng latlng) {
		this.address = address;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
	}
	
	public CachedLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
