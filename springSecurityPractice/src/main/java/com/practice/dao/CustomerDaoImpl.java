package com.practice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.practice.dto.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Customer> findCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		
		String sql = "select * from customers where username=?";
		Object[] args = {username};
		
		List<Customer> customer = jdbcTemplate.query(sql, args,new BeanPropertyRowMapper<Customer>(Customer.class));
		
		return customer;
	}

}
