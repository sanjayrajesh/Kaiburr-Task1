package com.kaiburr.task1.model;

import org.springframework.http.HttpStatus;

public class RequestJSONResponse {

	private String message;
	private HttpStatus status;

	public String getMessage() {
		return message;
	}

	public RequestJSONResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public RequestJSONResponse setStatus(HttpStatus status) {
		this.status = status;
		return this;
	}

}
