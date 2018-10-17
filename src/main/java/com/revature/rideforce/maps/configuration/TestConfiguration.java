package com.revature.rideforce.maps.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories("com.revature.rideforce.maps.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.revature.rideforce")
// @ComponentScan(basePackages = "com.revature.rideforce", excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com\\.revature\\.rideforce\\.maps\\.repository\\.config"))
public class TestConfiguration {
	
}
