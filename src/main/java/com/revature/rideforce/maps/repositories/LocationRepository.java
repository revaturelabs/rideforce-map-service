package com.revature.rideforce.maps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.rideforce.maps.beans.CachedLocation;

/**
 * Interface providing CRUD functionality for CachedLocation
 * primary key is Integer
 * @author Revature Java batch
 * @Repository
 */
@Repository
public interface LocationRepository extends JpaRepository<CachedLocation, Integer> {
	public CachedLocation findByAddress(String address);
}
