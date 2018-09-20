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
	
	public String getOne(String address) {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyBXWXgWzxhyvz9JyN9SrHgGOzi7VcU5G3g")
			    .build();
		try {
			GeocodingResult[] results =  GeocodingApi.geocode(context,
				    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//System.out.println(results[0].addressComponents[0]);
			//gson.toJson(results[0].addressComponents)
			return acp.parseAddressComponent(gson.toJson(results[0].addressComponents));
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//locationRepo.findByAddress1(address);
		return null;
	}
	
}
