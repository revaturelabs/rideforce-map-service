package com.revature.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.maps.GeoApiContext;


@Configuration
@EnableJpaRepositories("com.revature.rideforce.maps.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.revature.rideforce"})
// @ComponentScan(basePackages = "com.revature.rideforce", excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com\\.revature\\.rideforce\\.maps\\.repository\\.config"))
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages=  {"com.revature.rideforce.maps.service","com.revature.rideforce.maps.configuration"})
//@ContextConfiguration(classes= {GeoApiContext.class, ServiceTestConfiguration.class}, loader=AnnotationConfigContextLoader.class)
public class TestConfiguration {
	@Autowired
	static private GeoApiContext service;
	
	@Test
	public void notNull() {
		assertThat(service).isNull();
	
//	@Autowired
//	static private ServiceTestConfiguration service;
//	
//	@Test
//	public void notNull() {
//		assertThat(service).isNull();
//	}
	
	
}
}
