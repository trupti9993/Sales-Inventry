package com.sales_inventry.app.aspect;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.exception.ErrorDetails;
import com.sales_inventry.app.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

		Response response = new Response();
		response.setStatus(Response.FAIL_STATUS);
		response.setErrorDetails(errorDetails);
		response.setHttpStatus(HttpStatus.NOT_FOUND);

		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globleExcpetionHandler(Exception ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

		Response response = new Response();
		response.setStatus(Response.FAIL_STATUS);
		response.setErrorDetails(errorDetails);
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

		return ResponseEntity.ok(response);
	}
}