package com.revature.rideshare.maps.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.revature.rideshare.maps.beans.Location;
import com.revature.rideshare.maps.repository.LocationRepository;
import com.revature.rideshare.maps.util.AddressComponentParser;

@Service
public class LocationService {

	//@Autowired
	//private LocationRepository locationRepo;
	@Autowired
	private AddressComponentParser acp;
	
	public Location getOne(String address) {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyBXWXgWzxhyvz9JyN9SrHgGOzi7VcU5G3g")
			    .build();
		try {
			GeocodingResult[] results =  GeocodingApi.geocode(context,
				    address).await();
			return new Location(address, results[0].geometry.location);
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
