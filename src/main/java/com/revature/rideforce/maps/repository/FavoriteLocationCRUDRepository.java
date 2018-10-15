package com.revature.rideforce.maps.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.rideforce.maps.beans.FavoriteLocation;

public interface FavoriteLocationCRUDRepository extends CrudRepository<FavoriteLocation, Long> {

	@Transactional
	Long deleteByName(String name);
	@Transactional
	FavoriteLocation removeByNameAndUserId(String Name, int userId);
	
}
