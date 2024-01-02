package com.ovms.response;

public enum Response {

	SUCCESS(200,"SUCCESS");
//	, BAD_REQUEST(500), METHOD_NOT_FOUND(400);

	private Integer code;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Response(Integer code,String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
