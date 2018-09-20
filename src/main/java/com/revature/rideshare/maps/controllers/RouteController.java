package com.revature.rideshare.maps.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideshare.maps.beans.Location;

@RestController
@RequestMapping(value="/route")
public class RouteController {

	@RequestMapping(method=RequestMethod.GET)
	public Location get(@RequestParam String origin, @RequestParam String destination) {
		
		return null;
	}
	
}
