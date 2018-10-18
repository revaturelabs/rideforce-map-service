package com.revature.rideforce.maps.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RunWith(SpringRunner.class)
@WebMvcTest(ErrorController.class)
public class ErrorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void handleError_isInternalServerError() throws Exception {
		this.mockMvc.perform(get("/error")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
	}
	
	@Test
	public void handleException_is400sError() throws Exception {
		this.mockMvc.perform(get("/route").param("start", "").param("end", "")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void validateHandleException() throws Exception, MethodArgumentNotValidException {
		// if the following lines actually throw an exception, then this test will pass
		mockMvc.perform(get("/location").param("address", "****")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
	
}


