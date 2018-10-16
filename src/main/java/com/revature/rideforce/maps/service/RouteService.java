package com.revature.rideforce.maps.service;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Service
 */
@Service // changed from @Component
public class RouteService {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(RouteService.class);
	
  	/**
  	 * Injecting the GeoApiContext, the entry point for making requests against the Google Geo APIs. 
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
		log.info("GeoApiContext set");
	}
 	
 	/**
 	 * class constructor
 	 * set this geoApiContext to 'geoApiContext'
 	 * @param geoApiContext
 	 */
 	public RouteService(GeoApiContext geoApiContext) {
		super();
		this.geoApiContext = geoApiContext;
		log.info("RouteService instantiated");
	}
 	
	/**
	 * class constructor (no args)
	 */
 	public RouteService() {
		super();
	}

	/**
	 * get the route using Driving travel mode
	 * @param origin (a starting address)
	 * @param destination (an ending address)
	 * @return Route (in meters and seconds)
	 */
	public Route getRoute(String origin, String destination)  {
		try {
			DirectionsRoute route = DirectionsApi.getDirections(geoApiContext, origin, destination)
					.mode(TravelMode.DRIVING).await().routes[0];
			String[] splitOrigin= origin.split(" ");
			if(StringUtils.isNumeric(splitOrigin[0]) ) {
				if(Integer.parseInt(splitOrigin[0])<0) {
					log.warn(String.format("User attempted to input address with negative numbers; address: %s", origin));
					return null;
				}
			}
			String[] splitDestination=destination.split(" ");
			if(StringUtils.isNumeric(splitDestination[0])) {
				if(Integer.parseInt(splitDestination[0])<0) {
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
			log.info(String.format("Route with the following information returned, distance: %d; duration: %d", distance,duration));
			return new Route(distance, duration);
		} catch (ApiException | InterruptedException | IOException e) {
			log.error("Unexpected exception when fetching route.", e);
			Thread.currentThread().interrupt();
			return null;
		}
	}
}
