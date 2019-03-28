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
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/location")
public class LocationController {
	private static final Logger log = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	private LocationService ls;

	/**
	 * GET request method. Receives an address, processes it and returns a location
	 * with coordinates in latitude and longitude.
	 * 
	 * @param address a string representing an address, either as a zip code, or a
	 *                street address (assumes: Street, City, State, Zip; comma
	 *                delimited, in that order).
	 * @return ResponseEntity<?> either ResponseError with given message wrapped in
	 *         a ResponseEntity to allow it to be returned from a controller method
	 *         or a ResponseEntity<> with a location and HTTP status code, and no
	 *         headers.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> get(@RequestParam String address) {
		Validate normalize = new Validate();

		// don't accept an empty address parameter
		if (address.isEmpty()) {
			return new ResponseError("Must specify an address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}

		// normalize the address input
		address = normalize.validateAddress(address);
		if (address.matches("^[^\\w].*")) {
			address = address.substring(1, address.length());
		}
		if (address.matches("^.*[^\\w]$")) {
			address = address.substring(0, address.length() - 1);
		}

		// if the address is solely a zip code
		if (StringUtils.isNumeric(address)) {
			log.info(address);
			if (address.length() != 5) {
				// number is the wrong length to be a zip code, so return bad request
				log.info(String.format("numcheck = %d", address.length()));
				return new ResponseError("Address cannot be a number that is not a Zip code.")
						.toResponseEntity(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(ls.getLocationByZipCode(address), HttpStatus.OK);
			}
		}

		// address was not a zip code, so get location by address
		return new ResponseEntity<>(ls.getLocationByAddress(address), HttpStatus.OK);
	}
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, produces = MediaType.Application_JSON_VALUE)
	 * public ResponseEntity<List<CachedLocation>>getCachedLocation(@RequestParam CachedLocation location){
	 * return new ResponseEntity<List<CachedLocation>>(ls.getLocationByAddress(location), HttpStatus.OK);
	 * }
	 */
}



