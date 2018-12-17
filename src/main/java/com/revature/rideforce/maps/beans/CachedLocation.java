package com.revature.rideforce.maps.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.google.maps.model.LatLng;

/**
 * A cached lookup for geocoding information, associating an address string with
 * geographic coordinates.
 */
@Component
@Entity
@Table(name = "ADDRESS")
public class CachedLocation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CACHED_ID")
	Integer id;
	
	/**
	 * An address string denoted as the id
	 */
	@Size(max = 85)
	@Column(name = "ADDRESS")
	String address;
	
	/**
	 * An address string denoted as the id
	 */
	@Size(max = 85)
	@Column(name = "CITY")
	String city;
	
	/**
	 * An address string denoted as the id
	 */
	@Size(max = 2)
	@Column(name = "STATE_CODE")
	String stateCode;
	
	@Column(name = "ZIP_CODE")
	String zip;
	
	/**
	 * geographic coordinate latitude
	 */
	@Column(name = "LATITUDE")
	double latitude;
	
	/**
	 * geographic coordinate longitude
	 */
	@Column(name = "LONGITUDE")
	double longitude;
	
	/**
	 * get address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * set this address to 'address'
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * get latitude
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * set this latitude to 'latitude'
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * get longitude
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * set this longitude to 'longitude'
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Class constructor (no args)
	 */
	public CachedLocation() {
		super();
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latlng.lat'
	 * sets this longitude to 'latlng.lng'
	 * @param address
	 * @param latlng
	 */
	public CachedLocation (String address, String city, String stateAbv, LatLng latlng) {
		this.address = address;
		this.city = city;
		this.stateCode = stateAbv;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
	}

	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latlng.lat'
	 * sets this longitude to 'latlng.lng'
	 * @param address
	 * @param latlng
	 */
	public CachedLocation (String address, String city, LatLng latlng) {
		this.address = address;
		this.city = city;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
	}
	
	public CachedLocation (String address, LatLng latlng) {
		this.address = address;
		this.city = city;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latlng.lat'
	 * sets this longitude to 'latlng.lng'
	 * @param address
	 * @param latlng
	 */
	public CachedLocation (String address, LatLng latlng, String state) {
		this.address = address;
		this.stateCode = state;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latitude'
	 * sets this longitude to 'longitude'
	 * @param address
	 * @param latitude
	 * @param longitude
	 */
	public CachedLocation(String address, String city, String stateAbv, double latitude, double longitude) {
		super();
		this.address = address;
		this.city = city;
		this.stateCode = stateAbv;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latitude'
	 * sets this longitude to 'longitude'
	 * @param address
	 * @param latitude
	 * @param longitude
	 */
	public CachedLocation(String address, String city, double latitude, double longitude) {
		super();
		this.address = address;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latitude'
	 * sets this longitude to 'longitude'
	 * @param address
	 * @param latitude
	 * @param longitude
	 */
	public CachedLocation(String address,double latitude, double longitude) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latlng.lat'
	 * sets this longitude to 'latlng.lng'
	 * @param address
	 * @param latlng
	 */
	public CachedLocation (LatLng latlng, String zip) {
		this.zip = zip;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
	}

	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latitude'
	 * sets this longitude to 'longitude'
	 * @param address
	 * @param latitude
	 * @param longitude
	 */
	public CachedLocation(double latitude, double longitude, String zip) {
		super();
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * get location
	 * @return latitude and longitude of location
	 */
	public LatLng getLocation() {
		return new LatLng(latitude, longitude);	
	}
	
	@Override
	public String toString() {
		return "Location [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
