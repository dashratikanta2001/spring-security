package com.ovms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.BrandsDao;
import com.ovms.entity.Brands;
import com.ovms.enums.VehicleType;

@Repository
@Transactional
public class BrandsDaoImpl implements BrandsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Brands save(Brands brands) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(brands);

		return brands;
	}

	@Override
	public List<Brands> allBrands() {
		// TODO Auto-generated method stub
		List<Brands> brandList = sessionFactory.getCurrentSession().createQuery("from Brands", Brands.class)
				.getResultList();

		return brandList;
	}

	@Override
	public Brands find(Brands brands) {
		// TODO Auto-generated method stub

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Brands.class);

		criteria.add(Restrictions.eq("name", brands.getName()));
		criteria.add(Restrictions.eq("vehicleType", brands.getVehicleType()));

		Brands brand = (Brands) criteria.uniqueResult();

		return brand;

	}

	@Override
	public List<Brands> findByvehicleType(VehicleType vehicleType) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Brands.class);
		
		criteria.add(Restrictions.eq("vehicleType", vehicleType));
		
		return criteria.list();
	}

}
