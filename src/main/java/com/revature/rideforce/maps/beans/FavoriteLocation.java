package com.revature.rideforce.maps.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.maps.model.LatLng;

@Component
@Entity
@Table(name = "FAVORITE_ADDRESSES")
public class FavoriteLocation {
	
	/**
	 * an address string denoted as the id (primary key)
	 */
	@Id
	@Size(max = 85)
	@JsonProperty
	@Column(name = "ADDRESS")
	String address;
	
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
	 * the type of favorite location (ie: home, work, etc)
	 */
	@Column (name="FAVORITED_LOCATION_NAME")
	String name;
	
	/**
	 * the current user's id
	 */
	@Min(1)
	@Column(name = "USER_ID")
	private int userId;

	/**
	 * class constructor (no args)
	 */
	public FavoriteLocation() {
		super();
	}
	
	/**
	 * class constructor
	 * sets this address to 'address'
	 * sets this latitude to 'latlng.lat'
	 * sets this longitude to 'latlng.lng'
	 * @param address
	 * @param latlng
	 * @param userId
	 */
	public FavoriteLocation (@Size(max = 85) String address, LatLng latlng, @Min(1) int userId) {
		this.address = address;
		this.latitude = latlng.lat;
		this.longitude = latlng.lng;
		this.userId = userId;
	}

	/**
	 * class constructor
	 * @param address
	 * @param latitude
	 * @param longitude
	 * @param name
	 * @param userId
	 */
	public FavoriteLocation(@Size(max = 85) String address, double latitude, double longitude, String name,
			@Min(1) int userId) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.userId = userId;
	}

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
	 * get name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get user id
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * set this user id to 'userId'
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * get favorite location
	 * @return latitude and longitude of location
	 */
	public LatLng getFavoriteLocation() {
		return new LatLng(latitude, longitude);	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + userId;
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
		FavoriteLocation other = (FavoriteLocation) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FavoriteLocation [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", name=" + name + ", userId=" + userId + "]";
	}

}
