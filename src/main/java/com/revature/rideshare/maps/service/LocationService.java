package com.revature.rideshare.maps.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.revature.rideshare.maps.beans.Location;
import com.revature.rideshare.maps.repository.LocationRepository;

@Service
@Transactional
public class LocationService {

	@Autowired
	private LocationRepository locationRepo;

	public Location getOne(String address) {
		Location location = locationRepo.findByAddress(address);
		if (location == null) {
			GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBXWXgWzxhyvz9JyN9SrHgGOzi7VcU5G3g")
					.build();
			try {
				GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
				location = new Location(address, results[0].geometry.location);
				locationRepo.save(location);
			} catch (ApiException | InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
		return location;
	}

}
