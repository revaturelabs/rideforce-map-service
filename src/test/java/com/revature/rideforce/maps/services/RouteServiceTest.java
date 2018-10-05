package com.revature.rideforce.maps.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void noStartParameter() {
		Route badRoute = routeService.getRoute("", "11730 Plaza America Dr. Reston, VA");
		assertNull(badRoute);
	}
	
	@Test
	public void noEndParameters() {
		Route badRoute = routeService.getRoute("11730 Plaza America Dr. Reston, VA","");
		assertNull(badRoute);
	}
}