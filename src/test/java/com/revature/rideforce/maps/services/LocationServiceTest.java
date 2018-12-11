package com.revature.rideforce.maps.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;
import com.revature.rideforce.maps.service.LocationService;

/**
 * Unit tests for LocationService
 * @author Java batch
 */
@RunWith(SpringRunner.class)
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
	private GeoApiContext geoApiContext;
	
	static final String apiKey = System.getenv("MAPS_API_KEY");
	
	static final GeoApiContext realGeo = new GeoApiContext.Builder()
			.apiKey(apiKey)
			.build();
	
	@Before
	public void init() {
	    CachedLocation loc = new CachedLocation("11610 Plaza America Dr, Reston, VA 20190", 38.9520418, -77.3472637);
	    Mockito.when(locationRepository.findByAddress(loc.getAddress())).thenReturn(loc);
	}
	
	/**
	 * get a location
	 * @throws Exception
	 */
    @Test
	public void getOne_Test() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		CachedLocation cloc = new CachedLocation();
		LatLng clocLatLng = new LatLng(38.9533932, -77.35044780000001);
		cloc.setAddress(address);
		cloc.setLatitude(38.9533932);
		cloc.setLongitude(-77.35044780000001);
		locationService.setGeoApiContext(realGeo);
	    Mockito.when(this.locationService.getOne(address, cloc.getLocation()));

		CachedLocation l = locationService.getOne(address, cloc.getLocation());
		
		CachedLocation location = new CachedLocation(address, 38.9533932, -77.35044780000001);
		Assertions.assertThat(l).isEqualTo(location.getLocation());
	}
	
}