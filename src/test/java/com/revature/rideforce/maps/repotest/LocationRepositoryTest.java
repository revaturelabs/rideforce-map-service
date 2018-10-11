package com.revature.rideforce.maps.repotest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
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

/**
 * Class that tests the repository
 * These tests were created with the intention of being able to be run independently
 * @author Revature Java batch
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=Application.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class LocationRepositoryTest {
	
	/**
	 * The entity manager provided by Spring Data JPA for testing
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	/** 
	 * The location repository
	 */
	@Autowired
	private LocationRepository locationRepository;
	/** 
	 * Assert that the beans get autowired
	 */
	@Before
	public void validate() {
		List <CachedLocation> locs = locationRepository.findAll();
		assertNotNull(entityManager);
		for(CachedLocation l : locs) {
			if(!l.equals(null)) {
				entityManager.remove(l);
			}
		}
	}	
	
	/**
	 * At this point, db should not be empty
	 */
	@Test
	public void databaseShouldNotBeEmpty() {
		persistCachedLocations();
		
		List<CachedLocation> locations = locationRepository.findAll();
		assertFalse(locations.size() == 0);
	}
	
	/**
	 * Three entries were added to db
	 */
	@Test
	public void databaseShouldHaveThreeAddresses() {
		persistCachedLocations();
		
		List<CachedLocation> locations = locationRepository.findAll();
		assertTrue(locations.size() == 3);
	}
	
	/**
	 * No more than three entries should be in db
	 */
	@Test
	public void databaseShouldNotHaveMoreThanThreeAddresses() {
		persistCachedLocations();
		
		List<CachedLocation> locations = locationRepository.findAll();
		assertFalse(locations.size() > 3);
	}
	
	/**
	 * After removing all entries, database should be empty
	 */
	@Test
	public void testRemoveCachedLocations() {
		persistCachedLocations();
		
		// remove everything just added to db
		entityManager.remove(locationRepository.findByAddress("11730 Plaza America Dr #205, Reston, VA 20190"));
		entityManager.remove(locationRepository.findByAddress("2100 Astoria Cir, Herndon, VA 20170"));
		entityManager.remove(locationRepository.findByAddress("503 Pride Ave, Herndon, VA 20170"));
		
		List<CachedLocation> locations = locationRepository.findAll();
//		System.out.println();
//		System.out.println("The current location Repository:  " + locations.toString());
//		System.out.println();
		assertTrue(locations.size() == 0);
	}
	
	/**
	 * After removing two entries, db should have one entry remaining
	 */
	@Test
	public void testRemoveTwoCachedLocations() {
		persistCachedLocations();
		
		entityManager.remove(locationRepository.findByAddress("11730 Plaza America Dr #205, Reston, VA 20190"));
		entityManager.remove(locationRepository.findByAddress("503 Pride Ave, Herndon, VA 20170"));
		
		List<CachedLocation> locations = locationRepository.findAll();
		assertTrue(locations.size() == 1);
	}

	/**
	 * private helper method to add entries to db to avoid repeating code
	 */
	private void persistCachedLocations() {
		CachedLocation cloc1 = new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533);
		CachedLocation cloc2 = new CachedLocation("2100 Astoria Cir, Herndon, VA 20170",38.96787,-77.414742);
		CachedLocation cloc3 = new CachedLocation("503 Pride Ave, Herndon, VA 20170",38.966271,-77.388985);
		
		// Keep in mind: the call of save on a detached instance creates a new persistent instance and assigns it a new identifier, 
		// which results in a duplicate record in a database upon committing or flushing.
		// Whereas, a second call to session.persist() would cause an exception
		entityManager.persist(cloc1);
		entityManager.persist(cloc2);
		entityManager.persist(cloc3);
	}	

	/**
	 * This tests if the data that was fetched from db is equal to the expected result, which is
	 * the cached location saved in db
	 */
	@Test
	public void testSaveCachedLocation() {
		CachedLocation cachedLocation = getCachedLocation();
		CachedLocation savedInDb = entityManager.persist(cachedLocation);
		// query by address because id for CachedLocation is address
		CachedLocation fetchFromDb = locationRepository.findByAddress(savedInDb.getAddress());
		
		assertThat(fetchFromDb).isEqualTo(savedInDb);
	}
	
	/**
	 * private helper method to get cached location
	 * @return the new CachedLocation object
	 */
	private CachedLocation getCachedLocation() {
		CachedLocation cLoc = new CachedLocation();
		cLoc.setAddress("1070 Elden St, Herndon, VA 20170"); // Fresh World! :)
		cLoc.setLatitude(38.9666958);
		cLoc.setLongitude(-77.3975926);
		return cLoc;
	}
	
	/**
	 * Test if an address is not in Db
	 */
	@Test
	public void testAddressNotInDb() {
		CachedLocation cachedLocation = getCachedLocation2();
		CachedLocation savedInDb = entityManager.persist(cachedLocation);
		// query by address because id for CachedLocation is address
		CachedLocation fetchFromDb = locationRepository.findByAddress("1230 Elden St, Herndon, VA 20170");
		
		assertThat(fetchFromDb).isNotEqualTo(savedInDb);
	}
	
	/**
	 * private helper method to get cached location
	 * @return the new CachedLocation object
	 */
	private CachedLocation getCachedLocation2() {
		CachedLocation cLoc2 = new CachedLocation();
		cLoc2.setAddress("1228 Elden St, Herndon, VA 20170"); // Giant! :)
		cLoc2.setLatitude(38.9583948);
		cLoc2.setLongitude(-77.3887017);
		return cLoc2;
	}
	
//	@Test
//	public void canFindByThing2() {
//		//List<CachedLocation> locations = locationRepository.findAll();
//		//System.out.println("locations: " + locations);
//        //Assertions.assertThat(locationRepository).isNotNull();
//		CachedLocation cLoc = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 ));
//		Assertions.assertThat(locationRepository.findAll()).isNotNull();
//	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsEmptyString() {
		persistCachedLocations();
		
		CachedLocation loc = locationRepository.findByAddress("");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsOnlySpace() {
		persistCachedLocations();
		
		CachedLocation loc = locationRepository.findByAddress(" ");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsOnlyNumbers() {
		persistCachedLocations();
		
		CachedLocation loc = locationRepository.findByAddress("2149");
		assertThat(loc).isNull();
	}
	
}
