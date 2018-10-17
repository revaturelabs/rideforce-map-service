package com.revature.rideforce.maps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideforce.maps.beans.ResponseError;
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
	 * @return ResponseEntity<?> (either ResponseError with given message wrapped in a ResponseEntity 
	 * to allow it to be returned from a controller method or a ResponseEntity<Route> with given route 
	 * and HTTP status code, and no headers)
	 * @throws Exception
	 */
//	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping
	public ResponseEntity<?> get(@RequestParam String start, @RequestParam String end) {
		if (start.isEmpty()) {
			return new ResponseError("Must specify a start address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if (end.isEmpty()) {
			return new ResponseError("Must specify a end address.").toResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		
		 if(start.matches("-?\\d+(\\.\\d+)?")){
			 return new ResponseError("Can't input negative numbers.").toResponseEntity(HttpStatus.BAD_REQUEST);

		 }

		 if(end.matches("-?\\d+(\\.\\d+)?")){
			 return new ResponseError("Can't input negative numbers.").toResponseEntity(HttpStatus.BAD_REQUEST);

		 }
		
		start = start.trim();
		if(start.matches("^[^\\w].*")) {
			start = start.substring(1, (start.length()));
		}
		int last = start.length() - 1;
		if(start.matches("^.*[^\\w]$")) {
			start = start.substring(0, last);
			}
		
		end = end.trim();
		if(end.matches("^[^\\w].*")) {
			end = end.substring(1, (end.length()));
		}
		int last1 = end.length() - 1;
		if(end.matches("^.*[^\\w]$")) {
			end = end.substring(0, last1);
			}
		
		return new ResponseEntity<>(routeService.getRoute(start, end), HttpStatus.OK);
	}
	
}
