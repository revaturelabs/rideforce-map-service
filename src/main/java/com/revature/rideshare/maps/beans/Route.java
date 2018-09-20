package com.revature.rideshare.maps.beans;

import org.springframework.stereotype.Component;

@Component
public class Route {

	int distance;
	int duration;
	CachedLocation origin;
	CachedLocation destination;
	
	
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public CachedLocation getOrigin() {
		return origin;
	}
	public void setOrigin(CachedLocation origin) {
		this.origin = origin;
	}
	public CachedLocation getDestination() {
		return destination;
	}
	public void setDestination(CachedLocation destination) {
		this.destination = destination;
	}
	public Route(int distance, int duration, CachedLocation origin, CachedLocation destination) {
		super();
		this.distance = distance;
		this.duration = duration;
		this.origin = origin;
		this.destination = destination;
	}
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Route [distance=" + distance + ", duration=" + duration + ", origin=" + origin + ", destination="
				+ destination + "]";
	}
	
}
