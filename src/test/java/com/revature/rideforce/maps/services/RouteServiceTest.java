package com.revature.rideforce.maps.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.repository.LocationRepository;
import com.revature.rideforce.maps.service.RouteService;

@RunWith(SpringRunner.class)
public class RouteServiceTest {
	
	@MockBean
	private RouteService routeService;
	
	@Test
	public void testGet() throws Exception {
		given(routeService.getRoute("2925 Rensselaer Ct. Vienna, VA 22181", "2925 Rensselaer Ct. Vienna, VA 22181")).willReturn(new Route(12714, 9600));
		
	}
	
//	@Test
//	public void testNoParams() throws Exception {
//		given(routeService.getRoute("", "")).willThrow(status().isBadRequest();
//	}
	
	@Test
	public void testNegativeNumbers1() throws Exception{
		routeService.getRoute("-5", "-5");
	}
	
	@Test
	public void testNegativeNumbers2() throws Exception{
		routeService.getRoute("2925 Rensselaer Ct. Vienna, VA 22181", "-5");
	}
	
	@Test
	public void testNegativeNumbers3() throws Exception{
		routeService.getRoute("-5", "2925 Rensselaer Ct. Vienna, VA 22181");
	}
	
	
	
}
