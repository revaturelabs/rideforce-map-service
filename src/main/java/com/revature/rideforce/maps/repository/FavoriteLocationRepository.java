package com.revature.rideforce.maps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.beans.FavoriteLocation;

@Repository
public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Integer>{
	public List<FavoriteLocation> findFavoriteLocationByUserId(int userId);
	public FavoriteLocation findByAddress(String address);
}
