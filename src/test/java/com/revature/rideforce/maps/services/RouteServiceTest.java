package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import com.netflix.discovery.shared.Application;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.configuration.TestConfiguration;
import com.revature.rideforce.maps.service.RouteService;


@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Configuration
@ComponentScan(basePackages = {"com.revature.rideforce.maps.service"})
public class RouteServiceTest {
	//Test Covrage completed with Maven ECLEmma Plugin
	
	@MockBean
	GeoApiContext geoApiContext;

	@MockBean
	RouteService routeService;
	
//	declared values
	//eclEmma coverage only appears after the instantiating the 
	//service and then using those methods
	public RouteService realRouteService1= new RouteService();
	
	//get system variables
	static final String apiKey= System.getenv("MAPS_API_KEY");
	static final GeoApiContext realGeo = new GeoApiContext.Builder()
		    .apiKey(apiKey)
		    .build();
	
	public RouteService realRouteService2= new RouteService(realGeo);

	@Before
	public void mockBeanValidate() {
		//mock bean route service
		assertNotNull(routeService);
		Assert.assertThat(routeService, instanceOf(RouteService.class));

	}
	
	//tests the initialized instances of the routeService and GeoContextApi
	@Before
	public void instanceValidate() {
		assertNotNull(realRouteService1);
		assertNotNull(realRouteService2);
		assertNotNull(realRouteService2.getGeoApiContext());
		assertNotNull(realGeo);
	}
	
	
	@Test
	public void getGeoApi() {
		routeService.setGeoApiContext(geoApiContext);
		given(routeService.getGeoApiContext()).willReturn(geoApiContext);
		GeoApiContext geo=routeService.getGeoApiContext();
		Assert.assertThat(geo,instanceOf(GeoApiContext.class));
}
	@Test
	public void setGeoApi() {
		realRouteService1.setGeoApiContext(realGeo);
		GeoApiContext setter=realRouteService1.getGeoApiContext();
		Assert.assertThat(setter,instanceOf(GeoApiContext.class));
	}
	/////////////////////////////////////
	@Test
	public void testDuration() {
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA 20190";
		final Route route = new Route(14988, 1166);
		Route routeTest = realRouteService2.getRoute(start, end);
		assertEquals(1166,routeTest.getDuration());
	}
	
	@Test
	public void testnegativeParameters() {
		Route negRoute= realRouteService2.getRoute("-80302", "80302");
		Route emptyRoute= new Route();
		assertEquals(emptyRoute, negRoute);
	}
	
	@Test
	public void noEndParameters(){
		Route badRoute = realRouteService2.getRoute("11730 Plaza America Dr. Reston, VA","");
		Route emptyRoute= new Route();
		assertEquals(emptyRoute, badRoute);
	}
	
//	@Test
//	public void incompleteStartParameter() {
//		Route badRoute = realRouteService2.getRoute("11730 Plaza America Dr.","12160 Sunset Hills Rd, Reston, VA 20190");
//		Route emptyRoute= new Route();
//		assertEquals(emptyRoute, badRoute);
//	}
	
	@Test 
	public void incompleteEndParameter() {
		Route badRoute = realRouteService2.getRoute("12160 Sunset Hills Rd, Reston, VA 20190","12160 Sunset Hills");
		Route emptyRoute= new Route();
		assertEquals(emptyRoute, badRoute);
	}
	
	//doesn't show up under the ECLEmma coverage
	
	@Test
	public void goodRouteMockBean(){
		routeService.setGeoApiContext(geoApiContext);
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA 20190";
		Route route = new Route(14988, 1166);
		given(routeService.getRoute(start, end)).willReturn(route);
		Route routeTest = routeService.getRoute(start, end);
		assertEquals(route,routeTest);
	}
	@Test
	public void testDistanceMockbean(){
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA 20190";
		final Route route = new Route(14988, 1166);
		given(routeService.getRoute(start, end)).willReturn(route);
		Route routeTest = routeService.getRoute(start, end);
		assertEquals(14988, routeTest.getDistance());
	}
	
	@Test
	public void testDurationMockBean() {
		final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
		final String end = "11730 Plaza America Dr. Reston, VA 20190";
		final Route route = new Route(14988, 1166);
		given(routeService.getRoute(start, end)).willReturn(route);
		Route routeTest = routeService.getRoute(start, end);
		assertEquals(routeTest.getDuration(),1166);
	}
		
		@Test
		public void testGetMockBean(){
			final String start = "2925 Rensselaer Ct. Vienna, VA 22181";
			final String end = "11730 Plaza America Dr. Reston, VA";
			final Route route = new Route(14988, 1166);
			given(routeService.getRoute(start, end)).willReturn(route);
			assertEquals(routeService.getRoute(start, end), route);
			Assert.assertEquals(routeService.getRoute(start, end), route);
		}	

	@AfterClass
	public static void resourceCloser() {
		//shutdown the GeoApiContext
		realGeo.shutdown();
	}
	
}
