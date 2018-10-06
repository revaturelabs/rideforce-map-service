package com.revature.rideforce.maps.repotest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@ContextConfiguration(classes=Application.class)
//@DataJpaTest
@Transactional
public class LocationRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LocationRepository locationRepository;
	
	// assert that the beans get autowired 
//	@Before
//	public void validate() {
//		Assertions.assertThat(locationRepository).isNotNull();
////		CachedLocation loc1 = new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533);
////		CachedLocation loc2 = new CachedLocation("2150 Astoria Cir, Herndon, VA 20170",38.968193,-77.4164055);
////		locationRepository.save(loc2);
////		testEntityManager.persist(loc2);
//	}
	
	@Test
	public void testSaveAddress() {
		//List<CachedLocation> locations = locationRepository.findAll();
		//System.out.println("locations: " + locations);
        //Assertions.assertThat(locationRepository).isNotNull();
//		CachedLocation cLoc = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 ));
//		Assertions.assertThat(locationRepository.findAll()).containsAnyOf(cLoc);
		
//		CachedLocation cachedLocation = getCachedLocation();
//		CachedLocation savedInDb = entityManager.persist(cachedLocation);
//		// can query by address
//		CachedLocation getFromDb = locationRepository.findByAddress(savedInDb.getAddress());
//		
//		assertThat(getFromDb).isEqualTo(savedInDb);
		
        this.entityManager.persist(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533));
        CachedLocation loc = this.locationRepository.findByAddress("11730 Plaza America Dr #205, Reston, VA 20190");
        
        assertThat(loc.getAddress()).isEqualTo("11730 Plaza America Dr #205, Reston, VA 20190");
       // assertThat(loc.getVin()).isEqualTo("123");
   // }
	}
	
	@Test
	public void testSaveAddress2() {
		//List<CachedLocation> locations = locationRepository.findAll();
		//System.out.println("locations: " + locations);
        //Assertions.assertThat(locationRepository).isNotNull();
//		CachedLocation cLoc = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 ));
//		Assertions.assertThat(locationRepository.findAll()).containsAnyOf(cLoc);
		
//		CachedLocation cachedLocation = getCachedLocation();
//		CachedLocation savedInDb = entityManager.persist(cachedLocation);
//		// can query by address
//		CachedLocation getFromDb = locationRepository.findByAddress(savedInDb.getAddress());
//		
//		assertThat(getFromDb).isEqualTo(savedInDb);
		
        this.entityManager.persist(new CachedLocation("11750 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533));
        CachedLocation loc = this.locationRepository.findByAddress("11750 Plaza America Dr #205, Reston, VA 20190");
        
        assertThat(loc.getAddress()).isEqualTo("11750 Plaza America Dr #205, Reston, VA 20190");
       // assertThat(loc.getVin()).isEqualTo("123");
   // }
	}
	
//	@Test
//	public void canFindByThing2() {
//		//List<CachedLocation> locations = locationRepository.findAll();
//		//System.out.println("locations: " + locations);
//        //Assertions.assertThat(locationRepository).isNotNull();
//		CachedLocation cLoc = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 ));
//		Assertions.assertThat(locationRepository.findAll()).isNotNull();
//	}
	
	private CachedLocation getCachedLocation() {
		CachedLocation cLoc = new CachedLocation();
		cLoc.setAddress("2100 Astoria Cir, Herndon, VA 20170");
		cLoc.setLatitude(38.9677237);
		cLoc.setLongitude(-77.4145711);
		return cLoc;
	}

	
	
}
