package com.revature.rideshare.maps.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Location;

@RestController
@RequestMapping(value="/location")
public class LocationController {

	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Location get(@PathVariable String address) {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.POST)
	public Location post(@PathVariable String address) {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.PUT)
	public Location put(@PathVariable String address) {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.PATCH)
	public Location patch(@PathVariable String address) {
		
		return null;
	}
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@PathVariable String address) {
		
	}
}
