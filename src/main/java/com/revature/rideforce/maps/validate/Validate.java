package com.revature.rideforce.maps.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.rideforce.maps.controllers.LocationController;

public class Validate {
	private static final Logger log = LoggerFactory.getLogger(LocationController.class);

	
	public String validateAddress(String address) {
		log.info("inside the validate method");
		if(address == null) {
			log.info("string input was null");
			return null;
		}
		if(address == "") {
			log.info("empty string");
			return "";
		}
		address = address.trim();
		boolean check = true;
		String addresscheck = address;
		do {
			addresscheck = address.replaceFirst("\\s{2,}", " ");
			if(addresscheck == address) {
				check = false;
			}
			address = addresscheck;
		
		}while(check);
		address = address.toLowerCase();
		log.info("returning normalized address");
		return address;
		
	}

}