package com.revature.rideforce.maps.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.discovery.shared.Application;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RouteServiceTest {

	@MockBean
	private RouteService routeService;
	
	@Before
	public void validate() {
		assertNotNull(routeService);
		assertThat(routeService, instanceOf(RouteService.class));
	}
	@Test
	public Route testGet() throws Exception {
		routeService.getRoute("2925 Rensselaer Ct. Vienna, VA 22181", "2925 Rensselaer Ct. Vienna, VA 22181");
		return new Route(12714, 9600);
	}
	@Test
	public void testNegativeParams() throws Exception{
	routeService.getRoute("-80302", "80302");
}
}