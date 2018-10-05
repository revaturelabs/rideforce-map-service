package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
//@ContextConfiguration(classes=ServiceTestConfiguration.class)
//@DataJpaTest
public class RouteServiceTest {
//	
//	@Autowired
//	private  TestEntityManager testEntityManager; 

	@MockBean
	private RouteService routeService;
	
	@MockBean
	private Route route;
	
	@Test
	@Before
	public void validate() {
		assertNotNull(routeService);
		assertTrue(routeService instanceof RouteService);
	}
	
	@Test
	public void goodRoute() {
		Route correctRoute= routeService.getRoute("12160 Sunset Hills Rd, Reston, VA 20190", "905 Herndon Pkwy A, Herndon, VA 20170");
//		assertTrue(correctRoute instanceof Route);
//		assertThat(correctRoute, instanceOf(Route.class));
		System.out.println(correctRoute.getDistance());
//		assertTrue(correctRoute.getDistance()==3520);
	}
	
	@Test
	public void noStartParameter() {
		Route badRoute = routeService.getRoute("", "11730 Plaza America Dr. Reston, VA");
		assertNull(badRoute);
	}
	
	@Test
	public void noEndParameters() {
		Route badRoute = routeService.getRoute("11730 Plaza America Dr. Reston, VA","");
		assertNull(badRoute);
	}
	
	@Test
	public void incompleteStartParameter() {
		Route badRoute = routeService.getRoute("11730 Plaza America Dr.","12160 Sunset Hills Rd, Reston, VA 20190");
		assertNull(badRoute);
	}
	
	@Test 
	public void incompleteEndParameter() {
		Route badRoute = routeService.getRoute("12160 Sunset Hills Rd, Reston, VA 20190","12160 Sunset Hills");
		assertNull(badRoute);
	}
	
}