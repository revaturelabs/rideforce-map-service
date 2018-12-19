package com.revature.rideforce.maps.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller for testing (carry-over from 1st iteration)
 * @author Revature Java batch
 */
@RestController
public class MapsTestController {

	/**
	 * GET method test
	 * @return String indicating that controller test works
	 */
	@GetMapping("/test")
	public String test() {
		return "maps controller works!";
	}
	
}
