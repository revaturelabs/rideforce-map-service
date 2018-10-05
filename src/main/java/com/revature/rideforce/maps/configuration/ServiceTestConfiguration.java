package com.revature.rideforce.maps.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.revature.rideforce.maps.beans.Route;
import com.revature.rideforce.maps.service.RouteService;



@Configuration
//@EnableJpaRepository()
//@EnableTransactionManagement
@ComponentScan(basePackages=  {"com.revature.rideforce.maps.service","com.revature.rideforce.maps.beans"},basePackageClasses=RouteService.class)
public class ServiceTestConfiguration {

	
	
}
