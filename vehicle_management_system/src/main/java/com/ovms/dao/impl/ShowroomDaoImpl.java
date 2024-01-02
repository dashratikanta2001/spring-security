package com.ovms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.BrandsDao;
import com.ovms.dao.ShowroomDao;
import com.ovms.entity.Brands;
import com.ovms.entity.Showroom;
import com.ovms.enums.VehicleType;
import com.ovms.exception.InvalidVehicleTypeException;

@Repository
@Transactional
public class ShowroomDaoImpl implements ShowroomDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private BrandsDao brandsDao;
	
	@Override
	public Showroom save(Showroom showroom) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(showroom);
		
		
		return showroom;
	}

	@Override
	public List<Showroom> findAll() {
		// TODO Auto-generated method stub
		
		List<Showroom> showrooms = sessionFactory.getCurrentSession().createQuery("from Showroom", Showroom.class).getResultList();
		return showrooms;
	}

	@Override
	public List<Showroom> findByType(String type) {
		// TODO Auto-generated method stub
		
		List<Brands> byvehicleType = brandsDao.findByvehicleType(VehicleType.valueOf(VehicleType.class, type));
		
//		try {
//			brands.setVehicleType(VehicleType.valueOf(VehicleType.class, brandDto.getVehicleType().toUpperCase()));
//			return brands;
//		} catch (Exception e) {
//			throw new InvalidVehicleTypeException("Invalid vehicle type");
//		}
		
		sessionFactory.getCurrentSession().createCriteria(getClass());
		
		return null;
	}

}
