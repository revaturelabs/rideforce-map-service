package com.revature.rideforce.maps.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;

@Service
@Transactional
public class LocationService {
  private static final Logger log = LoggerFactory.getLogger(LocationService.class);

	@Autowired
	private GeoApiContext geoApiContext;

	@Autowired
	private LocationRepository locationRepo;

	public LatLng getOne(String address) {
		CachedLocation location = locationRepo.findByAddress(address);
		if (location == null) {
			try {
				GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
				location = new CachedLocation(address, results[0].geometry.location);
				locationRepo.save(location);
				return results[0].geometry.location;
			} catch (ApiException | InterruptedException | IOException e) {
				log.error("Unexpected exception when fetching location.", e);
				return null;
			}
		}
		return location.getLocation();
	}
}
