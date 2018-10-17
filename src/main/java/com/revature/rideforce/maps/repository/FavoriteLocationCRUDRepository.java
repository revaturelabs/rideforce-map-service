package com.revature.rideforce.maps.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.rideforce.maps.beans.FavoriteLocation;

public interface FavoriteLocationCRUDRepository extends CrudRepository<FavoriteLocation, Long> {

	@Transactional
	public FavoriteLocation removeByNameAndUserId(String name, int userId);
	
	@Transactional
	public void delete(FavoriteLocation location);
}
