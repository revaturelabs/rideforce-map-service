package com.revature.rideforce.maps.services;

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
public class LocationServiceTest {
	/**
	 * Set up configuration for location service, so that it can be autowired for testing.
	 * Necessary setup, especially given that the GeoApiContext makes testing difficult,
	 * and setting it up as a bean here is a necessary part of the application context
	 */
	@TestConfiguration
    static class LocationServiceTestContextConfiguration {	
		@Bean
        public LocationService locationService() {
    		return new LocationService();
        }
		
		@Bean
        public GeoApiContext geoApiContext() {
			final String apiKey = System.getenv("MAPS_API_KEY");
			return new GeoApiContext.Builder().apiKey(apiKey).build();
        }
    }
	
	@InjectMocks
	@Autowired private LocationService locationService;
	
	@MockBean private LocationRepository locationRepository;

	// set up some constants for testing
	// lat and lng pulled from google request for this particular street address
	private static final LatLng LATLNG = new LatLng(38.9533386, -77.35047829999999);
	private static final String ADDRESS = "11730 Plaza America Dr., Reston, VA";
	private static final String STREET = "11730 Plaza America Dr.";
	private static final String CITY = "Reston";
	private static final String STATE = "VA";
	private static final String ZIP = "20190";
	
	/**
	 * Setup before tests to declare objects, establish some mocking,
	 * and stub certain methods for testing.
	 */
	@Before
	public void setup() {				  
		// build result object expected by location service, so that it can be mocked
		GeocodingResult geocodingResult = new GeocodingResult();
		geocodingResult.geometry = new Geometry();
		geocodingResult.geometry.location = LATLNG;
		GeocodingResult[] geocodingResults = new GeocodingResult[] { geocodingResult };
		
		// set up the mocking the locaiton repository, to stub the method for saves to the database
		Mockito.mock(LocationRepository.class);
		// when the tested code attempts to save any CachedLocation object, return null
	    Mockito.when(locationRepository.save(Mockito.any(CachedLocation.class))).thenReturn(null);
		
		// .when(Mockito.spy()) allows us to mock the location service's call to getocodeRequest()
		// .doReturn() returns the results object we expect to receive, should the method actually be called
	    // need to use doReturn() and spy() because the Geo API is difficult to mock/stub normally
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + "," + STATE);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",Virginia");
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",Virginia," + ZIP);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",12");
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",12,123");
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + ",12," + ZIP);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + "," + STATE + "," + ZIP);
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + "," + STATE + ",123");
		Mockito.doReturn(geocodingResults).when(Mockito.spy(LocationService.class)).geocodeRequest(STREET + "," + CITY + "," + STATE + "," + ZIP + ",Extra");
	}
	
	/**
	 * When the passed address string does not appear in the database, and is not a zip code.
	 * There are a number of configurations to check, based on the information contained 
	 * in the address string passed to LocationService.getLocationByAddress() method.
	 * This runs multiple assertions in one test, because otherwise setup and teardown
	 * for each individual test would be excessive.
	 * 
	 * @throws Exception
	 */
    @Test
	public void getLocationByAddressNotInDatabase() throws Exception {
		// initialize results object, testing object
		CachedLocation rs = new CachedLocation();	// results object
		CachedLocation cl = new CachedLocation();	// testing object
		cl.setLatitude(LATLNG.lat);
		cl.setLongitude(LATLNG.lng);
		cl.setAddress(STREET);
		
		// set up the mocking for calls to the database, so that it always returns null
	    Mockito.when(locationRepository.findByAddress(Mockito.anyString())).thenReturn(null);
	    Mockito.when(locationRepository.findByLatitude(Mockito.anyDouble())).thenReturn(null);
	    Mockito.when(locationRepository.findByLongitude(Mockito.anyDouble())).thenReturn(null);

		// TESTING BEGINS
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET));
		
		cl.setCity(CITY);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY));
		
		cl.setStateCode(STATE);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE));
		
		// when the sate code is the wrong length (the expected field value is null)
		cl.setStateCode(null);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",Virginia"));
		
		// add a correct zip code
		cl.setZip(ZIP);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",Virginia," + ZIP));
		
		// when the sate code is the correct length, but not letters (the expected field value is null)
		// with no state code
		cl.setZip(null);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",12"));

		// add an incorrect zip code
//		rs = locationService.getLocationByAddress(STREET + "," + CITY + ",12,123");
		// for this, running into the issue that sometimes the lat & lng values are not identical
		// so test with a delta, so that we aren't checking every point of precision in the double		
		Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.005);
		
		cl.setZip(ZIP);
//		rs = locationService.getLocationByAddress(STREET + "," + CITY + ",12," + ZIP);
		// again requires the delta, because google maps is returning slightly different coordinates		
		Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.005);
		
		// when state code is correct 
		cl.setStateCode(STATE);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + "," + ZIP));
		
		// when the zip code is the wrong length, but everything else is correct
		cl.setZip(null);
//		rs = locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + ",123");
		// again requires the delta, because google maps is returning slightly different coordinates		
		Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.005);
		
		// when the address breaks down into too many fields, expect null;
//		Assert.assertNull(locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + "," + ZIP + ",Extra"));
	
		/* this clears the interrupted flag on the testing thread. 
		 * this is important, because the final test in this method triggers: 
		 * 		Thread.currentThread().interrupt();
		 * (resetting this flag ourselves appears to be bad practice, but the most current 
		 * version of JUnit4 seems to have fixed this issue only about 10 days ago, so
		 * this might eventually need to be phased out)
		 */
		Thread.interrupted();
    }
    
    /**
	 * When the passed address string exists in the database, and is not a zip code
	 * (repository respose is mocked--the address doesn't need to be in the real database).
	 * There are a number of configurations to check, based on the information contained 
	 * in the address string passed to LocationService.getLocationByAddress() method.
	 * This runs multiple assertions in one test, because otherwise setup and teardown
	 * for each individual test would be excessive.
	 * 
	 * @throws Exception
	 */
    @Test
	public void getLocationByAddressInDatabase() throws Exception {
		// initialize results object, testing object
		CachedLocation rs = new CachedLocation();	// results object
		CachedLocation cl = new CachedLocation();	// testing object
		cl.setLatitude(LATLNG.lat);
		cl.setLongitude(LATLNG.lng);
		cl.setAddress(STREET);
		
		// set up the mocking for calls to the database, so that it always returns the correct object
	    CachedLocation db = new CachedLocation(ADDRESS, LATLNG);
	    Mockito.when(locationRepository.findByAddress(Mockito.anyString())).thenReturn(db);
	    Mockito.when(locationRepository.findByLatitude(Mockito.anyDouble())).thenReturn(db);
	    Mockito.when(locationRepository.findByLongitude(Mockito.anyDouble())).thenReturn(db);

		// TESTING BEGINS
		// .when(Mockito.spy() allows us to mock the location service's call to getocodeRequest()
		// .doReturn() returns the results object we expect to receive, should the method actually be called
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET));
		
		cl.setCity(CITY);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY));
		
		cl.setStateCode(STATE);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE));
		
		// when the sate code is the wrong length 
		// (the expected field value is the database statecode field: in this case, "VA")
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",Virginia"));
		
		// when the sate code is the correct length, but not letters (the expected field value is null)
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",12"));
		
		// add a correct zip code
		cl.setZip(ZIP);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + ",Virginia," + ZIP));

		// add an incorrect zip code
//		rs = locationService.getLocationByAddress(STREET + "," + CITY + ",12,123");
		// for this, running into the issue that sometimes the lat & lng values are not identical
		// so test with a delta, so that we aren't checking every point of precision in the double		
		Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.005);
		
		cl.setZip(ZIP);
//		rs = locationService.getLocationByAddress(STREET + "," + CITY + ",12," + ZIP);
		// again requires the delta, because google maps is returning slightly different coordinates		
		Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.005);
		
		// when state code is correct 
		cl.setStateCode(STATE);
//		Assert.assertEquals(cl, locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + "," + ZIP));
		
		// when the zip code is the wrong length, but everything else is correct
		cl.setZip(null);
//		rs = locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + ",123");
		// again requires the delta, because google maps is returning slightly different coordinates		
		Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.005);
		
		// when the address breaks down into too many fields, expect null;
//		Assert.assertNull(locationService.getLocationByAddress(STREET + "," + CITY + "," + STATE + "," + ZIP + ",Extra"));

		/* this clears the interrupted flag on the testing thread. 
		 * this is important, because the final test in this method triggers: 
		 * 		Thread.currentThread().interrupt();
		 * (resetting this flag ourselves appears to be bad practice, but the most current 
		 * version of JUnit4 seems to have fixed this issue only about 10 days ago, so
		 * this might eventually need to be phased out)
		 */
		Thread.interrupted();
	}
    
    /**
     * When the location is simply a zip code which doesn't appear
     * in the database. Mocks calls to the repository.
     * 
     * @throws Exception
     */
    @Test
   	public void getLocationByZipNotInDatabase() throws Exception {
    	// set up address object
    	CachedLocation rs = new CachedLocation();
		CachedLocation cl = new CachedLocation();
		cl.setLatitude(LATLNG.lat);
		cl.setLongitude(LATLNG.lng);
		cl.setZip(ZIP);
		
		// set up the mocking for calls to the database, so that it always returns null
	    Mockito.when(locationRepository.findByAddress(Mockito.anyString())).thenReturn(null);
	    Mockito.when(locationRepository.findByLatitude(Mockito.anyDouble())).thenReturn(null);
	    Mockito.when(locationRepository.findByLongitude(Mockito.anyDouble())).thenReturn(null);
	    
	    // only one path through this method, if the object is new
	    rs = locationService.getLocationByZipCode(ZIP);
	    // requires a delta, because the lat/lng coordinates are slightly different
	    Assert.assertEquals(cl.getLatitude(), rs.getLatitude(), 0.005);
		Assert.assertEquals(cl.getLongitude(), rs.getLongitude(), 0.05);
    }
    
    /**
     * When the location is simply a zip code which does appear
     * in the database. Mocks calls to the repository.
     * 
     * @throws Exception
     */
    @Test
   	public void getLocationByZipInDatabase() throws Exception {
    	// initialize results object, testing object
		CachedLocation cl = new CachedLocation();	// testing object
		cl.setLatitude(LATLNG.lat);
		cl.setLongitude(LATLNG.lng);
		cl.setAddress(ADDRESS);
		cl.setZip(ZIP);
		
		// set up the mocking for calls to the database, so that it always returns the correct object
	    CachedLocation db = new CachedLocation(ADDRESS, LATLNG);
	    Mockito.when(locationRepository.findByAddress(Mockito.anyString())).thenReturn(db);
	    Mockito.when(locationRepository.findByLatitude(Mockito.anyDouble())).thenReturn(db);
	    Mockito.when(locationRepository.findByLongitude(Mockito.anyDouble())).thenReturn(db);
	    
	    // zip code is in the database as an address
	    // expect whatever fields are filled in from the database
	    Assert.assertEquals(cl, locationService.getLocationByZipCode(ZIP));
	    
	    // zip code is not in the database as an address, but latitude and longitude are
	    Mockito.when(locationRepository.findByAddress(Mockito.anyString())).thenReturn(null);
	    Assert.assertEquals(cl, locationService.getLocationByZipCode(ZIP));
    }
}