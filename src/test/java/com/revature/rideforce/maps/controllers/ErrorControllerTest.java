package com.revature.rideforce.maps.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.beans.ResponseError;
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
	public void handleError_isInternalServerError() throws Exception {
		this.mockMvc.perform(get("/error")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
	}

//	@Test
//	public void handleException_isBadRequest() {
//		MvcResult result = mockMvc.perform(get("/location").param("address", "555555")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
//		
//	}
	
	@Test
	public void handleException_is400sError() throws Exception {
		this.mockMvc.perform(get("/route").param("start", "").param("end", "")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
	
//	@Test(expected = MethodArgumentNotValidException.class)
//	public void validateHandleException() throws Exception, MethodArgumentNotValidException {
//		// if the following lines actually throw an exception, then this test will pass
//		mockMvc.perform(get("/location").param("address", "555555")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON));
//	}
	
	// remove this test, test for exception
	@Test(expected=MethodArgumentNotValidException.class)
	public void testRandoException() {

		//getBindingResult() - Return the results of the failed validation
		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenThrow(MethodArgumentNotValidException.class);
	} 


}


