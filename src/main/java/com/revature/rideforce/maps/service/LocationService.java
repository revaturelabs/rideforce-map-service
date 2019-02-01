package com.revature.rideforce.maps.service;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * 
 * @author Revature Java batch
 */
@Service
@Transactional
public class LocationService {
	private static final Logger log = LoggerFactory.getLogger(LocationService.class);

	@Autowired
	private GeoApiContext geoApiContext;

	@Autowired
	private LocationRepository locationRepo;

	public LocationService() {
		super();
	}

	/**
	 * class constructor set this geoApiContext to 'geoApiContext'
	 * 
	 * @param geoApiContext
	 */
	public LocationService(GeoApiContext geoApiContext) {
		super();
		this.geoApiContext = geoApiContext;
		log.info("LocationService instantiated");
	}

	/**
	 * Get a location based on a full address string, either from an existing cached location
	 * in the database, or else by goecode search
	 * @param address the address string, some or all of street, city, state, zip
	 * @param coords latitude and longitude 
	 * @return a CachedLocation object matching the given address
	 */
	public CachedLocation getLocationByAddress(String address) {
		// look up the address
		GeocodingResult[] gr = geocodeRequest(address);	
		
		// check the db to see if the address is there.
		CachedLocation location = lookUpAddress(address, gr);

		// process the address string into street, city, state, zip
		String[] splitLocation = address.split(",");
		for (int i = 0; i < splitLocation.length; i++) {
			splitLocation[i] = splitLocation[i].trim();
		}
		
		// otherwise, use GeocodingApi to look up the passed address
		if (location == null) {
			// this is the same call as earlier, but for some reason the existing code makes it again	
			gr = geocodeRequest(address);	

			switch(splitLocation.length) {
				case 1:
					location = new CachedLocation(
							address, 
							gr[0].geometry.location);
					break;
				case 2:
					// assumes street, city
					location = new CachedLocation(
							splitLocation[0], 
							splitLocation[1], 
							gr[0].geometry.location);
					break;
				case 3:
					// check if third value is a state code
					if (splitLocation[2].length() == 2 && splitLocation[2].matches("[a-zA-Z]+")) {
						// assumes street, city, state
						location = new CachedLocation(
								splitLocation[0], 
								splitLocation[1], 
								splitLocation[2],
								gr[0].geometry.location);
					} else {
						// assumes street, city, ignores third field 
						location = new CachedLocation(
								splitLocation[0], 
								splitLocation[1], 
								gr[0].geometry.location);
					}
					break;
				case 4:
					// check if third value is a state code
					if (splitLocation[2].length() == 2 && splitLocation[2].matches("[a-zA-Z]+")) {
						// create the location object
						location = new CachedLocation();
						location.setAddress(splitLocation[0]);
						location.setCity(splitLocation[1]);
						location.setStateCode(splitLocation[2]);
						if (splitLocation[3].length() == 5) {
							location.setZip(splitLocation[3]);
						}
					} else {
						location = new CachedLocation();
						location.setAddress(splitLocation[0]);
						location.setCity(splitLocation[1]);
						if (splitLocation[3].length() == 5) {
							location.setZip(splitLocation[3]);
						}
					}
					// set coordinates
					location.setLatitude(gr[0].geometry.location.lat);
					location.setLongitude(gr[0].geometry.location.lng);
					break;
				default:
					log.error("Not a valid address");
					Thread.currentThread().interrupt();
					return null;
			}

			locationRepo.save(location);
			return location;
		} else {
			switch(splitLocation.length) {
				case 1:
					location.setAddress(splitLocation[0]);
					break;
				case 2:
					location.setCity(splitLocation[1]);
					break;
				case 3:
					// check if third value is a state code
					if (splitLocation[2].length() == 2 && splitLocation[2].matches("[a-z]+")) {
						location.setCity(splitLocation[1]);
						location.setStateCode(splitLocation[2]);
					} else {
						location.setCity(splitLocation[1]);
					}
					break;
				case 4:
					// check if third value is a state code
					if (splitLocation[2].length() == 2 && splitLocation[2].matches("[a-z]+")) {
						location.setCity(splitLocation[1]);
						location.setStateCode(splitLocation[2]);
						if (splitLocation[3].length() == 5) {
							location.setZip(splitLocation[3]);
						}
					} else {
						location.setCity(splitLocation[1]);
						if (splitLocation[3].length() == 5) {
							location.setZip(splitLocation[3]);
						}
					}
					break;
				default:
					log.error("Not a valid address");
					Thread.currentThread().interrupt();
					return null;
			}
		}
		return location;
	}

	/**
	 * Get a location based on a zip code, either from an existing cached location
	 * in the database, or else by goecode search.
	 * @param address the zip code, as a string
	 * @param coords latitude and longitude 
	 * @return a CachedLocation object matching the given address
	 */
	public CachedLocation getLocationByZipCode(String address) {
		// look up the address
		GeocodingResult[] gr = geocodeRequest(address);	
		
		// check the db to see if the address is there.
		CachedLocation location = lookUpAddress(address, gr);

		// if the location was not in the database
		if (location == null) {
			// create the location from the geocoding results, passed zip code
			location = new CachedLocation();
			location.setLatitude(gr[0].geometry.location.lat);
			location.setLatitude(gr[0].geometry.location.lng);
			location.setZip(address);
			
			// save location to database, return it
			locationRepo.save(location);
			return location;
		} else {
			location.setZip(address);
			return location;
		}
	}
	
	private CachedLocation lookUpAddress(String address, GeocodingResult[] gr) {
		// if the address is a zip code, check the database
		if(StringUtils.isNumeric(address)) {
			CachedLocation location = locationRepo.findByAddress(address);
			if(location != null) {
				return location;
			}
		}
		
		// create location objects, based on geocoding results, to look for in the database
		CachedLocation lat = locationRepo.findByLatitude(gr[0].geometry.location.lat);
		CachedLocation lng = locationRepo.findByLongitude(gr[0].geometry.location.lng);

		// seems to check for an address in the database, 
		// assuming that no two addresses will share the same latitude or longitude
		if (lat != null && lng != null) {
			if (lat.equals(lng)) {
				log.info("There is a valid coordinate here");
				return lat;
			}
		}
		
		// if the location wasn't in the database already, return null
		return null;
	}
	
	/**
	 * Makes a call to google maps api to look up an address.
	 * @param address the string to search for
	 * @return the GeocodingResult[] or null, if there was a problem
	 */
	public GeocodingResult[] geocodeRequest(String address) {
		try {
			return GeocodingApi.geocode(geoApiContext, address).await();
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getGeoApiContext() {
		return geoApiContext;
	}

	public void setGeoApiContext(GeoApiContext geoApiContext) {
		this.geoApiContext = geoApiContext;
	}
}
