package com.ovms.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.RoleDao;
import com.ovms.entity.Role;
import com.ovms.enums.RoleType;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findRole(RoleType role) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
		
		criteria.add(Restrictions.eq("role", role.toString()));
		
		return (Role) criteria.uniqueResult();
	}

	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().save(role);
		
	}

}
