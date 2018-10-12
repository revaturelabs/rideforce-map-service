package com.revature.rideforce.maps.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.service.FavoriteLocationService;

/**
 * The controller for obtaining the favorited locations
 * @author Revature Java batch
 * @RestController
 * @RequestMapping(value = "/favoritelocations/users")
 */
@RestController
@RequestMapping(value = "/favoritelocations/users")
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
	private FavoriteLocationService favoriteLocationService;
	
	/**
	 * list of favorite locations
	 */
	private List<FavoriteLocation> favoriteLocations = new ArrayList<>();

	/** 
	 * This method finds favorite locations by user id then limits that list to 5 locations
	 * @param userId
	 * @return List<FavoriteLocation>
	 */
	@GetMapping(value="/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<FavoriteLocation> getFavoriteLocationsByUserId(@PathVariable("userId") int userId, @RequestParam String address){
		log.info("finding locations by user");
		if(favoriteLocationService.findFavoriteLocationByUserId(userId).size() > 5) {
			log.debug("User with id: " + userId + "tried to favorite more than 5 locations");
			return null;
		}
		else {
			return favoriteLocations.stream().filter(location -> location.getUserId() == userId).collect(Collectors.toList());

		}

	}
}
