package com.revature.rideforce.maps.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.rideforce.maps.controllers.LocationController;

/**
 * This class handles situations where users might ignore/bypass the
 * autocomplete functionality of the Google Maps, thus making their input
 * non-uniform
 * 
 * @author Revature Java batch
 */
public class Validate {

	/**
	 * logger
	 */
//	private static final Logger log = LoggerFactory.getLogger(LocationController.class);
	private static final Logger log = LoggerFactory.getLogger(Validate.class); //Changed LocationController => Validate.class (1905-May Batch)


	/**
	 * This method normalizes the address input in hopes of reducing the number of
	 * possible extraneous entries in the geolocation cache (issue noted by
	 * ianprime0509)
	 * 
	 * @param address
	 * @return address
	 */
	public String validateAddress(String address) {
		log.info("inside the validate method");
		if (address == null) {
			log.info("string input was null");
			return null;
		}
		if (address.equals("")) { //Changed == to .equals(). We are comparing two values
			log.info("empty string");
			return "";
		}
		address = address.trim();
		boolean check = true;
		String addresscheck = address;
		do {
			addresscheck = address.replaceFirst("\\s{2,}", " ");
			if (addresscheck.equals(address)) { //Changed == to .equals(). We are comparing two values
				check = false;
			}
			address = addresscheck;

		} while (check);
		address = address.toLowerCase();
		log.info("returning normalized address");
		return address;

	}

}