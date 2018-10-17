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
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationRepository;

/**
 * The FavoriteLocationService
 * @author Revature Java batch
 * @Service
 * @Transactional
 */
@Service
@Transactional
public class FavoriteLocationService {
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(FavoriteLocationService.class);

	/**
	 * Injecting the GeoApiContext, the entry point for making requests against the Google Geo APIs. 
	 */
	@Autowired
	private GeoApiContext geoApiContext;

	/**
	 * Injecting the FavoriteLocationRepository
	 */
	@Autowired
	private FavoriteLocationRepository favoriteLocationRepo;

	/**
	 * get a favorite location
	 * @param address
	 * @param userId
	 * @return LatLng (geographical location represented by latitude/longitude pair)
	 */
	public FavoriteLocation saveFavoriteLocation(String address, int userId, String name) {
		FavoriteLocation location;
		FavoriteLocation location2;
		try {
			GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
			//bean initialization
			location = new FavoriteLocation(address, results[0].geometry.location.lat,results[0].geometry.location.lng,name, userId);
			location2 = favoriteLocationRepo.findByLatitudeAndLongitudeAndUserId(location.getLatitude(), location.getLongitude(), userId);
			log.info(String.format("Location with lat,long, and user: %s ", location2));		
			FavoriteLocation locationByName= favoriteLocationRepo.findByNameAndUserId(name, userId);
			log.info(String.format("Location with this name and user: %s", locationByName));
			if (location2 == null && locationByName==null) {
				return favoriteLocationRepo.save(location);
			}
			else {
				return new FavoriteLocation();
			}
		} catch (ApiException | InterruptedException | IOException e) {
			log.error("Unexpected exception when fetching location.", e);
			Thread.currentThread().interrupt();
			return new FavoriteLocation();
		}
}
			
	public FavoriteLocation deleteFavoriteLocationByNameAndUserId(String name, int userId) {
		//favoriteLocationCRUDRepo
		FavoriteLocation fav = favoriteLocationRepo.findByNameAndUserId(name, userId);
		if(fav == null) {
			fav = new FavoriteLocation();
			return fav;
		}
		favoriteLocationRepo.delete(fav);
		return fav;
		//returns the location if successful, empty location if not presents
	}

	/**
	 * fetching the favorite locations by the user's id
	 * @param userId
	 * @return List<FavoriteLocation>
	 */
	public List<FavoriteLocation> findFavoriteLocationByUserId(int userId) {
		return favoriteLocationRepo.findByUserId(userId);
	}
	
}
