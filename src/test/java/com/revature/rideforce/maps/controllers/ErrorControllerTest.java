package com.revature.rideforce.maps.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.controllers.RouteController;
import com.revature.rideforce.maps.service.RouteService;

@RunWith(SpringRunner.class)
@WebMvcTest(ErrorController.class)
public class ErrorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RouteService routeService;
	
	// THIS TEST SHOULD GO IN ROUTESERVICETEST
	@Test
	public void shouldReturn404() {
		Mockito.when(this.routeService.getRoute("", "")).thenThrow(MethodArgumentNotValidException.class);
	}


}


