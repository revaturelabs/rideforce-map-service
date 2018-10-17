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
//		assertNotNull(locationService);
//		Assert.assertThat(locationService, instanceOf(LocationService.class));
//		assertNotNull(autoLocationService);
//		Assert.assertThat(autoLocationService, instanceOf(LocationService.class));
//		assertNotNull(autoGeoApiContext);
	}
	
	@Test
	public void instanceValidate() {
//		assertNotNull(realLocationService1);
//		assertNotNull(realLocationService2);
//		assertNotNull(realLocationService2.getGeoApiContext());
//		assertNotNull(realGeo);
	}
	
	@Test
	public void noEndParameters(){
//		LatLng badLocation = realLocationService2.getOne("");
//		assertNull(badLocation);
	}
	
	@Test 
	public void validParameterGetOne() {
		final String address = "2925 Rensselaer Ct. Vienna, VA 22181";
		String location = "{distance:3520, duration:435}";
		LatLng testLatLng= autoLocationService.getOne(address);
		assertEquals(null, testLatLng);
	}
	
	
	
}
