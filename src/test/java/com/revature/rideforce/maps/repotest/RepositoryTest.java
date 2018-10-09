package com.revature.rideforce.maps.repotest;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional
//@DataJpaTest
//@DirtiesContext
//@AutoConfigureTestDatabase(replace = Replace.NONE) // do i need this?
public class RepositoryTest {

	
//	@Autowired
//	private TestEntityManager entityManager;
	
	@Autowired
	private LocationRepository locationRepository;
	

	@Test
    public void validateRepository() {
		CachedLocation loc1 = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533));
		Assertions.assertThat(locationRepository.findByAddress((loc1.getAddress()))).isEqualTo(loc1);
		
		

    }

	
	//-----------------------------------
//	@Test
//	public void testSaveAddress2() {
//
//		
//        this.entityManager.persist(new CachedLocation("11750 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533));
//        CachedLocation loc = this.locationRepository.findByAddress("11750 Plaza America Dr #205, Reston, VA 20190");
//        
//        assertThat(loc.getAddress()).isEqualTo("11750 Plaza America Dr #205, Reston, VA 20190");
//       
//	}
//	
//
//	
//	private CachedLocation getCachedLocation() {
//		CachedLocation cLoc = new CachedLocation();
//		cLoc.setAddress("2100 Astoria Cir, Herndon, VA 20170");
//		cLoc.setLatitude(38.9677237);
//		cLoc.setLongitude(-77.4145711);
//		return cLoc;
//	}
	
}