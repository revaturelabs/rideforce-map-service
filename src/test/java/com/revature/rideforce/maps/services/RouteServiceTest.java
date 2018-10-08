package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
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
		public void testGet() throws Exception {
			final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
			final String end = "11730 Plaza America Dr. Reston, VA";
			final Route route = new Route(12714, 9600);
			final String routeJson = "{ distance: 12714, duration: 9600 }";

			given(routeService.getRoute(start, end)).willReturn(route);
			
			Assert.assertEquals(routeService.getRoute(start, end), route);
		}
	
	@Test
	public void testNegativeParams() throws Exception{
	given(routeService.getRoute("-80302", "80302")).willReturn(null);
	
	Assert.assertEquals(routeService.getRoute("-80302", "80302"), null);
}
	
	

	
}