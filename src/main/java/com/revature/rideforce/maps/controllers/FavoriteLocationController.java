package com.revature.rideforce.maps.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rideforce.maps.beans.FavoriteLocation;
import com.revature.rideforce.maps.beans.ResponseError;
import com.revature.rideforce.maps.service.FavoriteLocationService;

/**
 * The controller for obtaining the favorited locations
 * @author Revature Java batch
 * @RestController
 * @RequestMapping(value = "/favoritelocations")
 */
@RestController
@RequestMapping(value = "/favoritelocations")
public class FavoriteLocationController {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(FavoriteLocationController.class);

	   
	/**
	 * Injecting the FavoriteLocationService spring bean
	 * @Autowired
	 */
	@Autowired
	private FavoriteLocationService fls;
	
	
	

	/** 
	 * This method finds favorite locations by user id then limits that list to 5 locations
	 * @param userId
	 * @return List<FavoriteLocation> (the list of favorite locations)
	 */
//	@GetMapping("/users/{id}")
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getLocationsByUserId(@PathVariable("id") int userId){
        //get should only retreive information and the post request should save the actual information
		log.info("finding locations by user");
    	List<FavoriteLocation> userLocationsList= fls.findFavoriteLocationByUserId(userId);
        String getMessage= String.format("User retreived the following saved locations %s",userLocationsList);
        log.info(getMessage);
        return new ResponseEntity<>(userLocationsList, HttpStatus.OK);
        
    }
	 
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
