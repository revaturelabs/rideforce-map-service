package com.revature.rideforce.maps.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller for testing
 * @author Revature Java batch
 * @RestController
 */
@RestController
public class MapsTestController {

	/**
	 * GET method test
	 * @return String indicating that controller test works
	 * @RequestMapping(value="/test", method=RequestMethod.GET)
	 */
	@GetMapping("/test")
	public String test() {
		return "maps controller works!";
	}
	
}
