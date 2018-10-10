package com.revature.rideforce.maps.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.service.RouteService;

/**
 * Interface providing CRUD functionality for LocationService
 * @author Revature Java batch
 * @Repository
 */
@Repository
public interface LocationRepository extends JpaRepository<CachedLocation, Integer> {
	
public CachedLocation findByAddress(String address);
}
