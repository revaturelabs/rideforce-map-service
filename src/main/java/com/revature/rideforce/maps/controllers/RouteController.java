package com.revature.rideforce.maps.controllers;

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
import com.revature.rideforce.maps.service.RouteService;

/**
 * The controller to handle the routes
 * @author Revature Java batch
 */
@CrossOrigin(origins="*")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/route")
public class RouteController {
	@Autowired
	private RouteService routeService;

	/**
	 * Http GET request method for the route
	 * @param	start a starting address
	 * @param	end	an ending address
	 * @return 	ResponseEntity<?> (either ResponseError with given message wrapped in a ResponseEntity 
	 * 			to allow it to be returned from a controller method or a ResponseEntity<Route> with given route 
	 * 			and HTTP status code, and no headers)
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> get(@RequestParam String start, @RequestParam String end) {
		// neither parameter can be empty
		if(start.isEmpty()) {
			return new ResponseError("Must specify a starting address.")
					.toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if(end.isEmpty()) {
			return new ResponseError("Must specify an ending address.")
					.toResponseEntity(HttpStatus.BAD_REQUEST);
		}

		// need to check whether the first part of the address is a negative number
		String[] s = start.split(" ");
		String[] e = end.split(" ");
		// matches negative integer and floating point numbers
		if(s[0].matches("-\\d+(\\.\\d+)?") || e[0].matches("-\\d+(\\.\\d+)?")){
			return new ResponseError("Can't input negative numbers.")
					.toResponseEntity(HttpStatus.BAD_REQUEST);
		}

		// trim whitespace from the strings
		start = start.trim();
		end = end.trim();
		
		// for both, remove special characters from the front of string
		if(start.matches("^[^\\w].*")) {
			start = start.substring(1, (start.length()));
		}
		if(end.matches("^[^\\w].*")) {
			end = end.substring(1, (end.length()));
		}
		
		// for both, remove special characters from the back of string
		if(start.matches("^.*[^\\w]$")) {
			start = start.substring(0, start.length() - 1);
		}
		if(end.matches("^.*[^\\w]$")) {
			end = end.substring(0, end.length() - 1);
		}

		return new ResponseEntity<>(routeService.getRoute(start, end), HttpStatus.OK);
	}

}
