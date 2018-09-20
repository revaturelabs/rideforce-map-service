package com.revature.rideshare.maps.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.revature.rideshare.maps.beans.ResponseError;

@RestControllerAdvice
public class ErrorController extends AbstractErrorController {
	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ResponseError> handleError(HttpServletRequest request) {
		Map<String, Object> errorAttributes = getErrorAttributes(request, false);
		return new ResponseError((String) errorAttributes.getOrDefault("message", "Internal server error."))
				.toResponseEntity(getStatus(request));
	}

	/**
	 * Handles the exception thrown when invalid input is sent to a controller.
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

	/**
	 * Handles the exception thrown when a request body cannot be read.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseError> handleException(HttpMessageNotReadableException e) {
		return new ResponseError("Invalid request body format.").withDetails(e.getMessage())
				.toResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
