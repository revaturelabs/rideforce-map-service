package com.revature.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.maps.GeoApiContext;
import com.revature.rideforce.maps.repository.LocationRepository;

@Configuration
//@EnableJpaRepositories("com.revature.rideforce.maps.repository")
//@EnableTransactionManagement
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"com.revature.rideforce.maps.service","com.revature.map.configuration"})
// @ComponentScan(basePackages = "com.revature.rideforce", excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com\\.revature\\.rideforce\\.maps\\.repository\\.config"))
public class TestConfiguration {
	
	@Autowired
	static private GeoApiContext service;
	
	@Test
	public void notNull() {
		assertThat(service).isNull();
	}
	
}
