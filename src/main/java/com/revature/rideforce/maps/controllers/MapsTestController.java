package com.revature.rideforce.maps.controllers;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller for testing
 * @author Revature Java batch
 * @RestController
 */
@RestController
@Lazy(true)
public class MapsTestController {

	/**
	 * GET method test
	 * @return String indicating that controller test works
	 * @RequestMapping(value="/test", method=RequestMethod.GET)
	 */
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "maps controller works!";
	}
	
}
