package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
import com.netflix.discovery.shared.Application;
import com.revature.rideforce.maps.service.RouteService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RouteServiceTest {
	@Autowired
	private GeoApiContext geoApiContext;
	@MockBean
	private RouteService routeService;
	
	@Test
	public void validate() {
		assertNotNull(routeService);
		assertThat(routeService, instanceOf(RouteService.class));
	}
//	@Test
//	public Route testGet() throws Exception {
//		routeService.getRoute("2925 Rensselaer Ct. Vienna, VA 22181", "2925 Rensselaer Ct. Vienna, VA 22181");
//		return new Route(12714, 9600);
//	}
//	@Test
//	public void testNegativeParams() throws Exception{
//	routeService.getRoute("-80302", "80302");
//}
	

	
}