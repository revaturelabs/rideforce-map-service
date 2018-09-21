package com.revature.rideshare.maps;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

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
	@Value("${MAPS_API_KEY}")
	private String apiKey;
	
	@Bean
	public GeoApiContext geoApiContext() {
		return new GeoApiContext.Builder().apiKey(apiKey).build();
	}
}
