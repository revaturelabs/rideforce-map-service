package com.revature.rideforce.maps.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.service.FavoriteLocationService;

/**
 * The controller for obtaining the favorited locations
 * @author Revature Java batch
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/favoritelocations")
public class FavoriteLocationController {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(FavoriteLocationController.class);
	
	/**
	 * Injecting the FavoriteLocationService spring bean
	 */
	@Autowired
	private FavoriteLocationService fls;
	
	/** 
	 * Http GET request
	 * This method finds favorite locations by user id then limits that list to 5 locations
	 * @param	userId
	 * @return	ResponseEntity<> with List<FavoriteLocation> (the list of favorite locations) and 
	 * 			Http status code
	 */
	@GetMapping(value= "/users/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getLocationsByUserId(@PathVariable("id") int userId){
        //get should only retrieve information and the post request should save the actual information
		log.info("finding locations by user");
    	List<FavoriteLocation> userLocationsList= fls.findFavoriteLocationByUserId(userId);
        String getMessage= String.format("User retreived the following saved locations %s",userLocationsList);
        log.info(getMessage);
        return new ResponseEntity<>(userLocationsList, HttpStatus.OK);
        
    }
	 
	/**
	 * Http POST request
	 * @param	address
	 * @param	name
	 * @param 	userId
	 * @return	ResponseEntity<?> (either ResponseError with given message wrapped in a ResponseEntity 
	 * 			to allow it to be returned from a controller method or a ResponseEntity<> with the favorite
	 * 			location and HTTP status code, and no headers)
	 */
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveNewFavoriteLocation(@RequestParam("address") String address,@RequestParam("name")  String name, @RequestParam("userId")  int userId){
		 if(fls.findFavoriteLocationByUserId(userId).size() > 5) {
			 String warning= String.format("User with id: %d tried to save more than 5 locations", userId);
	            log.warn(warning);
	            return new ResponseError("Cannot save that many locations").toResponseEntity(HttpStatus.BAD_REQUEST);
	        }
		FavoriteLocation result =fls.saveFavoriteLocation(address, userId, name);
		if(result.equals(new FavoriteLocation())) {
			return new ResponseError("Unable to save location").toResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
	}
	
	/**
	 * Http DELETE request
	 * @param	name
	 * @param	userId
	 * @return	ResponseEntity<?> (either ResponseError with given message wrapped in a ResponseEntity 
	 * 			to allow it to be returned from a controller method or a ResponseEntity<> with the favorite
	 * 			location and HTTP status code, and no headers)
	 */
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLocationByUserId(@RequestParam String name, @RequestParam int userId){
		log.info("DELETING locations by user");
		FavoriteLocation favorite = fls.deleteFavoriteLocationByNameAndUserId(name, userId);
		if(favorite == new FavoriteLocation()) {
			return new ResponseEntity<>(favorite,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(favorite,HttpStatus.OK);
		
    }
	
}
