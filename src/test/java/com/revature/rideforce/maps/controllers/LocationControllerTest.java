package com.revature.rideforce.maps.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.controllers.LocationController;
import com.revature.rideforce.maps.service.LocationService;

/**
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.controllers.LocationController LocationController} 
 * @author Revature Java batch
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
@WebAppConfiguration
public class LocationControllerTest {
	@Autowired private WebApplicationContext wac;
	
	private MockMvc mvc;

	@MockBean private LocationService locationService;
	
	/**
	 * Setup before tests to build the mocked mvc, establish 
	 * mocking for the LocationService responses 
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		// initialize the mocked mvc so it doesn't need to be initialized inside each test
	    this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    
	    /* because the LocationController doesn't check the object the LocationService
	     * returns, it doesn't matter what we return, and this could be null, 
	     * or new object, but we'll build an object to send back anyway.
	     */
	    CachedLocation cl = new CachedLocation();
	    cl.setLatitude(35.3964972);
	    cl.setLongitude(-118.9819731);
	    
	    // mock the location service, stub the getLocation methods to return an object when called.
	    Mockito.mock(LocationService.class);
//	    Mockito.when(locationService.getLocationByAddress(Mockito.anyString())).thenReturn(cl);
	    Mockito.when(locationService.getLocationByZipCode(Mockito.anyString())).thenReturn(cl);
	}
	
	@Test
	public void givenWac_whenServletContext_thenItProvidesLocationController() {
	    ServletContext servletContext = wac.getServletContext();
	    
	    // verify testing configuration is correct
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("locationController"));
	}
	
	/**
	 * Test requests with incorrect mapping, expecting an error
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithWrongMapping() throws Exception {
		mvc.perform(get("/loc").param("add", "11730")).andExpect(status().is4xxClientError());
		mvc.perform(get("/loc").param("address", "11730")).andExpect(status().is4xxClientError());
	}

	/**
	 * Test requests with incorrect mapping, expecting an error (400)
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithEmptyParameters() throws Exception {
		mvc.perform(get("/location")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("address", "")).andExpect(status().isBadRequest());
	}
	
	/**
	 * Test requests with incorrect parameter name with various values, 
	 * expecting an error (400)
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithIncorrectParameterName() throws Exception {
		mvc.perform(get("/location").param("Random", "11730")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("RANDOM", "11730 Plaza America Dr., Reston, VA")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("random", "93305@@")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("random", "'93305@@")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("random", "[[93305]]")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("random", "'[93305]'")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("random", "\"93305\"")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("random", "'93305'")).andExpect(status().isBadRequest());
	}
	
	/**
	 * Test requests with  a zip code of incorrect length, 
	 * expecting an error (400)
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithInvalidZipCodeAddress() throws Exception {
		mvc.perform(get("/location").param("address", "933056")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("address", "933")).andExpect(status().isBadRequest());
	}

	/**
	 * Test requests with correct parameters as zip codes
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithValidParametersAsZipCode() throws Exception {
		// unformatted zip codes
		mvc.perform(get("/location").param("address", "11730")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "93305")).andExpect(status().isOk());
		
		// test that normalizing address parameter value works correctly
		mvc.perform(get("/location").param("address", "93305withsometext")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "'93305'")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "'93305")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", " 93305")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", " 93305 ")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "93305 ")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "93305@")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "\"93305\"")).andExpect(status().isOk());
		
		// these particular tests pass here, but fail with Postman, seemingly because of the brackets
		mvc.perform(get("/location").param("address", "[93305@")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", " [93305@ ")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "[93305@ ")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "[93305]")).andExpect(status().isOk());
	}
	
	/**
	 *  Test requests with correct parameters as full address strings
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithValidParametersAsAddressString() throws Exception {
		// send a valid address
		mvc.perform(get("/location").param("address", "junky")).andExpect(status().isOk());
		mvc.perform(get("/location").param("address", "11730 Plaza America Dr., Reston, VA")).andExpect(status().isOk());
	}
}