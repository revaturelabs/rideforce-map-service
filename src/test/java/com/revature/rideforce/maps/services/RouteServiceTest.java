package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import org.junit.Assert;
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
		Assert.assertThat(routeService, instanceOf(RouteService.class));
	}
	
	@Test
	public void goodRoute() throws Exception {
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA";
		final Route route = new Route(12714, 9600);

		given(routeService.getRoute(start, end)).willReturn(route);
		Route routeTest = routeService.getRoute(start, end);
		
		assertEquals(route,routeTest);
	}
	@Test
	public void testDistance() throws Exception {
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA";
		final Route route = new Route(12714, 9600);

		given(routeService.getRoute(start, end)).willReturn(route);
		Route routeTest = routeService.getRoute(start, end);
		
		assertEquals(routeTest.getDistance(),12714);
	}
	
	@Test
	public void testDuration() throws Exception {
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA";
		final Route route = new Route(12714, 9600);

		given(routeService.getRoute(start, end)).willReturn(route);
		Route routeTest = routeService.getRoute(start, end);
		
		assertEquals(routeTest.getDuration(),9600);
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
	
	

	public void noStartParameter() throws Exception {
		Route badRoute = routeService.getRoute("", "11730 Plaza America Dr. Reston, VA");
		assertNull(badRoute);
	}
	
	@Test
	public void noEndParameters() throws Exception {
		Route badRoute = routeService.getRoute("11730 Plaza America Dr. Reston, VA","");
		assertNull(badRoute);
	}
	
	@Test
	public void incompleteStartParameter() throws Exception {
		Route badRoute = routeService.getRoute("11730 Plaza America Dr.","12160 Sunset Hills Rd, Reston, VA 20190");
		assertNull(badRoute);
	}
	
	@Test 
	public void incompleteEndParameter() throws Exception {
		Route badRoute = routeService.getRoute("12160 Sunset Hills Rd, Reston, VA 20190","12160 Sunset Hills");
		assertNull(badRoute);
	}
	
}
