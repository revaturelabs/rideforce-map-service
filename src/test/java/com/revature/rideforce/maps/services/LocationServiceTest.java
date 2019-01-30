package com.revature.rideforce.maps.services;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;
import com.revature.rideforce.maps.service.LocationService;

/**
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.service.LocationService LocationService} 
 * @author Revature Java batch
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { GeoApiContext.class })
public class LocationServiceTest {
	// Set up configuration for location service, so that it can be autowired for testing
	@TestConfiguration
    static class LocationServiceTestContextConfiguration {	
		@Bean
        public LocationService locationService() {
    		return new LocationService();
        }
		// even though the method that uses GeoApiContext is mocked, 
		// this bean is a necessary part of the application context
		@Bean
        public GeoApiContext geoApiContext() {
			final String apiKey = System.getenv("MAPS_API_KEY");
			GeoApiContext realGeo = new GeoApiContext.Builder().apiKey(apiKey).build();
    		return realGeo;
        }
    }
	
	@InjectMocks
	@Autowired private LocationService locationService;
	
	@MockBean private LocationRepository locationRepository;
	
	@Before
	public void init() {
		// this particular call only occurs once, in getOneZip()
	    CachedLocation cl = new CachedLocation("11610 Plaza America Dr, Reston, VA 20190", 38.9520418, -77.3472637);
	    Mockito.when(locationRepository.findByAddress(cl.getAddress())).thenReturn(cl);
	}
	
    @Test
<<<<<<< HEAD
	public void getOne_Test() throws Exception {
		final String address = "11730 Plaza America Dr. Reston";
		final String state = "VA"; 
		CachedLocation cloc = new CachedLocation();
		LatLng clocLatLng = new LatLng(38.9533932, -77.35044780000001);
		cloc.setAddress(address);
		cloc.setStateCode(state);
		cloc.setLatitude(38.9533386);
		cloc.setLongitude(-77.35047829999999);
		locationService.setGeoApiContext(realGeo);
	    Mockito.when(this.locationService.getOne(address, cloc.getLocation())).thenReturn(cloc);

		CachedLocation l = locationService.getOne(address, cloc.getLocation());
	
		System.out.println(l.toString());
		
		CachedLocation location = new CachedLocation(cloc.getAddress(), cloc.getLatitude(), cloc.getLongitude());
		
		System.out.println(location.toString());
		Assertions.assertThat(l).isEqualTo(location);
=======
	public void getLocationByAddress() throws Exception {
		// initialize results object, testing object
		CachedLocation rs = new CachedLocation();	// results object
		CachedLocation cl = new CachedLocation();	// testing object
		
    	// lat and lng pulled from google request for this particular street address
		LatLng latLng = new LatLng(38.9533386, -77.35047829999999);
		final String ADDRESS = "11730 Plaza America Dr., Reston, VA";
		final String STREET = "11730 Plaza America Dr.";
		final String CITY = "Reston";
		final String STATE = "VA";
		final String ZIP = "20190";
		
    	// set up address objects (SCSZ stands for Street/City/State/Zip, "_" for malformed state code)
		cl.setLatitude(latLng.lat);
		cl.setLongitude(latLng.lng);
		cl.setAddress(STREET);
		
		// build result object expected by location service, so that it can be mocked
		GeocodingResult geocodingResult = new GeocodingResult();
		geocodingResult.geometry = new Geometry();
		geocodingResult.geometry.location = latLng;
		GeocodingResult[] geocodingResults = new GeocodingResult[] { geocodingResult };

		// .when(Mockito.spy() allows us to mock the location service's call to getocodeRequest()
		// .doReturn() returns the results object we expect to receive, should the method actually be called
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET);
		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET));
		
		cl.setCity(CITY);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY);
		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY));
		
		cl.setStateCode(STATE);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + "," + STATE);
		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE));
		
		cl.setStateCode(null);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",Virginia");
		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",Virginia"));
		
		cl.setZip(ZIP);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",Virginia," + ZIP);
		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",Virginia," + ZIP));
		
		cl.setStateCode(STATE);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + "," + STATE + "," + ZIP);
		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + "," + ZIP));
		
//		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(ADDRESS);
//		rs = locationService.getLocationByAddress(ADDRESS);
//		Assert.assertEquals(clSCS, rs);
		//Assertions.assertThat(rs).isEqualTo(cl.getLocation());
>>>>>>> 89355595012ba9d8d32d8b343b5e23081538feff
	}

	//LatLng latLng = new LatLng(35.39649720,-118.98197310);	// 93305 zip code (with extra 0 on lat and lng)
}