package com.revature.rideforce.maps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.rideforce.maps.beans.FavoriteLocation;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Integer> {
	public List<FavoriteLocation> findFavoriteLocationByUserId(int userId);
	public FavoriteLocation findByAddress(String address);
}
