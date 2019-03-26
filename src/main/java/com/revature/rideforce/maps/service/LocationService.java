package com.revature.rideforce.maps.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
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
	 * Class constructor that sets the GeoApiContext, seems to at least be used
	 * during unit testing of the service
	 * 
	 * @param geoApiContext
	 */
	public LocationService(GeoApiContext geoApiContext) {
		super();
		this.geoApiContext = geoApiContext;
		log.info("LocationService instantiated");
	}

	/**
	 * Get a location based on a full address string, either from an existing cached
	 * location in the database, or else by goecode search
	 * 
	 * @param address the address string, some or all of street, city, state, zip
	 * @param coords  latitude and longitude
	 * @return a CachedLocation object matching the given address
	 */
	/*
	 * okay this method will get all users from DB and get the lng and lats from
	 * each we need to get all the users in a 50 mile radius of the main user
	 * address/location
	 * 
	 */
	public List<CachedLocation> getLocationByAddress(CachedLocation location) {

		List<CachedLocation> ls = new ArrayList<CachedLocation>();

		ls = locationRepo.findAll();

		List<CachedLocation> lip = new ArrayList<CachedLocation>();

		for (int i = 0; i < ls.size(); i++) {

			double loc = this.getUsersByDistance(location.getLatitude(), location.getLongitude(),
					ls.get(i).getLatitude(), ls.get(i).getLongitude());

			if (loc <= 50) {
				lip.add(ls.get(i));
			}
		}

		return lip;

	}

	public CachedLocation getLocationByZipCode(CachedLocation loc) {
		String address = loc.getAddress();
		String[] locDetails = address.split(",");

		// look up the address
		GeocodingResult[] gr = geocodeRequest(address);

		// check the db to see if the address is there.
		CachedLocation location = lookUpAddress(address, gr);

		// if the location was not in the database
		if (location == null) {
			// create the location from the geocoding results, passed zip code
			location = new CachedLocation();
			location.setLatitude(gr[0].geometry.location.lat);
			location.setLongitude(gr[0].geometry.location.lng);
			location.setAddress(address);
			if(locDetails.length == 5) {
				location.setZip(locDetails[3].replace(" ", ""));
				location.setStateCode(locDetails[2].replace(" ", ""));
			} else {
				location.setZip(locDetails[2].split(" ")[2].replace(" ", ""));
				location.setStateCode(locDetails[2].split(" ")[1].replace(" ", ""));
			}
			
			location.setCity(locDetails[1]);

			// save location to database, return it
			locationRepo.save(location);
			return location;
		} else {
			loc = location;
			return loc;
		}
	}

	private CachedLocation lookUpAddress(String address, GeocodingResult[] gr) {
		// if the address is a zip code, check the database
		if (StringUtils.isNumeric(address)) {
			CachedLocation location = locationRepo.findByAddress(address);
			if (location != null) {
				return location;
			}
		}

		// create location objects, based on geocoding results, to look for in the
		// database
		// existing code checked for two objects, instead of searching by lat & lng
		// together
		CachedLocation findByLat = locationRepo.findByLatitude(gr[0].geometry.location.lat);
		CachedLocation findByLng = locationRepo.findByLongitude(gr[0].geometry.location.lng);

		// checks to see if the two addresses from the database match
		if (findByLat != null && findByLng != null) {
			if (findByLat.equals(findByLng)) {
				log.info("There is a valid coordinate here");
				return findByLat;
			}
		}

		// if the location wasn't in the database already, return null
		return null;
	}

	/**
	 * Makes a call to google maps Geo API to look up an address. In a separate
	 * method for modularity, and so that unit testing can stub the method
	 * (otherwise the service isn't testable)
	 * 
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

	// this is a helper method to calc distance for each user in DB
	public double getUsersByDistance(double lat, double lng, double lat2, double lng2) {
		double rad = 6371.0;
		double dlat = deg2Rad(lat2 - lat);
		double dlng = deg2Rad(lng2 - lng);

		double cha = Math.sin(dlat / 2) * Math.sin(dlat / 2)
				+ Math.acos(deg2Rad(lat)) * Math.acos(deg2Rad(lat2)) * Math.asin(dlng / 2) * Math.asin(dlng / 2);

		double pa = 2 * Math.atan2(Math.sqrt(cha), Math.sqrt(1 - cha));
		double da = rad * pa;
		double ans = da * 0.62137;
		return ans;

	}

	public double deg2Rad(double num) {
		return num * (Math.PI / 180);
	}
}
