package com.ovms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.VehicleDao;
import com.ovms.entity.Brands;
import com.ovms.entity.Showroom;
import com.ovms.entity.Vehicle;

@Repository
@Transactional
public class VehicleDaoImpl implements VehicleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Vehicle addNewVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(vehicle);
		return vehicle;
	}

	@Override
	public List<Vehicle> findByShowroom(Showroom showroom) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
		
		criteria.add(Restrictions.eq("showroom", showroom));
		
		List list = criteria.list();
		System.out.println("Showroom list = "+list);
		
		return list;
	}

	@Override
	public List<Vehicle> findByShowroomAndBrand(Integer showroomId, Integer brandId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Vehicle.class);
		
		Showroom showroom = session.get(Showroom.class, 6);
		Brands brand = session.get(Brands.class, 7);
		
		System.out.println("Show room = "+showroom);
		
		criteria.add(Restrictions.eq("showroom", showroom));
		criteria.add(Restrictions.eq("brand", brand));
		
		List list = criteria.list();
		System.out.println("List = "+list);
		
		return null;
	}

	@Override
	public Vehicle RegisterVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle findByRegistrationNo(String registrationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle findByEngineNumber(String engineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle findByChesisNumber(String chesisNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle findByEngineAndChessis(String engineNumber, String chesisNumber) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
		
//		criteria.add(Restrictions.eq("engineNumber", engineNumber));
//		criteria.add(Restrictions.eq("chesisNumber", chesisNumber));
		
		criteria.add(Restrictions.or(
				Restrictions.eq("engineNumber", engineNumber),
				Restrictions.eq("chesisNumber", chesisNumber)
				));
		
		return (Vehicle) criteria.uniqueResult();
	}

	@Override
	public Integer countLastVehicleNumber(Showroom showroom) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
		
		criteria.add(Restrictions.eq("showroom", showroom));
		
		int size = criteria.list().size();
		
		System.out.println("Number of vehicle = "+size);
		return size;
	}
	
	
	

}
