package com.revature.rideshare.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.revature.rideshare.maps.service.LocationService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories //(basePackages="com.revature.rideshare.maps", entityManagerFactoryRef="emf")
public class Application {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
