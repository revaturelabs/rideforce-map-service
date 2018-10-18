package com.revature.rideforce.maps.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.maps.GeoApiContext;
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationRepository;
import com.revature.rideforce.maps.service.FavoriteLocationService;



//@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class FavoriteLocationServiceTest {


	@TestConfiguration
    static class FavoriteLocationServiceImplTestContextConfiguration {
  
        @Bean
        public FavoriteLocationService favLocationService() {
            return new FavoriteLocationService();
        }
    }
	
	
	
	@Autowired
    private FavoriteLocationService favLocationService;
 
    @MockBean
    private FavoriteLocationRepository favLocationRepository;
	

    @MockBean
	private GeoApiContext geoApiContext;
	
    static final String apiKey= System.getenv("MAPS_API_KEY");
	static final GeoApiContext realGeo = new GeoApiContext.Builder()
		    .apiKey(apiKey)
		    .build();

    @Test
	public void testPost() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		final String name = "workplace";
		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
		int userId = 2;
		FavoriteLocation fnew = new FavoriteLocation();
		fnew.setAddress(address);
		fnew.setName(name);
		fnew.setUserId(2);
		fnew.setLatitude(38.9533932);
		fnew.setLongitude(-77.35044780000001);
		fnew.setLocationId(0);
		favLocationService.setGeoApiContext(realGeo);
	    when(this.favLocationService.saveFavoriteLocation(address, userId, name)).thenReturn(fnew);

		FavoriteLocation f = favLocationService.saveFavoriteLocation(address, userId, name);

		
		FavoriteLocation location = new FavoriteLocation(address, 38.9533932, -77.35044780000001, name, userId);
		assertThat(f).isEqualTo(location);
		System.out.println(f);
	}
    
    @Test
	public void testGet() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		final String name = "workplace";
		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
		int userId = 2;
		FavoriteLocation fnew = new FavoriteLocation();
		fnew.setAddress(address);
		fnew.setName(name);
		fnew.setUserId(2);
		fnew.setLatitude(38.9533932);
		fnew.setLongitude(-77.35044780000001);
		fnew.setLocationId(0);
		favLocationService.setGeoApiContext(realGeo);
		favLocationService.saveFavoriteLocation(address, userId, name);
		List<FavoriteLocation> locations = new ArrayList<FavoriteLocation>();
		locations.add(fnew);

	    when(this.favLocationService.saveFavoriteLocation(address, userId, name)).thenReturn(fnew);

		FavoriteLocation f = favLocationService.saveFavoriteLocation(address, userId, name);


	    when(this.favLocationService.findFavoriteLocationByUserId(2)).thenReturn(locations);

		FavoriteLocation locationAns = new FavoriteLocation(address, 38.9533932, -77.35044780000001, name, userId);
		assertThat(locations.size()).isNotNull();
		assertThat(locations.contains(locationAns)).isEqualTo(true);
		System.out.println(locations);
	}
    
    @Test
	public void testDelete() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		final String name = "workplace";
		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
		int userId = 2;
		FavoriteLocation fnew = new FavoriteLocation();
		fnew.setAddress(address);
		fnew.setName(name);
		fnew.setUserId(2);
		fnew.setLatitude(38.9533932);
		fnew.setLongitude(-77.35044780000001);
		fnew.setLocationId(0);
		favLocationService.setGeoApiContext(realGeo);
		favLocationService.saveFavoriteLocation(address, userId, name);
		List<FavoriteLocation> locations = new ArrayList<FavoriteLocation>();
		locations.add(fnew);

	    when(this.favLocationService.saveFavoriteLocation(address, userId, name)).thenReturn(fnew);

		FavoriteLocation f = favLocationService.saveFavoriteLocation(address, userId, name);


	    when(this.favLocationService.findFavoriteLocationByUserId(2)).thenReturn(locations);
	    when(this.favLocationService.deleteFavoriteLocationByNameAndUserId(name, userId)).thenReturn(fnew);


		FavoriteLocation locationAns = new FavoriteLocation(address, 38.9533932, -77.35044780000001, name, userId);
		assertThat(locations.size()).isNotNull();
		assertThat(locations.contains(locationAns)).isEqualTo(true);
		FavoriteLocation f2 = favLocationService.deleteFavoriteLocationByNameAndUserId(name, userId);
		assertThat(f2).isEqualTo(fnew);
		System.out.println(locations);
	}
    


}
