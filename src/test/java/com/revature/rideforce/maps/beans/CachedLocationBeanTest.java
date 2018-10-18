package com.revature.rideforce.maps.beans;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.maps.model.LatLng;

public class CachedLocationBeanTest {
	@Test
	public void newLocation() {
		LatLng lat = new LatLng(512, 523);
		CachedLocation k = new CachedLocation ("1530 bernard street", lat );
		assertThat("1530 bernard street").isEqualTo(k.getAddress());
		

		
	}

}
