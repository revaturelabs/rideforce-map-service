package com.revature.rideforce.maps.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;
import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;



@Configuration
//@EnableJpaRepository()
//@EnableTransactionManagement
@ComponentScan(basePackages=  {"com.revature.rideforce.maps.service"})
public class ServiceTestConfiguration {

	@Autowired
	private GeoApiContext geoApiContext;
	
}
