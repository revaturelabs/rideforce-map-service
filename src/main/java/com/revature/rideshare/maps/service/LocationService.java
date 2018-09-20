package com.revature.rideshare.maps.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.revature.rideshare.maps.beans.Location;

@Service
public class LocationService {

	// @Autowired
	// private LocationRepository locationRepo;

	public Location getOne(String address) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBXWXgWzxhyvz9JyN9SrHgGOzi7VcU5G3g").build();
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
			LatLng location = results[0].geometry.location;
			return new Location(location.lat, location.lng);
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
