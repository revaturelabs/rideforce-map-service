package com.revature.rideforce.maps.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;
import com.revature.rideforce.maps.service.LocationService;

/**
 * Unit tests for LocationService
 * @author Java batch
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@Transactional
public class LocationServiceTest {
	
	/**
	 * creates a mock of location service
	 */
	@TestConfiguration
    static class LocationServiceTestContextConfiguration {	
		@Bean
        public LocationService locationService() {
    		return new LocationService();
        }
    }
	
	/**
	 * Injecting the LocationService
	 */
	@Autowired
	private LocationService locationService;
	
	/**
	 * this creates a Mock for the LocationRepository which can be used to bypass the call 
	 * to the actual LocationRepository
	 */
	@MockBean
    private LocationRepository locationRepository;
	
	/**
	 * this creates a Mock for the GeoApiContext which can be used to bypass the call 
	 * to the actual GeoApiContext
	 */
	@MockBean
	public GeoApiContext geoApiContext;
	
	static final String apiKey= System.getenv("MAPS_API_KEY");
	
	static final GeoApiContext realGeo = new GeoApiContext.Builder()
			.apiKey(apiKey)
			.build();
	
	@Before
	public void init() {
	    CachedLocation loc = new CachedLocation("11610 Plaza America Dr, Reston, VA 20190", 38.9520418, -77.3472637);
	    Mockito.when(locationRepository.findByAddress(loc.getAddress())).thenReturn(loc);
		
		CachedLocation cloc = new CachedLocation();
		cloc.setAddress("11610 Plaza America Dr, Reston, VA 20190");
		cloc.setLatitude(38.9520418);
		cloc.setLongitude(-77.3472637);
	}
	
//	/**
//	 * test getOne method from LocationService
//	 */
//	@Test
//	 public void getOne_Test() {
//		 LatLng latlng = locationService.getOne("2925 Rensselaer Ct. Vienna, VA 22181");
//		 LatLng expectedLatLng = new LatLng(38.9520418, -77.3472637);
//		 Assertions.assertThat(latlng).isEqualTo(expectedLatLng);	 
//	 }
	
//    @Test
//	public void getOne_Test2() throws Exception {
//		final String address = "11730 Plaza America Dr. Reston, VA";
//		final String name = "workplace";
//		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
//		int userId = 2;
//		CachedLocation cloc = new CachedLocation();
//		cloc.setAddress(address);
//		cloc.setLatitude(38.9533932);
//		cloc.setLongitude(-77.35044780000001);
//		locationService.geoApiContext = realGeo;
//	    Mockito.when(this.locationService.getOne(address)).thenReturn(cloc);
//
//		LatLng f = locationService.getOne(address);
//
//		
//		CachedLocation location = new CachedLocation(address, 38.9533932, -77.35044780000001);
//		Assertions.assertThat(f).isEqualTo(location);
//		System.out.println(f);
//	}
	
}