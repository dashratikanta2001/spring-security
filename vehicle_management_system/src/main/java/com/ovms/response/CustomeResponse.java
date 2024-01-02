package com.ovms.response;

public class CustomeResponse<T> {

	private Integer status;

	private Object data;

	private String message;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomeResponse(Object data, Integer status, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public CustomeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
