package com.revature.rideshare.maps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.revature.rideshare.maps.service.LocationService;

@RestController
@RequestMapping(value = "/location")
public class LocationController {
	@Autowired
	private LocationService ls;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<LatLng> get(@RequestParam("address") String address) {
		return new ResponseEntity<LatLng>(ls.getOne(address), HttpStatus.OK);
	}
}
