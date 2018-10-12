package com.revature.rideforce.maps.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.repository.FavoriteLocationRepository;
import com.revature.rideforce.maps.service.FavoriteLocationService;


@RestController
@RequestMapping(value = "/favoritelocations")
public class FavoriteLocationController {
	
	@Autowired
	FavoriteLocationService fls;
	
	private static final Logger log = LoggerFactory.getLogger(FavoriteLocationController.class);
	private List<FavoriteLocation> favoriteLocations = new ArrayList<>();
	
	/** making lists now based on user id
	 * @param id
	 * @return List<CachedLocation>
	 */
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getLocationsByUserId(@RequestParam String address, @PathVariable("id") int userId){
		log.info("finding locations by user");
		if(fls.findFavoriteLocationByUserId(userId).size() >5) {
			log.debug("User with id: " + userId + "tried to favorite more than 5 locations");
			return null;
		}
		else {
			return new ResponseEntity<LatLng>(fls.getOne(address, userId), HttpStatus.OK);
		}
	
	}
}
