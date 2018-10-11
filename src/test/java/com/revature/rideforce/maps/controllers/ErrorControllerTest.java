package com.revature.rideforce.maps.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.controllers.RouteController;
import com.revature.rideforce.maps.service.RouteService;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@AutoConfigureMockMvc
@WebMvcTest(ErrorController.class)
public class ErrorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHandleError() throws Exception {
		this.mockMvc.perform(get("/error")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
	}

//	@Test
//	public void testHandleException() throws Exception {
//		this.mockMvc.perform(get("/location")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
//	}
	
//	@Test(expected = MethodArgumentNotValidException.class)
//	public void validateHandleException() throws Exception, MethodArgumentNotValidException {
//		// if the following lines actually throw an exception, then this test will pass
//		mockMvc.perform(get("/location").param("address", "555555")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON));
//	}

}


