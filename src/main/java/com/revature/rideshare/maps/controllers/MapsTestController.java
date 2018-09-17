package com.revature.rideshare.maps.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapsTestController {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "maps controller works!";
	}
}
