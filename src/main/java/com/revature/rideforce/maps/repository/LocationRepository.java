package com.revature.rideforce.maps.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.CachedLocation;

/**
 * Interface providing CRUD functionality for CachedLocation
 * @author Revature Java batch
 */
@Repository
public interface LocationRepository extends JpaRepository<CachedLocation, Integer> {
	
	public CachedLocation findByAddress(String address);
	
	public CachedLocation findByLatitude(double latitude);
	
	public CachedLocation findByLongitude(double longitude);
}
