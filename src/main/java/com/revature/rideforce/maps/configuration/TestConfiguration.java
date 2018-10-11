package com.revature.rideforce.maps.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.rideforce.maps.repository.LocationRepository;

@Configuration
@EnableJpaRepositories("com.revature.rideforce.maps.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.revature.rideforce")
// @ComponentScan(basePackages = "com.revature.rideforce", excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com\\.revature\\.rideforce\\.maps\\.repository\\.config"))
public class TestConfiguration {
	
}
