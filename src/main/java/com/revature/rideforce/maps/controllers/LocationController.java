package com.revature.rideforce.maps.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.service.LocationService;
import com.revature.rideforce.maps.validate.Validate;

/**
 * The controller for obtaining a location.
 * 
 * @author Revature Java batch
 */
@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/location")
public class LocationController {
	private static final Logger log = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	private LocationService ls;

	/**
	 * GET request method. Receives an address, processes it and returns a location with
	 * coordinates in latitude and longitude.
	 * 
	 * @param address a string representing an address, either as a zip code, or a 
	 * street address (assumes: Street, City, State, Zip; comma delimited, in that order).
	 * @return ResponseEntity<?> either ResponseError with given message wrapped in
	 *         a ResponseEntity to allow it to be returned from a controller method
	 *         or a ResponseEntity<> with a location and HTTP status code, and no headers.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CachedLocation>> get(@RequestParam CachedLocation location) {

		return new ResponseEntity<List<CachedLocation>>(ls.getLocationByAddress(location), HttpStatus.OK);
	}
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, produces = MediaType.Application_JSON_VALUE)
	 * public ResponseEntity<List<CachedLocation>>getCachedLocation(@RequestParam CachedLocation location){
	 * return new ResponseEntity<List<CachedLocation>>(ls.getLocationByAddress(location), HttpStatus.OK);
	 * }
	 */
}



