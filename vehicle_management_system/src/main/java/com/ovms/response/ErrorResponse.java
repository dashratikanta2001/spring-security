package com.ovms.response;

public class ErrorResponse<T> {

	private Integer status;
	
	private String message;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
}
