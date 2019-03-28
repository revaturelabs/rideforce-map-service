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
 * 
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
	 * 
	 * @param start String representing a starting location
	 * @param end   String representing an ending location
	 * @return Route a route in meters and seconds, or a new empty object on failure
	 */
	public Route getRoute(String start, String end) {
		// call a method to make a request to the DirectionsApi
		DirectionsRoute route = directionsRequest(start, end);

		// if the request threw an error, route will be null
		if (route == null) {
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
		log.info(String.format("Route with the following information returned, distance: %d; duration: %d", distance,
				duration));
		return new Route(distance, duration);
	}

	/**
	 * Makes a call to the DirectionsApi to get directions for a starting and ending
	 * point
	 * 
	 * @param start starting location
	 * @param end   ending location
	 * @return a DirectionsRoute, or null if there was a problem
	 */
	public DirectionsRoute directionsRequest(String start, String end) {
		try {
			return DirectionsApi.getDirections(geoApiContext, start, end).mode(TravelMode.DRIVING).await().routes[0];
		} catch (ApiException e) {
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
