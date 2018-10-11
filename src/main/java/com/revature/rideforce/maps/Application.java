package com.revature.rideforce.maps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The starting point of spring application
 * @author Revature Java batch
 * @SpringBootApplication
 */
@SpringBootApplication
public class Application {
	
	/**
	 * {@code main}
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
