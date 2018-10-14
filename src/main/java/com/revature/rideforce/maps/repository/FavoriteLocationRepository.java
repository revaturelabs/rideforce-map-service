package com.revature.rideforce.maps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.FavoriteLocation;

/**
 * Interface providing CRUD functionality for FavoriteLocation
 * @author Revature Java batch
 * @Repository
 */
@Repository
public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Integer> {
	
	public List<FavoriteLocation> findFavoriteLocationByUserId(int userId);
	public FavoriteLocation findByAddress(String address);
	public FavoriteLocation findByLongitude(double d);
	public FavoriteLocation findByLatitude(double d);
	
}
