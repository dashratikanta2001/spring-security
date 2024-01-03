package com.ovms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.ovms.dao.VehicleDao;
import com.ovms.entity.Vehicle;

public class VehicleDaoImpl implements VehicleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Vehicle addNewVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().save(vehicle);
		
		return vehicle;
	}

	@Override
	public List<Vehicle> findByShowroom(String showroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findByShowroomAndBrand(String showroom, String brand) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
		
		criteria.add(Restrictions.eq("showroom.", criteria));
		
		return null;
	}
	
	
	

}
