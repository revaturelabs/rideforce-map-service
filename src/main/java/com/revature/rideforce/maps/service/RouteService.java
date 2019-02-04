package com.revature.rideforce.maps.service;

import java.io.IOException;

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
 */

@Service
public class RouteService {
	private static final Logger log = LoggerFactory.getLogger(RouteService.class);
	
	@Autowired
	private GeoApiContext geoApiContext;
 	
 	public RouteService() {
		super();
	}
 	
 	public RouteService(GeoApiContext geoApiContext) {
		super();
		this.geoApiContext = geoApiContext;
		log.info("LocationService instantiated");
	}

	/**
	 * Gets the route using Driving travel mode
	 * @param start String representing a starting location
	 * @param end String representing an ending location
	 * @return Route a route in meters and seconds, or a new empty object
	 * on failure
	 */
	public Route getRoute(String start, String end)  {
		// call a method to make a request to the DirectionsApi
		DirectionsRoute route = directionsRequest(start, end);
		
		// if the request threw an error, route will be null
		if(route == null) {
			// the original code returned an empty route on exceptions
			return new Route();
		}

		// calculate the distance and duration of the route
		long distance = 0;
		long duration = 0;
		
		// for each leg in the route, calculate total distance/duration
		for (DirectionsLeg leg : route.legs) {
			distance += leg.distance.inMeters;
			duration += leg.duration.inSeconds;
		}

		// return the constructed route object
		log.info(String.format("Route with the following information returned, distance: %d; duration: %d", distance, duration));
		return new Route(distance, duration);
	}
	
	/**
	 * Makes a call to the DirectionsApi to get directions for
	 * a starting and ending point
	 * @param start starting location
	 * @param end ending location
	 * @return a DirectionsRoute, or null if there was a problem
	 */
<<<<<<< HEAD
	public Route getRoute(String origin, String destination)  {
		log.trace("in getRoute begin");
		try {
			log.trace("in getRoute"); 
			DirectionsRoute route = DirectionsApi.getDirections(geoApiContext, origin, destination)
					.mode(TravelMode.DRIVING).await().routes[0];
			String[] splitOrigin= origin.split(" ");
			if(StringUtils.isNumeric(splitOrigin[0]) && Integer.parseInt(splitOrigin[0])<0  ) {
					String warning= String.format("User attempted to input address with negative numbers; address: %s", origin);
					log.warn(warning);
					return new Route();
			}
			String[] splitDestination=destination.split(" ");
			if(StringUtils.isNumeric(splitDestination[0]) && Integer.parseInt(splitDestination[0])<0) {
					log.info("Can't input a negative origin");
					return new Route();
			}
			long distance = 0;
			long duration = 0;

			for (DirectionsLeg leg : route.legs) {
				log.trace("in getRoute for loop to get distance and duration"); 
				distance += leg.distance.inMeters;
				duration += leg.duration.inSeconds;
				
				log.trace("got it: \nDistance: " + distance + "\nDuration: " + duration);
			}
			String info=String.format("Route with the following information returned, distance: %d; duration: %d", distance,duration);
			log.info(info);
			return new Route(distance, duration);
		} catch (ApiException | InterruptedException | IOException e) {
			log.trace("CAUGHT THE EXCEPTION!!!!!!!!!!");
=======
	public DirectionsRoute directionsRequest(String start, String end) {
		try {
			return DirectionsApi.getDirections(geoApiContext, start, end)
					.mode(TravelMode.DRIVING).await().routes[0];
		} catch (ApiException e) {
>>>>>>> e63660622945a10e99300bb52a17b19a47350d2b
			log.error("Unexpected exception when fetching route.", e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			log.error("Unexpected exception when fetching route.", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Unexpected exception when fetching route.", e);
			e.printStackTrace();
		}
		Thread.currentThread().interrupt();
		return null;
	}
}
