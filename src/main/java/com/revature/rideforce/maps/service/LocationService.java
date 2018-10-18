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


/**
 * The LocationService
 * @author Revature Java batch
 * @Service
 * @Transactional
 */
@Service
@Transactional
public class LocationService {
	/**
	 * logger
	 */
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
	private LocationRepository locationRepo;

	public LocationService() {
		super();
	}
	
	public LocationService(GeoApiContext geoApiContext) {
		super();
		this.geoApiContext = geoApiContext;
		log.info("LocationService instantiated");
	}

	/**
	 * get a location
	 * @param address
	 * @return LatLng (geographical location represented by latitude/longitude pair)
	 */	
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
				Thread.currentThread().interrupt();
				return null;
			}
		}
		return location.getLocation();
	}

	/**
	 * get the geo api context
	 * @return geoApiContext
	 */
	public Object getGeoApiContext() {
		return geoApiContext;
	}
	
	/**
	 * set this geo api context to 'geoApiContext'
	 */
	public void setGeoApiContext(GeoApiContext geoApiContext) {
		this.geoApiContext = geoApiContext;
	}
	
}
