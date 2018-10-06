package com.revature.rideforce.maps.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.rideforce.maps.ApplicationConfig;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private RouteService routeService;

	@Test
	public void testGet() throws Exception {
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA";
		final Route route = new Route(12714, 9600);
		final String routeJson = "{ distance: 12714, duration: 9600 }";

		given(routeService.getRoute(start, end)).willReturn(route);
		mvc.perform(get("/route").param("start", start).param("end", end)).andExpect(status().isOk())
				.andExpect(content().json(routeJson));
	}

	@Test
	public void testGetBadParams() throws Exception {
		mvc.perform(get("/route")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testNoStartParams() throws Exception {
		mvc.perform(get("/route").param("start", "")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testNegativeStart() throws Exception {
		mvc.perform(get("/route").param("start", "-80302").param("end", "80302"))
		.andExpect(status().isBadRequest());
}
	
	@Test
	public void testMissingEnd() throws Exception {
		mvc.perform(get("/route").param("start", "2925 Rensselaer Ct. Vienna, VA 22181").param("end", "-80302"))
		.andExpect(status().isBadRequest());
}
	
	@Test
	public void testNoEndParams() throws Exception {
		mvc.perform(get("/route").param("start", "2925 Rensselaer Ct. Vienna, VA 22181").param("end", ""))
				.andExpect(status().isBadRequest());
	}
	
	// nozuko added, may delete
	@Test
	public void testEmptyParams() throws Exception {
		mvc.perform(get("/route").param("start", "").param("end", ""))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testPartiallyIncompleteParams() throws Exception {
		final String start = "2925 Rensselaer Ct. Vienna";
		final String end = "11730 Plaza America Dr. Reston";
		final Route route = new Route(12714, 9600);
		final String routeJson = "{ distance: 12714, duration: 9600 }";

		given(routeService.getRoute(start, end)).willReturn(route);
		mvc.perform(get("/route").param("start", start).param("end", end)).andExpect(status().isOk())
				.andExpect(content().json(routeJson)).andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetValidParam11() throws Exception {
		mvc.perform(get("/route").param("start", "[93305@ ").param("end", "[9330@")).andExpect(status().isOk());
	}
	@Test
	public void testGetValidParam12() throws Exception {
		mvc.perform(get("/route").param("start", "[93305]").param("end", "[93305]")).andExpect(status().isOk());
	
	}
}
