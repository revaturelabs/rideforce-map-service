package com.revature.rideforce.maps.service;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.List;

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
	public CachedLocation getOne(String address, LatLng coords) {
		String[] splitLocation = address.split(",");
		for(int i = 0; i < splitLocation.length; i++)
		{
			splitLocation[i] = splitLocation[i].trim();
		}
		
		CachedLocation location = null;
		
		CachedLocation loclat = locationRepo.findByLatitude(coords.lat);
		CachedLocation loclon = locationRepo.findByLongitude(coords.lng);
		
		if(loclat != null && loclon != null)
		{
			if(loclat.equals(loclon))
			{
				log.info("There is a valid coordinate here");
				location = loclat;
			}
		}

		//CachedLocation locationByLat, locationByLon;
		if (location == null) {
			try {
				GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
				//locationByLat = locationRepo.findByLatitude(results[0].geometry.location.lat);
				//locationByLon = locationRepo.findByLongitude(results[0].geometry.location.lng);
				
				if(splitLocation.length == 1)
				{
					location = new CachedLocation(address, results[0].geometry.location);
				}
				else if(splitLocation.length == 2)
				{
					location = new CachedLocation(splitLocation[0], splitLocation[1], results[0].geometry.location);
				}
				else if(splitLocation.length == 3)
				{
					if(splitLocation[2].length() == 2 && splitLocation[2].matches("[a-z]+"))
					{
						location = new CachedLocation(splitLocation[0], splitLocation[1], splitLocation[2], results[0].geometry.location);
					}
					else
					{
						location = new CachedLocation(splitLocation[0], splitLocation[1], results[0].geometry.location);
					}
				}
				else if(splitLocation.length == 4)
				{
					if(splitLocation[2].length() == 2 && splitLocation[2].matches("[a-z]+"))
					{
						location.setCity(splitLocation[1]);
						location.setStateCode(splitLocation[2]);
						location.setZip(splitLocation[3]);
					}
					else
					{
						location.setCity(splitLocation[1]);	
						location.setZip(splitLocation[3]);
					}
				}
				else
				{
					log.error("Not a valid address");
					Thread.currentThread().interrupt();
					return null;
				}
				
				locationRepo.save(location);
				return location;
			} catch (ApiException | InterruptedException | IOException e) {
				log.error("Unexpected exception when fetching location.", e);
				Thread.currentThread().interrupt();
				return null;
			}
		}
		else
		{
			if(splitLocation.length == 2)
			{
				location.setCity(splitLocation[1]);
			}
			else if(splitLocation.length == 3)
			{
				if(splitLocation[2].length() == 2 && splitLocation[2].matches("[a-z]+"))
				{
					location.setCity(splitLocation[1]);
					location.setStateCode(splitLocation[2]);
				}
				else
				{
					location.setCity(splitLocation[1]);				
				}
			}
			else if(splitLocation.length == 4)
			{
				if(splitLocation[2].length() == 2 && splitLocation[2].matches("[a-z]+"))
				{
					location.setCity(splitLocation[1]);
					location.setStateCode(splitLocation[2]);
					location.setZip(splitLocation[3]);
				}
				else
				{
					location.setCity(splitLocation[1]);	
					location.setZip(splitLocation[3]);
				}
			}
			else
			{
				log.error("Not a valid address");
				Thread.currentThread().interrupt();
				return null;
			}
		}
		return location;
	}
	
	/**
	 * get a location
	 * @param address
	 * @return LatLng (geographical location represented by latitude/longitude pair)
	 */	
	public LatLng getOneZip(String address, LatLng coords) {
		CachedLocation location = locationRepo.findByAddress(address);
		CachedLocation loclat = locationRepo.findByLatitude(coords.lat);
		CachedLocation loclon = locationRepo.findByLongitude(coords.lng);
		
		if(loclat != null && loclon != null)
		{
			if(loclat.equals(loclon))
			{
				log.info("There is a valid coordinate here");
				location = loclat;
			}
		}
		
		if (location == null) {
			try {
				GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
				LatLng coord = results[0].geometry.location;
				DecimalFormat df = new DecimalFormat("#.##");
				coord.lat = Double.parseDouble(df.format(coord.lat));
				coord.lng = Double.parseDouble(df.format(coord.lng));
				location = new CachedLocation(results[0].geometry.location, address);
				locationRepo.save(location);
				return results[0].geometry.location;
			} 
//			catch(SQLIntegrityConstraintViolationException e)
//			{
//				
//			}
			catch (ApiException | InterruptedException | IOException e) {
				log.error("Unexpected exception when fetching location.", e);
				Thread.currentThread().interrupt();
				return null;
			}
		}
		else
		{
			location.setZip(address);
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
	
	private void ValidateNewAddress(CachedLocation newLocation)
	{
		
	}
	
	/**
	 * set this geo api context to 'geoApiContext'
	 */
	public void setGeoApiContext(GeoApiContext geoApiContext) {
		this.geoApiContext = geoApiContext;
	}
	
}
