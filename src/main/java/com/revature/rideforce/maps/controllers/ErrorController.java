package com.revature.rideforce.maps.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.revature.rideforce.maps.beans.ResponseError;

/**
 * The controller to handle errors/exceptions
 * @author Revature Java batch
 * @RestController
 * @RestControllerAdvice
 */
@RestController
@RestControllerAdvice
public class ErrorController extends AbstractErrorController {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

	/**
	 * class constructor
	 * @param errorAttributes
	 */
	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	/**
	 * Notifies of internal server error
	 * @param request
	 * @return ResponseEntity<ResponseError>
	 * @RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 */
	@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ResponseError> handleError(HttpServletRequest request) {
		Map<String, Object> errorAttributes = getErrorAttributes(request, true);
		if (getStatus(request) == HttpStatus.INTERNAL_SERVER_ERROR) {
			String errorMessage= String.format("Handling unexpected error with attributes %s", errorAttributes);
			log.error(errorMessage);
		}
		String message = (String) errorAttributes.get("message");
		if (message == null) {
			Throwable error = (Throwable) errorAttributes.get("trace");
			message = error == null ? "Internal server error." : error.getMessage();
		}
		return new ResponseError(message).toResponseEntity(getStatus(request));
	}

	/**
	 * Handles the exception thrown when invalid input is sent to a controller.
	 * @ExceptionHandler(MethodArgumentNotValidException.class)
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> handleException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		// Get a human-readable list of validation failure strings.
		String[] details = result.getFieldErrors().stream()
				.map(err -> "Error in property \"" + err.getField() + "\": " + err.getDefaultMessage())
				.toArray(String[]::new);
		return new ResponseError("Input validation failed.").withDetails(details)
				.toResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
