package com.revature.rideshare.maps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideshare.maps.beans.Location;
import com.revature.rideshare.maps.service.LocationService;

@RestController
@RequestMapping(value = "/location")
public class LocationController {
	@Autowired
	LocationService ls;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Location> get(@RequestParam("address") String address) {
		return new ResponseEntity<Location>(ls.getOne(address), HttpStatus.OK);
	}
}
