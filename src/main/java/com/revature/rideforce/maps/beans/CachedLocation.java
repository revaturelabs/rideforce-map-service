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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CachedLocation other = (CachedLocation) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (stateCode == null) {
			if (other.stateCode != null)
				return false;
		} else if (!stateCode.equals(other.stateCode))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CachedLocation [id=" + id + ", address=" + address + ", city=" + city + ", stateCode=" + stateCode
				+ ", zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
