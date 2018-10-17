package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import com.netflix.discovery.shared.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.repository.LocationRepository;
import com.revature.rideforce.maps.service.LocationService;
import com.revature.rideforce.maps.service.RouteService;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Configuration
@ComponentScan(basePackages = {"com.revature.rideforce.maps.service"})
public class LocationServiceTest {
	@Autowired
	GeoApiContext autoGeoApiContext;
	@MockBean
	LocationService locationService;
	@MockBean
	GeoApiContext geoApiContext;
	@MockBean
	LocationRepository locationRepo;
	@Autowired
	LocationService autoLocationService;
	public LocationService realLocationService1= new LocationService();
	static final String apiKey= System.getenv("MAPS_API_KEY");
	static final GeoApiContext realGeo = new GeoApiContext.Builder()
		    .apiKey(apiKey)
		    .build();
	public LocationService realLocationService2= new LocationService(realGeo);
	
	@Before
	public void mockBeanValidate() {
		//mock bean location service
		assertNotNull(locationService);
		Assert.assertThat(locationService, instanceOf(LocationService.class));
		//autowired location service and GeoApiContext
		assertNotNull(autoLocationService);
		Assert.assertThat(autoLocationService, instanceOf(LocationService.class));
		assertNotNull(autoGeoApiContext);
	}
	
	@Test
	public void instanceValidate() {
		assertNotNull(realLocationService1);
		assertNotNull(realLocationService2);
		assertNotNull(realLocationService2.getGeoApiContext());
		assertNotNull(realGeo);
	}
	
	@Test
	public void noEndParameters(){
		LatLng badLocation = locationService.getOne("");
		assertNull(badLocation);
	}
	
//	@Test
//	public void locationTest() {
//		String address = "12160 Sunset Hills Rd, Reston, VA 20190";
//		CachedLocation location = locationRepo.findByAddress(address);
//		assertEquals(location, );
//	}
//	
}
