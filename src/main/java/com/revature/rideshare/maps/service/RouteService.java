package com.revature.rideshare.maps.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.revature.rideshare.maps.beans.Route;

@Component
public class RouteService {
	private static final String API_KEY = "AIzaSyBXWXgWzxhyvz9JyN9SrHgGOzi7VcU5G3g";

	public Route getRoute(String origin, String destination) {
		DirectionsRoute route;
		long distance = 0;
		long duration = 0;
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

		try {
			route = DirectionsApi.getDirections(context, origin, destination).await().routes[0];
			for (DirectionsLeg leg : route.legs) {
				distance += leg.distance.inMeters;
				duration += leg.duration.inSeconds;
			}
			return new Route(distance, duration);
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
