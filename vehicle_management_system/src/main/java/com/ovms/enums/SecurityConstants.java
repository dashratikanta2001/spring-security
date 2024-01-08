package com.ovms.enums;

public enum SecurityConstants {

	HEADER("Authorization");
	
	private String header;

	private SecurityConstants(String header) {
		// TODO Auto-generated constructor stub
		this.header = header;
	}
	
		
	public String getHeader() {
		return header;
	}

//
//
//	private Integer roleId = null;
//
//	RoleType(Integer roleId) {
//		this.roleId = roleId;
//		// TODO Auto-generated constructor stub
//	}
//
//	public Integer getRoleId() {
//		return roleId;
//	}
}
