package com.revature.rideforce.maps;

import static org.mockito.Mockito.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.rideforce.maps.repository.LocationRepository;


@Configuration
@EnableJpaRepositories("com.revature.rideforce.maps.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.revature.rideforce" })
public class RepoTestConfig {
	
	//private static final Logger logger = LoggerFactory.getLogger(RepoTestConfig.class);

//	@Bean
//	@Profile("mock-location-repository")
//	// change bean name
//	public LocationRepository locationRepositoryMock() {
//		//logger.info("Mocking: {}", LocationRepository.class);
//		//logger.info("I am logging");
//		return mock(LocationRepository.class);
//	}
	
	
}