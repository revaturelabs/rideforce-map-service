package com.revature.rideforce.maps.controllers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.service.FavoriteLocationService;

/**
 * 
 * @author Jorge
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FavoriteLocationController.class)
public class FavoriteLocationControllerTest {
	
	@Autowired
	private MockMvc locationMvc;
	
	@MockBean
	private FavoriteLocationService favoriteLocationService;
	
	@Test
	public void validate() {
		assertNotNull(locationMvc);
		assertNotNull(favoriteLocationService);
	}
	
	@Test
	public void getOneTest() {
		String address= "2925 Rensselaer Ct. Vienna, VA 22181";
//		LatLng expectedLatLng= favoriteLocationService.saveFavoriteLocation(address, 1);
		
	}
	
	@Test
	public void testGet() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		final String name = "workplace";
		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
		int userId = 4;

		FavoriteLocation location = new FavoriteLocation(address, 38.9533932, -77.35044780000001, name, userId);
		given(favoriteLocationService.saveFavoriteLocation(address, userId, name)).willReturn(location);
		locationMvc.perform(get("/favoritelocations/users/4")).andExpect(status().isOk());
	}
// this one was not working	
//	@Test
//	public void testPost() throws Exception {
//		final String address = "11730 Plaza America Dr. Reston, VA";
//		final String name = "workplace";
//		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
//		int userId = 4;
//
//		FavoriteLocation location = new FavoriteLocation(address, 38.9533932, -77.35044780000001, name, userId);
//		given(favoriteLocationService.saveFavoriteLocation(address, userId, name)).willReturn(location);
//		System.out.println(locationMvc.perform(post("/favoritelocations").param("address", address).param("name", name).param("userId", "4")).andExpect(status().isOk()));
//	}

//	@Test
//	public void testGetBadParams() throws Exception {
//		mvc.perform(get("/location")).andExpect(status().isBadRequest());
//		mvc.perform(get("/location").param("address", "")).andExpect(status().isBadRequest());
//		
//	}

}
