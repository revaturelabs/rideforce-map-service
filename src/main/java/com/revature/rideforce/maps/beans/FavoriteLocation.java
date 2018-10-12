package com.revature.rideforce.maps.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

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
	@Column(name = "ADDRESS")
	String address;
	
	@Column(name = "LATITUDE")
	double latitude;
	
	@Column(name = "LONGITUDE")
	double longitude;
	
	@Column(name = "FAVORITED_LOCATION_NAME")
	String name;
	
	@Min(1)
	@Column
	private int userId;

	/**
	 * 
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
	 * @param address
	 * @param latitude
	 * @param longitude
	 * @param name
	 * @param userId
	 */
	public FavoriteLocation(@Size(max = 85) @NotNull String address, double latitude, double longitude, String name,
			@Min(1) int userId) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.userId = userId;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		return "FavoritedLocation [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", name=" + name + ", userId=" + userId + "]";
	}

	/**
	 * get favorite location
	 * @return latitude and longitude of location
	 */
	public LatLng getFavoriteLocation() {
		return new LatLng(latitude, longitude);	
	}
	
}