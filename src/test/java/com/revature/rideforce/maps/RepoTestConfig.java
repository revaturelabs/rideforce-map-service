package com.revature.rideforce.maps;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.revature.rideforce.maps.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.revature.rideforce" })
public class RepoTestConfig {
	
	
}