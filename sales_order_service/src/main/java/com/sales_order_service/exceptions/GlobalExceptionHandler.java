package com.sales_order_service.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.sales_order_service.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validator(MethodArgumentNotValidException validException) {
		Map<String, String> resp = new HashMap<>();
		validException.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			System.out.println(fieldName + " " + defaultMessage);
			resp.put(fieldName, defaultMessage);

		});
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> pageNumber(IllegalArgumentException e) {
		String message = e.getMessage();
		ApiResponse api = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ApiResponse> pageNumber(ItemNotFoundException e) {
		String message = e.getMessage();
		ApiResponse api = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> pageNumber(HttpClientErrorException e) {
		String message = e.getLocalizedMessage();

		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ApiResponse> pageNumber(ResourceAccessException e) {
		
		ApiResponse api = new ApiResponse("Server is down your patience will be highly appriciated !!!", false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
