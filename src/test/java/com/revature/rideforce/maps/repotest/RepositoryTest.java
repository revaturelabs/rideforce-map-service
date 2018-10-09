package com.revature.rideforce.maps.repotest;

import org.assertj.core.api.Assertions;
//import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.rideforce.maps.Application;
import com.revature.rideforce.maps.beans.CachedLocation;
import com.revature.rideforce.maps.repository.LocationRepository;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
//@DirtiesContext
//@AutoConfigureTestDatabase(replace = Replace.NONE) // do i need this?
public class RepositoryTest {
//	org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaConfiguration': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': Invocation of init method failed; nested exception is java.lang.IllegalStateException: Failed to replace DataSource with an embedded database for tests. If you want an embedded database please p
//	org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaConfiguration': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': Invocation of init method failed; nested exception is java.lang.IllegalStateException: Failed to replace DataSource with an embedded database for tests. If you want an embedded database please put a supported one on the classpath or tune the replace attribute of @AutoConfigureTestDatabase.
//	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:732) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
	//private static final Logger logger = LoggerFactory.getLogger(RepositoryTest.class);


//	Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
//	2018-10-09 10:22:42.417 ERROR [maps-service,,,] 26168 --- [           main] o.s.boot.SpringApplication               : Application run failed
//
//	org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unsatisfied dependency expressed through method 'entityManagerFactory' parameter 0; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'entityManagerFactoryBuilder' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unsatisfied dependency expressed through method 'entityManagerFactoryBuilder' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaVendorAdapter' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.orm.jpa.JpaVendorAdapter]: Factory method 'jpaVendorAdapter' threw exception; nested exception is java.lang.RuntimeException: Driver oracle.jdbc.driver.OracleDriver claims to not accept jdbcUrl, ${JDBC_URL}
//		at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:732) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
//		at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:474) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
//		at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1247) ~[spring-beans-5.0.8.RELEASE.jar:5.0.8.RELEASE]
//		at org
	
//	@Autowired
//	private TestEntityManager testEntityManager;
	
	@Autowired
	private LocationRepository locationRepository;
	
//	@Autowired
//	private LocationService locationService;
	
//	@Before
//	public void checks() {
//		testEntityManager.persist(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190", 38.953414, -77.350533));
//		testEntityManager.persist(new CachedLocation("2100 Astoria Cir, Herndon, VA 20170", 38.96787, -77.414742));
//		testEntityManager.persist(new CachedLocation("503 Pride Ave, Herndon, VA 20170", 38.966271, -77.388985));

		
//		assertNotNull(testEntityManager);
//		CachedLocation l1 = new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533 );
//		CachedLocation l2 = new CachedLocation("2100 Astoria Cir, Herndon, VA 20170", 38.96787, -77.414742 );
//		CachedLocation l3 = new CachedLocation("503 Pride Ave, Herndon, VA 20170", 38.966271, -77.388985 );

		//logger.info("hello");
		//assertNotNull(locationService);
		
//	}
	@Test
    public void validateRepository() {
		CachedLocation loc1 = locationRepository.save(new CachedLocation("11730 Plaza America Dr #205, Reston, VA 20190",38.953414,-77.350533));
		Assertions.assertThat(locationRepository.findByAddress((loc1.getAddress()))).isEqualTo(loc1);
		
		
//        List<CachedLocation> locations = locationRepository.findAll();
//        assertTrue(locations.size() == 3);
        //Assertions.assertThat(locations).hasSize(3);
    }
//	@Test
//	public void databaseShouldHaveThreeAddresses() {
//		List<CachedLocation> cachedLocations = locationRepository.findAll();
//		assertTrue(cachedLocations.size() == 3);
//		
//	}
	
}