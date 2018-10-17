package com.revature.rideforce.maps.repotest;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationCRUDRepository;

/**
 * Class that tests the repository
 * These tests were created with the intention of being able to be run independently
 * @author Revature Java batch
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class FavoriteLocationCRUDRepositoryTest {
	
	/**
	 * The entity manager provided by Spring Data JPA for testing
	 */
	@Autowired
	private TestEntityManager entityManager;
	
	/** 
	 * The FavoriteLocationCRUD repository
	 */
	@Autowired
	private FavoriteLocationCRUDRepository favoriteLocationCRUDRepository;
	
	/** 
	 * Assert that the beans get autowired
	 */
	@Before
	public void validate() {
		Assertions.assertThat(favoriteLocationCRUDRepository).isNotNull();
	}	
	
	@Test
	public void removeByNameAndUserId_noName() {
		FavoriteLocation deletedFavLocation = favoriteLocationCRUDRepository.removeByNameAndUserId("", 1);
	}
	
	/**
	 * private helper method to remove all locations from repository
	 */
	private void removeAllEntitiesFromDb() {
		List<FavoriteLocation> locs = (List<FavoriteLocation>) favoriteLocationCRUDRepository.findAll();
		for (FavoriteLocation l : locs) {
			if (!l.equals(null)) {
				entityManager.remove(l);
			}
		}	
	}

}
