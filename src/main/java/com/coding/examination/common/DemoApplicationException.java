package com.coding.examination.common;

import java.util.Objects;

import org.springframework.http.HttpStatus;

public class DemoApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;

	public DemoApplicationException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemoApplicationException other = (DemoApplicationException) obj;
		return Objects.equals(message, other.message) && status == other.status;
	}

}