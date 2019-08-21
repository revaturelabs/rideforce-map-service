package com.revature.rideforce.maps.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;

/**
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.service.RouteService RouteService} 
 * @author Revature Java batch
 */
@RunWith(SpringRunner.class)
public class RouteServiceTest {
	/**
	 * Set up configuration for route service, so that it can be autowired for testing.
	 * Necessary setup, especially given that the GeoApiContext makes testing difficult,
	 * and setting it up as a bean here is a necessary part of the application context
	 */
	@TestConfiguration
    static class RouteServiceTestContextConfiguration {	
		@Bean
        public RouteService routeService() {
    		return new RouteService();
        }
		
		@Bean
        public GeoApiContext geoApiContext() {
			final String apiKey = System.getenv("MAPS_API_KEY");
			return new GeoApiContext.Builder().apiKey(apiKey).build();
        }
    }

	@InjectMocks
	@Autowired private RouteService routeService;

	// constants for controlling starting and ending points of a trip
	static final String START = "2925 Rensselaer Ct. Vienna, VA 22181";
	static final String END = "11730 Plaza America Dr. Reston, VA 20190";
	
	/**
	 * Build a DirectionsRoute object to stub the DirectionsApi call
	 */
	@Before
	public void setup() {
		// setup a DirectionsRoute object for a stubbed method to return
		DirectionsRoute directionsResult = new DirectionsRoute();
		DirectionsLeg leg = new DirectionsLeg();
		leg.startAddress = "2925 Rensselaer Ct, Vienna, VA 22181, USA";
		leg.endAddress = "11730 Plaza America Dr, Reston, VA 20190, USA";
		
		// distance and time from a previous call to DirectionsApi
		Distance dis = new Distance();
		dis.inMeters = 14983;
		Duration dur = new Duration();
		dur.inSeconds = 1170;
		
		// build the DirectionsRoute's leg
		leg.distance = dis;
		leg.duration = dur;
		directionsResult.legs = new DirectionsLeg[] { leg };
		
		// mocking to prevent calls to directionsRequest() method from calling DirectionsApi
		Mockito.doReturn(directionsResult).when(Mockito.spy(RouteService.class)).directionsRequest(START, END);
	}

	

	
	/**
	 * The RouteService only pulls out duration and distance values from the
	 * DirectionsApi, and we're mocking those values, so this test is mostly
	 * a sanity check.
	 */
	@Test
	public void testGetRouteWithCorrectAddresses(){
		assertEquals(new Route(14983, 1170), routeService.getRoute(START, END));
	}

	/**
	 * This is adapted from a legacy test, to see if the DirectionsApi would return a null 
	 * route. However, the RouteService doesn't return null on failure, it returns an empty Route(),
	 * and the DirectionsApi is quite good at finding a possible address based on most strings
	 */
//	@Test
//	public void testGetRouteWithIncompleteParameters() {
//		 //even with partial addresses, the DirectionsApi still finds a route if we don't stub it
//		assertNotEquals(new Route(), routeService.getRoute("Plaza America Dr.", "12160 Sunset Hills Rd, Reston, VA 20190"));
//		assertNotEquals(new Route(), routeService.getRoute("Plaza America Dr.", "Sunset Hills"));
//		assertNotEquals(new Route(), routeService.getRoute("11730 Plaza America Dr.", "12160 Sunset Hills"));	
//	}
	
	/**
	 * It was proving difficult to stub the method that calls the DirectionsApi to
	 * return a null value. Instead, this test uses the interrupt flag on its thread 
	 * to trick the DirectionsApi call into throwing an exception, so that it fails,
	 * catches the exception, and returns null
	 */
	@Test
	public void testGetRouteWithException() {
		// sets the current thread to interrupted 
		Thread.currentThread().interrupt();
		
		// when the call to DirectionsApi throws an exception, the service returns an empty Route object
		assertEquals(new Route(), routeService.getRoute(START, END));
		
		// need to reset the interrupted status on the thread, so that later tests run correctly
		Thread.interrupted();
	}
}
