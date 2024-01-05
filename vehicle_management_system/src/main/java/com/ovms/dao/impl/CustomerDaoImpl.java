package com.ovms.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.CustomerDao;
import com.ovms.entity.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		
		return customer;
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		
		criteria.add(Restrictions.eq("email", email));
		Customer customer = (Customer) criteria.uniqueResult();
		
		if (customer == null) {
			return null;
		}
		
		return customer;
	}

}
