package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "")
public class Location {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author")
	@SequenceGenerator(name = "author", sequenceName = "author_seq", allocationSize = 1)
	int id;
	@Column(name = "ADDR_LINE_1")
	String address1;
	@Column(name = "ADDR_LINE_2")
	String address2;
	@Column(name = "CITY")
	String city;
	@Column(name = "STATE")
	String state;
	@Column(name = "ZIP")
	String zip;
	@Column(name = "LATITUDE")
	double latitude;
	@Column(name = "LONGITUDE")
	double longitude;

	public Location(String address1, String address2, String city, String state, String zip, double latitude,
			double longitude) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Location [address=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state
				+ ", zipcode=" + zip + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
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
	
	
}
