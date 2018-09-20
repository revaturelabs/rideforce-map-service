package com.revature.rideshare.maps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideshare.maps.beans.Location;
import com.revature.rideshare.maps.service.LocationService;

@RestController
@RequestMapping(value="/location")
public class LocationController {

	@Autowired
	LocationService ls;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Location> get(@RequestParam("address") String address) {
		return new ResponseEntity<Location>(ls.getOne(address), HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	public Location post() {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.PUT)
	public Location put() {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.PATCH)
	public Location patch() {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete() {
		
	}
}
