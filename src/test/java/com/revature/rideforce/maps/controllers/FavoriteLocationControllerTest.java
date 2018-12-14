package com.revature.rideforce.maps.controllers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.controllers.FavoriteLocationController FavoriteLocationController} 
 * @author Revature Java batch
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

}
