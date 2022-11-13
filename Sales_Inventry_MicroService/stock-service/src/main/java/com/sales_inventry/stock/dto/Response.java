package com.sales_inventry.stock.dto;

import org.springframework.http.HttpStatus;

import com.sales_inventry.exception.ErrorDetails;

public class Response {

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static String SUCCESS_STATUS = "SUCCESS";

	public static String FAIL_STATUS = "FAIL";

	private String status;

	private Object responseData;

	private ErrorDetails errorDetails;

	private HttpStatus httpStatus;

}
