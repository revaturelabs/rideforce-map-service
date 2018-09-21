package com.revature.rideforce.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.CachedLocation;

@Repository
public interface LocationRepository extends JpaRepository<CachedLocation, Integer> {

	public CachedLocation findByAddress(String address);
	
}
