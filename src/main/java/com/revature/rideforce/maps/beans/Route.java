package com.revature.rideforce.maps.beans;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

/**
 * The distance and duration of the route
 * @author Revature Java batch
 * @Component
 */
@Component
public class Route {
	@Max(15000)
  
	/**
	 * the distance between starting address and ending address
	 * according to {@code RouteService} in meters
	 */
	@Positive
	long distance;
	/**
	 * the duration of the drive between starting address and ending address
	 * according to {@code RouteService} in seconds
	 */
	@Positive
	long duration;

	/**
	 * class constructor (no args)
	 */
	public Route() {
	}

	/**
	 * class constructor
	 * sets this distance to 'distance'
	 * sets this duration to 'duration'
	 * @param distance
	 * @param duration
	 */
	public Route(long distance, long duration) {
		this.distance = distance;
		this.duration = duration;
	}

	/**
	 * get distance
	 * @return distance
	 */
	public long getDistance() {
		return distance;
	}

	/**
	 * set distance to 'distance'
	 * @param distance
	 */
	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	/**
	 * get duration
	 * @return duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * set this duration to 'duration'
	 * @param duration
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (distance ^ (distance >>> 32));
		result = prime * result + (int) (duration ^ (duration >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (distance != other.distance)
			return false;
		return duration == other.duration;
	}

	@Override
	public String toString() {
		return "Route [distance=" + distance + ", duration=" + duration + "]";
	}

}
