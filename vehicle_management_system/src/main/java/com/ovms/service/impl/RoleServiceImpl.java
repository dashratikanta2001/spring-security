package com.ovms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovms.dao.RoleDao;
import com.ovms.entity.Role;
import com.ovms.enums.RoleType;
import com.ovms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role findRole(RoleType roleType) {
		// TODO Auto-generated method stub
		Role role = roleDao.findRole(roleType);
		return role;
	}

	@Override
	public Integer addnewRole(RoleType roleType) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRole(roleType.toString());
		roleDao.addRole(role);
		return 1;
	}

}
