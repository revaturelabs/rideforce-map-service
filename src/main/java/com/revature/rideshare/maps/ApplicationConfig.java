package com.revature.rideshare.maps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

@Configuration
public class ApplicationConfig {
	@Value("${MAPS_API_KEY}")
	private String apiKey;
	
	@Bean
	public GeoApiContext geoApiContext() {
		return new GeoApiContext.Builder().apiKey(apiKey).build();
	}
}
