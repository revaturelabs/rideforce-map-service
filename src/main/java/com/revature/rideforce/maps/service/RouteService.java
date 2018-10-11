package com.revature.rideforce.maps.service;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import com.revature.rideforce.maps.beans.Route;

/**
 * The route service
 * @author Revature Java batch
 * @Component
 */
@Component
public class RouteService {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(RouteService.class);

  	/**
  	 * Injecting the GeoApiContext
  	 */
	@Autowired
	private GeoApiContext geoApiContext;
	
	/**
	 * get geoApiContext
	 * @return geoApiContext
	 */
	public GeoApiContext getGeoApiContext() {
		return geoApiContext;
	}
	
	/**
	 * set this geoApiContext to 'geoApiContext'
	 * @param geoApiContext
	 */
 	public void setGeoApiContext(GeoApiContext geoApiContext) {
		this.geoApiContext = geoApiContext;
	}
 	
 	/**
 	 * class constructor
 	 * set this geoApiContext to 'geoApiContext'
 	 * @param geoApiContext
 	 */
 	public RouteService(GeoApiContext geoApiContext) {
		super();
		this.geoApiContext = geoApiContext;
	}
 	
	/**
	 * class constructor (no args)
	 */
 	public RouteService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * get the route
	 * @param origin
	 * @param destination
	 * @return Route
	 * @throws Exception 
	 */
	public Route getRoute(String origin, String destination){
		try {
			DirectionsRoute route = DirectionsApi.getDirections(geoApiContext, origin, destination)
					.mode(TravelMode.DRIVING).await().routes[0];
			if(StringUtils.isNumeric(origin)) {
				if(Integer.parseInt(origin)<0) {
					log.info("Can't input a negative origin");
					return null;
				}
			}
			if(StringUtils.isNumeric(destination)) {
				if(Integer.parseInt(destination)<0) {
					log.info("Can't input a negative origin");
					return null;
				}
			}
			long distance = 0;
			long duration = 0;

			for (DirectionsLeg leg : route.legs) {
				distance += leg.distance.inMeters;
				duration += leg.duration.inSeconds;
			}
			return new Route(distance, duration);
		} catch (ApiException | InterruptedException | IOException e) {
			log.error("Unexpected exception when fetching route.", e);
			Thread.currentThread().interrupt();
			return null;
		}
	}
}
