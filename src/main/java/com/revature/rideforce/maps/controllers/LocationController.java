package com.revature.rideforce.maps.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.service.LocationService;

/**
 * The controller for obtaining the location
 * @author Revature Java batch
 * @RestController
 * @RequestMapping(value = "/location")
 */
@RestController
@RequestMapping(value = "/location")
public class LocationController {
	
	/**
	 * Injecting the LocationService spring bean
	 * @Autowired
	 */
	@Autowired
	private LocationService ls;

	/**
	 * GET request method
	 * @param address
	 * @return ResponseEntity<?> ResponseEntity<?> (either ResponseError or ResponseEntity<LatLng> depending on validity of input)
	 * @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> get(@RequestParam String address) {
		if (address.isEmpty()) {
			return new ResponseError("Must specify an address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		address = address.trim();
		if( ((address.charAt(0) > 32) && (address.charAt(0) < 48)) ||
			((address.charAt(0) > 57) && (address.charAt(0) < 65)) ||
			((address.charAt(0) > 90) && (address.charAt(0) < 97)) ||
			((address.charAt(0) > 122) && (address.charAt(0) < 127))) {
			address = address.substring(1, (address.length()));
		}
		int last = address.length() - 1;
		if( ((address.charAt(last) > 32) && (address.charAt(last) < 48)) ||
			((address.charAt(last) > 57) && (address.charAt(last) < 65)) ||
			((address.charAt(last) > 90) && (address.charAt(last) < 97)) ||
			((address.charAt(last) > 122) && (address.charAt(last) < 127))) {
			address.substring(0, last);
			}
		if(StringUtils.isNumeric(address)) {
			int numCheck = address.length();
			System.out.println(address);
			if(numCheck != 5) {
				System.out.println("numcheck = " +numCheck);
				return new ResponseError("Address cannot be a number that is not a Zip code.").toResponseEntity(HttpStatus.BAD_REQUEST);
			}	
		}
		return new ResponseEntity<LatLng>(ls.getOne(address), HttpStatus.OK);
	}
}
