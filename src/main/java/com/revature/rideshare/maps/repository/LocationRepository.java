package com.revature.rideshare.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.rideshare.maps.beans.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	public Location findByAddress(String address);
	
}
