package com.ovms.enums;

public enum RoleType {

	ROLE_SUPERADMIN(501), ROLE_ADMIN(502), ROLE_RECEPTIONIST(503), ROLE_MANAGER(504), ROLE_USER(505);

	private Integer roleId = null;

	RoleType(Integer roleId) {
		this.roleId = roleId;
		// TODO Auto-generated constructor stub
	}

	public Integer getRoleId() {
		return roleId;
	}

}
