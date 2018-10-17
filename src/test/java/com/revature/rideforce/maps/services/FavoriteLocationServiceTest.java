package com.revature.rideforce.maps.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.discovery.shared.Application;
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationRepository;
import com.revature.rideforce.maps.service.FavoriteLocationService;


//@RunWith(SpringRunner.class)
//@WebMvcTest(FavoriteLocationController.class)
//@ComponentScan(basePackages = {"com.revature.rideforce.maps.service"})

//@SpringBootTest(classes = Application.class)
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Transactional

	
	

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)

public class FavoriteLocationServiceTest {


	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public FavoriteLocationService favLocationService() {
            return new FavoriteLocationService();
        }
    }
	
	
	
	@Autowired
    private FavoriteLocationService favLocationService;
 
    @MockBean
    private FavoriteLocationRepository favLocationRepository;
	

	
	

    @Test
	public void testPost() throws Exception {
		final String address = "11730 Plaza America Dr. Reston, VA";
		final String name = "workplace";
		final String locationJson = "{ lat: 38.95, lng: -77.35 }";
		int userId = 4;

		FavoriteLocation location = new FavoriteLocation(address, 38.9533932, -77.35044780000001, name, userId);
		assertThat(favLocationService.saveFavoriteLocation(address, userId, name)).isEqualTo(location);
	}


}
