package com.revature.rideforce.maps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.FavoriteLocation;

/**
 * Interface providing CRUD functionality for FavoriteLocation
 * @author Revature Java batch
 */
@Repository
public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Integer> {
	
	public List<FavoriteLocation> findByUserId(int userId);
	public FavoriteLocation findByAddress(String address);
	public FavoriteLocation findByLatitudeAndLongitudeAndUserId(double lat, double lng, int userId);
	public FavoriteLocation findByNameAndUserId(String name, int userId);
//	public FavoriteLocation findFavoriteLocationByLatitudeAndLongitude(double)
	
}
