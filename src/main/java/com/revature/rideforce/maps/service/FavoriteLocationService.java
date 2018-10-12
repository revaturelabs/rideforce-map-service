package com.revature.rideforce.maps.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;

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
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationRepository;
import com.revature.rideforce.maps.repository.LocationRepository;

@Service
@Transactional
public class FavoriteLocationService {
	private static final Logger log = LoggerFactory.getLogger(LocationService.class);

	/**
	 * Injecting the GeoApiContext, the entry point for making requests against the Google Geo APIs. 
	 */
	@Autowired
	private GeoApiContext geoApiContext;

	/**
	 * Injecting the LocationRepository
	 */
	@Autowired
	private FavoriteLocationRepository flr;

	/**
	 * get a location
	 * @param address
	 * @param userId 
	 * @return LatLng (geographical location represented by latitude/longitude pair)
	 */
	public LatLng getOne(String address, @Min(1) int userId) {
		FavoriteLocation location = flr.findByAddress(address);
		if (location == null) {
			try {
				GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
				location = new FavoriteLocation(address, results[0].geometry.location, userId);
				flr.save(location);
				return results[0].geometry.location;
			} catch (ApiException | InterruptedException | IOException e) {
				log.error("Unexpected exception when fetching location.", e);
				Thread.currentThread().interrupt();
				return null;
			}
		}
		return location.getFavoriteLocation();
	}
	
	
	public List<FavoriteLocation> findFavoriteLocationByUserId(int userId) {
		return flr.findFavoriteLocationByUserId(userId);
	}
	
}
