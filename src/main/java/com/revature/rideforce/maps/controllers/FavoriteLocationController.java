package com.revature.rideforce.maps.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.service.FavoriteLocationService;

/**
 * The controller for obtaining the favorited locations
 * @author Revature Java batch
 * @RestController
 * @RequestMapping(value = "/favoritelocations")
 */
@RestController
@RequestMapping(value = "/favoritelocations")
public class FavoriteLocationController {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(FavoriteLocationController.class);
	
	/**
	 * Injecting the FavoriteLocationService spring bean
	 * @Autowired
	 */
	@Autowired
	private FavoriteLocationService fls;
	
	/**
	 * list of favorite locations
	 */
	private List<FavoriteLocation> favoriteLocations = new ArrayList<>();

	/** 
	 * This method finds favorite locations by user id then limits that list to 5 locations
	 * @param userId
	 * @return List<FavoriteLocation> (the list of favorite locations)
	 */
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getLocationsByUserId(@RequestParam String address, @PathVariable("id") int userId){
        log.info("finding locations by user");
        if(fls.findFavoriteLocationByUserId(userId).size() > 5) {
            log.debug("User with id: " + userId + "tried to favorite more than 5 locations");
            return null;
        }
        else {
            return new ResponseEntity<LatLng>(fls.getOne(address, userId), HttpStatus.OK);
        }
    
    }
	
	/** 
	 * This method finds favorite locations by user id then limits that list to 5 locations
	 * @param userId
	 * @return List<FavoriteLocation> (the list of favorite locations)
	 */
	@RequestMapping(value="/users/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> postLocationsByUserId(@RequestParam String address, @PathVariable("id") int userId){
        log.info("finding locations by user");
        if(fls.findFavoriteLocationByUserId(userId).size() >= 5) {
            log.debug("User with id: " + userId + "tried to favorite more than 5 locations");
            return null;
        }
        else {
            return new ResponseEntity<LatLng>(fls.getOne(address, userId), HttpStatus.OK);
        }
    
    }
}
