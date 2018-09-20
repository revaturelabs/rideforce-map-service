package com.revature.rideshare.maps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideshare.maps.beans.Route;
import com.revature.rideshare.maps.service.RouteService;

@RestController
@RequestMapping(value="/route")
public class RouteController {
	
	@Autowired
	private RouteService routeService;

	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Route> get(@RequestParam String origin, @RequestParam String destination) {
		
		return new ResponseEntity<Route>(routeService.getRoute(origin, destination), HttpStatus.OK);
		
	}
	
}
