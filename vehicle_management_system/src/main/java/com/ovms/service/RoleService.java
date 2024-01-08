package com.ovms.service;

import com.ovms.entity.Role;
import com.ovms.enums.RoleType;

public interface RoleService {

	Role findRole(RoleType role);
	Integer addnewRole(RoleType roleType);
}
