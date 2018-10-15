package com.revature.rideforce.maps.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.maps.model.LatLng;

import com.revature.rideforce.maps.service.FavoriteLocationService;

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
		LatLng expectedLatLng= favoriteLocationService.getOne(address, 1);
		
	}

}
