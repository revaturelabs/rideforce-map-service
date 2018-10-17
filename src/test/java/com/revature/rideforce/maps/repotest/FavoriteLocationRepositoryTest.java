package com.revature.rideforce.maps.repotest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

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
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationRepository;

/**
 * Class that tests the repository
 * These tests were created with the intention of being able to be run independently
 * @author Revature Java batch
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class FavoriteLocationRepositoryTest {
	
	/**
	 * The entity manager provided by Spring Data JPA for testing
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	/** 
	 * The FavoriteLocation repository
	 */
	@Autowired
	private FavoriteLocationRepository favoriteLocationRepository;
	
	/** 
	 * Assert that the beans get autowired
	 */
	@Before
	public void validate() {
		assertNotNull(entityManager);
		
		removeAllEntitiesFromDb();		
	}	
	
	/**
	 * At this point, db should not be empty
	 */
	@Test
	public void databaseShouldNotBeEmpty() {
		persistFavoriteLocations();
		
		List<FavoriteLocation> locations = favoriteLocationRepository.findAll();
		Assertions.assertThat(locations).isNotEmpty();
	}
	
	/**
	 * I should be able to find the the proper list size of favorited locations by userId
	 */
	@Test
	public void findByUserId_TestHasTwo() {
		persistFavoriteLocations2();
		
		List<FavoriteLocation> locationsByUserId = favoriteLocationRepository.findByUserId(2);
		Assertions.assertThat(locationsByUserId).hasSize(2);
	}
	
	/**
	 * I should be able to find the the favorited locations by userId
	 */
	@Test
	public void findByUserId_OnlyListWithParticularId() {
		FavoriteLocation cloc1 = new FavoriteLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533, "Home", 2);
		FavoriteLocation cloc2 = new FavoriteLocation("2100 Astoria Cir, Herndon, VA 20170",38.96787,-77.414742, "Work", 2);
		FavoriteLocation cloc3 = new FavoriteLocation("503 Pride Ave, Herndon, VA 20170",38.966271,-77.388985, "Special", 3);
		
		entityManager.persist(cloc1);
		entityManager.persist(cloc2);
		entityManager.persist(cloc3);
		
		List<FavoriteLocation> locationsByUserId = favoriteLocationRepository.findByUserId(2);
		Assertions.assertThat(locationsByUserId).contains(cloc1);
		Assertions.assertThat(locationsByUserId).contains(cloc2);
	}
	
	/**
	 * I should only be able to find the the favorited locations by userId and not other ids
	 */
	@Test
	public void findByUserId_LocationByOtherIdNotIncluded() {
		FavoriteLocation cloc1 = new FavoriteLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533, "Home", 2);
		FavoriteLocation cloc2 = new FavoriteLocation("2100 Astoria Cir, Herndon, VA 20170",38.96787,-77.414742, "Work", 2);
		FavoriteLocation cloc3 = new FavoriteLocation("503 Pride Ave, Herndon, VA 20170",38.966271,-77.388985, "Special", 3);
		
		entityManager.persist(cloc1);
		entityManager.persist(cloc2);
		entityManager.persist(cloc3);
		
		List<FavoriteLocation> locationsByUserId = favoriteLocationRepository.findByUserId(2);
		Assertions.assertThat(locationsByUserId).doesNotContain(cloc3);
	}
	
	/**
	 * I should be able to find a favorited location by location name and user id
	 */
	@Test
	public void findByNameAndUserId_Test() {
		FavoriteLocation cloc1 = new FavoriteLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533, "Home", 2);
		entityManager.persist(cloc1);
		
		FavoriteLocation locationByName= favoriteLocationRepository.findByNameAndUserId("Home", 2);
		Assertions.assertThat(locationByName).isEqualTo(cloc1);
	}
	
	/**
	 * I should be able to find a favorited location by lat/lng pair and userId
	 */
	@Test
	public void findByLatitudeAndLongitudeAndUserId_Test() {
		FavoriteLocation cloc3 = new FavoriteLocation("503 Pride Ave, Herndon, VA 20170",38.966271,-77.388985, "MyLoc", 3);
		entityManager.persist(cloc3);
		
		FavoriteLocation locationByLatLng= favoriteLocationRepository.findByLatitudeAndLongitudeAndUserId(38.966271,-77.388985, 3);
		Assertions.assertThat(locationByLatLng).isEqualTo(cloc3);
	}
	
	/**
	 * Three entries were added to db
	 */
	@Test
	public void databaseShouldHaveThreeAddresses() {
		persistFavoriteLocations();
		
		List<FavoriteLocation> locations = favoriteLocationRepository.findAll();
		Assertions.assertThat(locations).hasSize(3);
	}
	
	/**
	 * No more than three entries should be in db
	 */
	@Test
	public void databaseShouldNotHaveMoreThanThreeAddresses() {
		persistFavoriteLocations();
		
		List<FavoriteLocation> locations = favoriteLocationRepository.findAll();
		assertFalse(locations.size() > 3);
	}
	
	/**
	 * After removing all entries, database should be empty
	 */
	@Test
	public void testRemoveFavoriteLocations() {
		persistFavoriteLocations();
		
		// remove everything just added to db
		entityManager.remove(favoriteLocationRepository.findByAddress("11730 Plaza America Dr #205, Reston, VA 20190"));
		entityManager.remove(favoriteLocationRepository.findByAddress("2100 Astoria Cir, Herndon, VA 20170"));
		entityManager.remove(favoriteLocationRepository.findByAddress("503 Pride Ave, Herndon, VA 20170"));
		
		List<FavoriteLocation> locations = favoriteLocationRepository.findAll();
		Assertions.assertThat(locations).hasSize(0);
	}
	
	/**
	 * After removing two entries, db should have one entry remaining
	 */
	@Test
	public void testRemoveTwoFavoriteLocations() {
		persistFavoriteLocations();
		
		entityManager.remove(favoriteLocationRepository.findByAddress("11730 Plaza America Dr #205, Reston, VA 20190"));
		entityManager.remove(favoriteLocationRepository.findByAddress("503 Pride Ave, Herndon, VA 20170"));
		
		List<FavoriteLocation> locations = favoriteLocationRepository.findAll();
		Assertions.assertThat(locations).hasSize(1);
	}

	/**
	 * This tests if the data that was fetched from db is equal to the expected result, which is
	 * the favorite location saved in db
	 */
	@Test
	public void testSaveFavoriteLocation() {
		FavoriteLocation favoriteLocation = getFavoriteLocation();
		FavoriteLocation savedInDb = entityManager.persist(favoriteLocation);
		// query by address because id for FavoriteLocation is address
		FavoriteLocation fetchFromDb = favoriteLocationRepository.findByAddress(savedInDb.getAddress());
		
		assertThat(fetchFromDb).isEqualTo(savedInDb);
	}
	
	/**
	 * After putting in 3 entries, size of list should be 3
	 */
	@Test
	public void testProperSize() {
		persistFavoriteLocations();
		List<FavoriteLocation> locations = favoriteLocationRepository.findAll();

		Assertions.assertThat(locations).hasSize(3);
	}
	
	/**
	 * Test if an address is not in Db
	 */
	@Test
	public void testAddressNotInDb() {
		FavoriteLocation favoriteLocation = getFavoriteLocation2();
		FavoriteLocation savedInDb = entityManager.persist(favoriteLocation);
		// query by address because id for FavoriteLocation is address
		FavoriteLocation fetchFromDb = favoriteLocationRepository.findByAddress("1230 Elden St, Herndon, VA 20170");
		
		assertThat(fetchFromDb).isNotEqualTo(savedInDb);
	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsEmptyString() {
		persistFavoriteLocations();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress("");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsOnlySpace() {
		persistFavoriteLocations();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress(" ");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsOnlyPeriod() {
		persistFavoriteLocations();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress(".");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there is only a single character
	 */
	@Test
	public void returnsNullWhenAddressIsOnlySingleCharacter() {
		persistFavoriteLocations();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress("A");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there is invalid id (address)
	 */
	@Test
	public void returnsNullWhenAddressIsOnlyNumbers() {
		persistFavoriteLocations();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress("2149");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should return null when there are no locations in db
	 */
	@Test
	public void returnsNullWhenThereAreNoLocations() {
		removeAllEntitiesFromDb();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress("2149 Astoria Cir, Herndon, VA 20170");
		assertThat(loc).isNull();
	}
	
	/**
	 * Should not return null when the location is actually in DB
	 */
	@Test
	public void returnsNotNullWhenFetchingSpecificResultInDb() {
		persistFavoriteLocations();
		
		FavoriteLocation loc = favoriteLocationRepository.findByAddress("503 Pride Ave, Herndon, VA 20170");
		assertThat(loc).isNotNull();
	}	
	
	/**
	 * private helper method to get favorite location
	 * @return the new FavoriteLocation object
	 */
	private FavoriteLocation getFavoriteLocation() {
		FavoriteLocation cLoc = new FavoriteLocation();
		cLoc.setAddress("1070 Elden St, Herndon, VA 20170"); // Fresh World! :)
		cLoc.setLatitude(38.9666958);
		cLoc.setLongitude(-77.3975926);
		cLoc.setUserId(1);
		return cLoc;
	}
	
	/**
	 * private helper method to get favorite location
	 * @return the new FavoriteLocation object
	 */
	private FavoriteLocation getFavoriteLocation2() {
		FavoriteLocation cLoc2 = new FavoriteLocation();
		cLoc2.setAddress("1228 Elden St, Herndon, VA 20170"); // Giant! :)
		cLoc2.setLatitude(38.9583948);
		cLoc2.setLongitude(-77.3887017);
		cLoc2.setUserId(1);
		return cLoc2;
	}
	
	/**
	 * private helper method to add entries to db to avoid repeating code
	 */
	private void persistFavoriteLocations() {
		FavoriteLocation cloc1 = new FavoriteLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533, null, 1);
		FavoriteLocation cloc2 = new FavoriteLocation("2100 Astoria Cir, Herndon, VA 20170",38.96787,-77.414742, null, 1);
		FavoriteLocation cloc3 = new FavoriteLocation("503 Pride Ave, Herndon, VA 20170",38.966271,-77.388985, null, 1);
		
		// Keep in mind: the call of save on a detached instance creates a new persistent instance and assigns it a new identifier, 
		// which results in a duplicate record in a database upon committing or flushing.
		// Whereas, a second call to session.persist() would cause an exception
		entityManager.persist(cloc1);
		entityManager.persist(cloc2);
		entityManager.persist(cloc3);
	}
	
	/**
	 * private helper method to add entries to db to avoid repeating code
	 */
	private void persistFavoriteLocations2() {
		FavoriteLocation cloc1 = new FavoriteLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533, "Home", 2);
		FavoriteLocation cloc2 = new FavoriteLocation("2100 Astoria Cir, Herndon, VA 20170",38.96787,-77.414742, "Work", 2);
		FavoriteLocation cloc3 = new FavoriteLocation("503 Pride Ave, Herndon, VA 20170",38.966271,-77.388985, "Special", 3);
		
		// Keep in mind: the call of save on a detached instance creates a new persistent instance and assigns it a new identifier, 
		// which results in a duplicate record in a database upon committing or flushing.
		// Whereas, a second call to session.persist() would cause an exception
		entityManager.persist(cloc1);
		entityManager.persist(cloc2);
		entityManager.persist(cloc3);
	}
	
	/**
	 * private helper method to remove all locations from repository
	 */
	private void removeAllEntitiesFromDb() {
		List<FavoriteLocation> locs = favoriteLocationRepository.findAll();
		for (FavoriteLocation l : locs) {
			if (!l.equals(null)) {
				entityManager.remove(l);
			}
		}	
	}
	
	
}
