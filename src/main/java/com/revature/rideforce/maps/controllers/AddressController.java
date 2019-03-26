package com.revature.rideforce.maps.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.service.LocationService;



@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/address")
public class AddressController {


	@Autowired
	private LocationService ls;

	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CachedLocation> hitLocationApi(@RequestBody CachedLocation location) {
		
		String address = location.getAddress();
		
		
		return new ResponseEntity<CachedLocation>(ls.getLocationByZipCode(address), HttpStatus.OK);
		
}

}

