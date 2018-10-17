package com.revature.rideforce.maps.controllers;

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

import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.service.LocationService;
import com.revature.rideforce.maps.validate.Validate;

/**
 * The controller for obtaining the location
 * @author Revature Java batch
 * @RestController
 * @RequestMapping(value = "/location")
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/location")
public class LocationController {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(LocationController.class);
	
	/**
	 * Injecting the LocationService spring bean
	 * @Autowired
	 */
	@Autowired
	private LocationService ls;

	/**
	 * GET request method
	 * @param address
	 * @return ResponseEntity<?> (either ResponseError with given message wrapped in a ResponseEntity 
	 * to allow it to be returned from a controller method or a ResponseEntity<LatLng> with given address 
	 * and HTTP status code, and no headers)
	 * @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> get(@RequestParam String address) {
		Validate normalize = new Validate();
		if (address.isEmpty()) {
			return new ResponseError("Must specify an address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		address = normalize.validateAddress(address);
		if(address.matches("^[^\\w].*")) {
			address = address.substring(1, (address.length()));
		}
		int last = address.length() - 1;
		if(address.matches("^.*[^\\w]$")) {
			address = address.substring(0, last);
			}
		if(StringUtils.isNumeric(address)) {
			int numCheck = address.length();
			log.info(address);
			if(numCheck != 5) {
				String message= String.format("numcheck = %d", numCheck);
				log.info(message);
				return new ResponseError("Address cannot be a number that is not a Zip code.").toResponseEntity(HttpStatus.BAD_REQUEST);
			}	
		}
		return new ResponseEntity<>(ls.getOne(address), HttpStatus.OK);
	}
	
}