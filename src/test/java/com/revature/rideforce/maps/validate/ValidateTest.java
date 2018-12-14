package com.revature.rideforce.maps.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.rideforce.maps.controllers.LocationController;

/**
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.validate.Validate Validate} 
 * @author Revature Java batch
 */
public class ValidateTest {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(LocationController.class);

	@Test
	public void test1() {
		Validate validate = new Validate();
		String str = validate.validateAddress(" this   test works!");
		log.info(str);
		assertThat(str).isEqualTo("this test works!");
	}
	
	@Test
	public void test2() {
		Validate validate = new Validate();
		String str = validate.validateAddress(" this   test       works! ");
		log.info(str);
		assertThat(str).isEqualTo("this test works!");
	}
	
	@Test
	public void test3() {
		Validate validate = new Validate();
		String str = validate.validateAddress(null);
		log.info(str);
		assertThat(str).isEqualTo(null);
	}
	
	@Test
	public void test4() {
		Validate validate = new Validate();
		String str = validate.validateAddress("");
		log.info(str);
		assertThat(str).isEqualTo("");
	}
	
}
