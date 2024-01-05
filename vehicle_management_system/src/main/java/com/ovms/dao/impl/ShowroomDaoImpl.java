package com.ovms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.BrandsDao;
import com.ovms.dao.ShowroomDao;
import com.ovms.entity.Showroom;
import com.ovms.enums.VehicleType;

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

		List<Showroom> showrooms = sessionFactory.getCurrentSession().createQuery("from Showroom", Showroom.class)
				.getResultList();
		
		System.out.println("Showroom list = == = "+showrooms);
		return showrooms;
	}

	@Override
	public List<Showroom> findByType(VehicleType type) {
		// TODO Auto-generated method stub

//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Showroom.class);
//		criteria.add(Restrictions.eq("vehicleType", type));
//		return criteria.list();

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Showroom> criteriaQuery = criteriaBuilder.createQuery(Showroom.class);
		Root<Showroom> showroomRoot = criteriaQuery.from(Showroom.class);
		Predicate predicate = criteriaBuilder.equal(showroomRoot.join("brand").get("vehicleType"), type);
		criteriaQuery.where(predicate);
		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Showroom> findByBrand(String brandName) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Showroom> criteriaQuery = criteriaBuilder.createQuery(Showroom.class);

		Root<Showroom> showroomRoot = criteriaQuery.from(Showroom.class);
//        Join<Showroom, Brands> brandJoin = showroomRoot.join("brand");

		Predicate predicate = criteriaBuilder.equal(showroomRoot.join("brand").get("name"), brandName);
		criteriaQuery.where(predicate);

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Showroom> findByCity(String city) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Showroom.class);
		criteria.add(Restrictions.eq("address", city));
		return criteria.list();
	}

	@Override
	public Showroom findById(Integer id) {
		// TODO Auto-generated method stub
		
		Showroom showroom = sessionFactory.getCurrentSession().get(Showroom.class, id);
		return showroom;
	}
	
	
	//Just trying 
	//Dont use this in future
	//Manajit told.
	/*
	@Override
	public List<Showroom> findByBrand(String brandName) {
//		try {
			
			Session session = sessionFactory.getCurrentSession() ;
			Criteria criteria = session.createCriteria(Showroom.class);
			criteria.createAlias("brand", "b");
			criteria.add(Restrictions.eq("b.name", brandName));
			return criteria.list();

//		} catch (Exception e) {
//			e.printStackTrace();

//			return null;
//		}
	}
	*/
}
