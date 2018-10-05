package com.revature.rideforce.maps.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.netflix.discovery.shared.Application;
import com.revature.rideforce.maps.configuration.ServiceTestConfiguration;
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
	
	@Test
	public void validate() {
		assertNotNull(routeService);
	}

}