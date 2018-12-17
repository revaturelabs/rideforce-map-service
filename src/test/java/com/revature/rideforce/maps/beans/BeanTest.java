package com.revature.rideforce.maps.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.maps.model.LatLng;

import org.junit.Assert;

/**
 * This class holds the unit tests for our 
 * {@link com.revature.rideforce.maps.beans beans}:
 * {@link com.revature.rideforce.maps.beans.CachedLocation CachedLocation}, 
 * {@link com.revature.rideforce.maps.beans.FavoriteLocation FavoriteLocation},
 * {@link com.revature.rideforce.maps.beans.ResponseError ResponseError}, 
 * {@link com.revature.rideforce.maps.beans.Route Route}
 * @author Revature Java batch
 */
public class BeanTest{
	
	private static ValidatorFactory validatorFactory;
    private static Validator validator;
    
	
    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @AfterClass
    public static void close() {
        validatorFactory.close();
    }
    
    @Test
    public void validateRouteTest() {
    	Route route = new Route(12714, 9600);
        Set<ConstraintViolation<Route>> violations
            = validator.validate(route);

        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void violateConstraintTest() {
    	Route route = new Route(16000, 9600);
     
        Set<ConstraintViolation<Route>> violations
            = validator.validate(route);
     
        assertEquals(1,violations.size());
     
        ConstraintViolation<Route> violation
            = violations.iterator().next();
        assertEquals("must be less than or equal to 15000",
                     violation.getMessage());
        assertEquals("distance", violation.getPropertyPath().toString());
        assertEquals(16000L, violation.getInvalidValue());
    }
    
    @Test
    public void validateCachedLocationTest() {
    	CachedLocation location = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
        Set<ConstraintViolation<CachedLocation>> violations
            = validator.validate(location);

        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void violateConstraintTest2() {
    	CachedLocation location = new CachedLocation("2925 Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
     
        Set<ConstraintViolation<CachedLocation>> violations
            = validator.validate(location);
     
        assertEquals(1, violations.size());
     
        ConstraintViolation<CachedLocation> violation
            = violations.iterator().next();
        assertEquals("size must be between 0 and 85",
                     violation.getMessage());
        assertEquals("address", violation.getPropertyPath().toString());
        assertEquals("2925 Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Rensselaer Ct. Vienna, VA 22181", violation.getInvalidValue());
    }
    
    @Test
    public void routeEqualsReflexive(){	
    	Route route = new Route(12714, 9600);
        assertEquals(route, route);
        assertFalse(route.equals(null));
        
    }
    
    @Test
    public void routeEqualsSymmetric(){	
    	Route route = new Route(12714, 9600);
    	Route route1 = new Route(12714, 9600);
        assertEquals(route, route1);
        assertEquals(route1, route);
        
    }
    
    @Test
    public void routeEqualsTransitive() {
    	Route route = new Route(12714, 9600);
    	Route route1 = new Route(12714, 9600);
    	Route route2 = new Route(12714, 9600);
        assertTrue(route.equals(route1));
        assertTrue(route1.equals(route2));
        assertTrue(route.equals(route2));
    }
    
    @Test
    public void hashCodeRouteConsistencyTest()
    {
    	Route route = new Route(12714, 9600);
       
        assertEquals(route.hashCode(), route.hashCode());
        
    }
    
    @Test
    public void hashCodeRouteTest()
    {
    	Route route = new Route(12714, 9600);
    	Route route1 = new Route(12714, 9600);
       
        assertEquals(route.hashCode(), route1.hashCode());
    }
    
    @Test
    public void cachedLocationEqualsReflexive(){	
    	CachedLocation location = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
        assertEquals(location, location);
        assertNotNull(location);
    }
    
    @Test
    public void cachedLocationEqualsSymmetric() {
//    	CachedLocation location = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
//    	CachedLocation location1 = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
//    	assertEquals(location,location1);
//    	assertTrue(location.equals(location1));
//        assertTrue(location1.equals(location));
    }
    
    @Test
    public void cachedLocationEqualsTransitive() {
//    	CachedLocation location = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
//    	CachedLocation location1 = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
//    	CachedLocation location2 = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
//        assertTrue(location.equals(location1));
//        assertTrue(location1.equals(location2));
//        assertTrue(location.equals(location2));
    }
    
    @Test
    public void locationGetterTest() {
        CachedLocation location = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
    	
        assertEquals(location.getLocation(), new LatLng(38.95, -77.35));
    }
   
    
    @Test
    public void routeToStringTest()
    {
    	Route route = new Route(12714, 9600);
        String expected = "Route [distance=" + 12714 + ", duration=" + 9600 + "]";
        Assert.assertEquals(expected, route.toString());
    }
    
    @Test
    public void locationToStringTest()
    {
    	CachedLocation location = new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", 38.95, -77.35);
        String expected = "Location [address=" + "2925 Rensselaer Ct. Vienna, VA 22181" + ", latitude=" + 38.95 + ", longitude=" + -77.35 + "]";
        Assert.assertEquals(expected, location.toString());
    }
    
    @Test
    public void routeConstructorTest() {
    	new Route();
    }
    
    @Test
    public void locationConstructorTest() {
    	new CachedLocation();
    }
    
    @Test
    public void locationConstructorTest2() {
    	new CachedLocation("2925 Rensselaer Ct. Vienna, VA 22181", new LatLng(38.95, -77.35));
    }
    
    @Test
    public void routeSetterAndGetterTest() {
        Route route = new Route();
        route.setDistance(12714);
        assertEquals(12714,route.getDistance());
    }
    
    @Test
    public void routeSetterAndGetterTest2() {
        Route route = new Route();
        route.setDuration(9600);
        assertEquals(9600,route.getDuration());
    }
    
    @Test
    public void locationSetterAndGetterTest() {
    	CachedLocation location = new CachedLocation();
        location.setAddress("2925 Rensselaer Ct. Vienna, VA 22181");
        assertEquals("2925 Rensselaer Ct. Vienna, VA 22181",location.getAddress());
    }
    
    @Test
    public void locationSetterAndGetterTest2() {
    	CachedLocation location = new CachedLocation();
        location.setLatitude(38.95);
        assertTrue(location.getLatitude()==38.95);
    }
    
    @Test
    public void locationSetterAndGetterTest3() {
    	CachedLocation location = new CachedLocation();
        location.setLongitude(-77.35);
        assertTrue(location.getLongitude()==-77.35);
    }
    
    @Test
    public void responseEqualsReflexive(){
    	ResponseError responseError = new ResponseError("message");
        assertEquals(responseError, responseError);
        assertFalse(responseError.equals(null));
        
    }
    
    @Test
    public void responseEqualsSymmetric(){
    	ResponseError responseError = new ResponseError("message");
    	ResponseError responseError1 = new ResponseError("message");
        assertEquals(responseError, responseError1);
        assertEquals(responseError, responseError1);
        
    }
    
    @Test
    public void responseEqualsTransitive() {
    	ResponseError responseError = new ResponseError("message");
    	ResponseError responseError1 = new ResponseError("message");
    	ResponseError responseError2 = new ResponseError("message");
    	
        assertTrue(responseError.equals(responseError1));
        assertTrue(responseError1.equals(responseError2));
        assertTrue(responseError.equals(responseError2));
    }
    
    @Test
    public void hashCodeResponseConsistencyTest()
    {
    	ResponseError responseError = new ResponseError("message");
        assertEquals(responseError.hashCode(), responseError.hashCode());
    }
    
    @Test
    public void hashCodeResponseTest()
    {
    	ResponseError responseError = new ResponseError("message");
    	ResponseError responseError1 = new ResponseError("message");
        assertEquals(responseError.hashCode(), responseError1.hashCode());
    }
    
    @Test
    public void errorResponseToStringTest(){
    		
    	ResponseError responseError = new ResponseError("message");
    	String[] details = {};
        String expected = "ResponseError [message=" + "message" + ", details=" + Arrays.toString(details) + "]";
        Assert.assertEquals(expected, responseError.toString());
    }

    
    
}
