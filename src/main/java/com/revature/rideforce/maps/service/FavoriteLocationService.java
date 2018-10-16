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
import com.revature.rideforce.maps.repository.FavoriteLocationCRUDRepository;
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

	@Autowired
	private FavoriteLocationCRUDRepository favoriteLocationCRUDRepo;
	/**
	 * get a favorite location
	 * @param address
	 * @param userId
	 * @return LatLng (geographical location represented by latitude/longitude pair)
	 */
	public LatLng getOne(String address, @Min(1) int userId) {
		FavoriteLocation location;
		FavoriteLocation location2;
		try {
			GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
			location = new FavoriteLocation(address, results[0].geometry.location, userId);
			location2 = favoriteLocationRepo.findByLatitude(location.getLatitude());
			if (location2 == null) {
				favoriteLocationRepo.save(location);
				return results[0].geometry.location;
			}
			else if((location.getLatitude() == location2.getLatitude()) && (location.getLongitude() == location2.getLongitude())) {
				System.out.println("Location already in saved location database");
			}
			else {
				favoriteLocationRepo.save(location2);
				return results[0].geometry.location;
			}
		} catch (ApiException | InterruptedException | IOException e) {
			log.error("Unexpected exception when fetching location.", e);
			Thread.currentThread().interrupt();
			return null;
		}
		return location.getFavoriteLocation();
}
			
	public FavoriteLocation deleteFavoriteLocationByNameAndUserId(String name, int userId) {
		//favoriteLocationCRUDRepo
		FavoriteLocation fav = favoriteLocationRepo.findByNameAndUserId(name, userId);
		if(fav == null) {
			fav = new FavoriteLocation();
			return fav;
		}
		favoriteLocationCRUDRepo.removeByNameAndUserId(name, userId);
		return fav;
	}

	/**
	 * fetching the favorite locations by the user's id
	 * @param userId
	 * @return List<FavoriteLocation>
	 */
	public List<FavoriteLocation> findFavoriteLocationByUserId(int userId) {
		return favoriteLocationRepo.findFavoriteLocationByUserId(userId);
	}

}
