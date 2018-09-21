package com.revature.rideshare.maps;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main application configuration class. Any Spring Boot configuration
 * annotations should be put on this class to avoid causing problems with unit
 * tests (which load the main SpringBootApplication but not Configuration
 * classes).
 */
@Configuration
@EnableDiscoveryClient
@EnableJpaRepositories
public class ApplicationConfig {
}
