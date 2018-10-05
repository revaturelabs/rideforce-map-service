package com.revature.rideforce.maps.repotest;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // do i need this?
public class RepositoryTest {
	
	//private static final Logger logger = LoggerFactory.getLogger(RepositoryTest.class);


	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private LocationRepository locationRepository;
	
//	@Autowired
//	private LocationService locationService;
	
	@Before
	public void checks() {
		testEntityManager.persist(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190", 38.953414, -77.350533));
		testEntityManager.persist(new CachedLocation("2100 Astoria Cir, Herndon, VA 20170", 38.96787, -77.414742));
		testEntityManager.persist(new CachedLocation("503 Pride Ave, Herndon, VA 20170", 38.966271, -77.388985));

		
//		assertNotNull(testEntityManager);
//		CachedLocation l1 = new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 );
//		CachedLocation l2 = new CachedLocation("2100 Astoria Cir, Herndon, VA 20170", 38.96787, -77.414742 );
//		CachedLocation l3 = new CachedLocation("503 Pride Ave, Herndon, VA 20170", 38.966271, -77.388985 );

		//logger.info("hello");
		//assertNotNull(locationService);
		
	}
	@Test
    public void validateRepository() {
        List<CachedLocation> locations = locationRepository.findAll();
        Assertions.assertThat(locations).hasSize(3);
    }
//	@Test
//	public void databaseShouldHaveThreeAddresses() {
//		List<CachedLocation> cachedLocations = locationRepository.findAll();
//		assertTrue(cachedLocations.size() == 3);
//		
//	}
	
}