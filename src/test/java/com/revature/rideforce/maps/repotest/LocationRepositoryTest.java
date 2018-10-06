package com.revature.rideforce.maps.repotest;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@ContextConfiguration
//@DataJpaTest
@Transactional
public class LocationRepositoryTest {
	
//	@Autowired
//	private TestEntityManager testEntityManager;
	
	@Autowired
	private LocationRepository locationRepository;
	
	// assert that the beans get autowired 
	@Before
	public void validate() {
		Assertions.assertThat(locationRepository).isNotNull();
//		CachedLocation loc1 = new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533);
//		CachedLocation loc2 = new CachedLocation("2150 Astoria Cir, Herndon, VA 20170",38.968193,-77.4164055);
//		locationRepository.save(loc2);
//		testEntityManager.persist(loc2);
	}
	
	@Test
	public void canFindByThing() {
		//List<CachedLocation> locations = locationRepository.findAll();
		//System.out.println("locations: " + locations);
        //Assertions.assertThat(locationRepository).isNotNull();
		CachedLocation cLoc = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 ));
		Assertions.assertThat(locationRepository.findAll()).containsAnyOf(cLoc);
	}

	
	
}
