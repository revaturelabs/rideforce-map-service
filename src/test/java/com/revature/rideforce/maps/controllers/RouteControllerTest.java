package com.revature.rideforce.maps.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.revature.rideforce.maps.controllers.RouteController;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;

/**
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.controllers.RouteController RouteController} 
 * @author Revature Java batch
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private RouteService routeService;

	/**
	 * Test requests with valid address strings, mocking the results.
	 * This test might be a good candidate for converting into an
	 * integration test
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithValidAddressStrings() throws Exception {
		final String start = "2925 Rensselaer Ct. Vienna";
		final String end = "11730 Plaza America Dr. Reston";
		final Route route = new Route(12714, 9600);
		final String routeJson = "{ distance: 12714, duration: 9600 }";

		Mockito.when(routeService.getRoute(start, end)).thenReturn(route);
		mvc.perform(get("/route").param("start", start).param("end", end)).andExpect(status().isOk())
			.andExpect(content().json(routeJson)).andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}
	
	/**
	 * Testing that the RouteController removes white space and special characters
	 * from the passed parameters
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithWhiteSpaceAndSpecialCharacters() throws Exception {
		// stub calls to the RouteService, return a blank object (since RouteController doesn't check it)
		Mockito.when(routeService.getRoute(Mockito.anyString(), Mockito.anyString())).thenReturn(new Route());
		
		mvc.perform(get("/route").param("start", " 93305 ").param("end", " 93305 ")).andExpect(status().isOk());
		mvc.perform(get("/route").param("start", "#93305!").param("end", "@93305&")).andExpect(status().isOk());
		
		mvc.perform(get("/route").param("start", "@93305]").param("end", "[93305@")).andExpect(status().isOk());
		mvc.perform(get("/route").param("start", "[93305]").param("end", "@93305@")).andExpect(status().isOk());
	}
	
	/**
	 * Test requests with missing parameters, empty parameters,
	 * expecting an error (400)
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithMissingAndEmptyParameters() throws Exception {
		mvc.perform(get("/route")).andExpect(status().isBadRequest());
		mvc.perform(get("/route").param("start", "")).andExpect(status().isBadRequest());
		
		mvc.perform(get("/route").param("start", "").param("end", "")).andExpect(status().isBadRequest());
		mvc.perform(get("/route").param("start", "2925 Rensselaer Ct. Vienna, VA 22181").param("end", ""))
				.andExpect(status().isBadRequest());
	}

	/**
	 * Test requests with parameters that contain negative numbers,
	 * expecting an error (400) 
	 * @throws Exception
	 */
	@Test
	public void testGetMappingWithNegativeNumbers() throws Exception {
		mvc.perform(get("/route").param("start", "-80302").param("end", "80302")).andExpect(status().isBadRequest());
		mvc.perform(get("/route").param("start", "2925 Rensselaer Ct. Vienna, VA 22181").param("end", "-80302"))
				.andExpect(status().isBadRequest());
		
	}
}
