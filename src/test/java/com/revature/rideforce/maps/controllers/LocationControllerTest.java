package com.revature.rideforce.maps.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.controllers.LocationController;
import com.revature.rideforce.maps.service.LocationService;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
public class LocationControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private LocationService locationService;

	@Test
	public void testGet() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		final LatLng location = new LatLng(38.95, -77.35);
		final String locationJson = "{ lat: 38.95, lng: -77.35 }";

		given(locationService.getOne(address)).willReturn(location);
		mvc.perform(get("/location").param("address", address)).andExpect(status().isOk())
				.andExpect(content().json(locationJson));
	}

	@Test
	public void testGetBadParams() throws Exception {
		mvc.perform(get("/location")).andExpect(status().isBadRequest());
		mvc.perform(get("/location").param("address", "")).andExpect(status().isBadRequest());
		

	}
	@Test
	public void testGetBadParams2() throws Exception {
		mvc.perform(get("/location").param("address", "junky")).andExpect(status().isOk());

	}
	@Test
	public void testGetBadParams3() throws Exception {
		mvc.perform(get("/location").param("address", "11730")).andExpect(status().isOk());

	}
	@Test
	public void testGetBadParams4() throws Exception {
		mvc.perform(get("/location").param("address", "11730")).andExpect(status().isOk());

	}
	@Test
	public void testGetBadParams5() throws Exception {
		mvc.perform(get("/location").param("Random", "11730")).andExpect(status().isBadRequest());

	}
	@Test
	public void testGetBadParams6() throws Exception {
		mvc.perform(get("/location").param("RANDOM", "11730 Plaza America Dr. Reston, VA")).andExpect(status().isBadRequest());
	}
	@Test
	public void testGetBadParams7() throws Exception {
		mvc.perform(get("/location").param("random", "93305@@")).andExpect(status().isBadRequest());
	}
	@Test
	public void testGetBadParams8() throws Exception {
		mvc.perform(get("/location").param("random", "'93305@@")).andExpect(status().isBadRequest());
	}
	@Test
	public void testGetBadParams9() throws Exception {
		mvc.perform(get("/location").param("random", "[[93305]]")).andExpect(status().isBadRequest());
	}
	@Test
	public void testGetBadParams10() throws Exception {
		mvc.perform(get("/location").param("random", "'[93305]'")).andExpect(status().isBadRequest());
	}
	@Test
	public void testGetBadMapping() throws Exception {
		mvc.perform(get("/loc").param("address", "11730")).andExpect(status().is4xxClientError());
	}
	@Test
	public void testGetBadParamName() throws Exception {
		mvc.perform(get("/loc").param("add", "11730")).andExpect(status().is4xxClientError());
	}
	@Test
	public void testValidZipCodeAddress() throws Exception {
		mvc.perform(get("/location").param("address", "93305")).andExpect(status().isOk());
	}

}
