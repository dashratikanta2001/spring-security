package com.ovms.dao;

import com.ovms.entity.Role;
import com.ovms.enums.RoleType;

public interface RoleDao {

	Role findRole(RoleType role);
	
	void addRole(Role role);
}
