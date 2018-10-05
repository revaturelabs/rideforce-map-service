package com.revature.rideforce.maps.controllers;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;

/**
 * The controller to handle the routes
 * @author Revature Java batch
 * @RestController
 * @RequestMapping(value = "/route")
 */
@RestController
@RequestMapping(value = "/route")
public class RouteController {
	
	/**
	 * Injecting the RouteService spring bean
	 * @Autowired
	 */
	@Autowired
	private RouteService routeService;

	/**
	 * GET request method for the route
	 * @param start
	 * @param end
	 * @return ResponseEntity<?> (either ResponseError or ResponseEntity<Route> depending on validity of input)
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> get(@RequestParam String start, @RequestParam String end) {
		if (start.isEmpty()) {
			return new ResponseError("Must specify a start address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if (end.isEmpty()) {
			return new ResponseError("Must specify a end address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isNumeric(start)) {
			int numCheck = start.length();
			if(numCheck != 5) {
				log.info("numcheck = " +numCheck);
				return new ResponseError("Address cannot be a number that is not a Zip code.").toResponseEntity(HttpStatus.BAD_REQUEST);
			}	
		}
		return new ResponseEntity<Route>(routeService.getRoute(start, end), HttpStatus.OK);
	}
}
