package com.ovms.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.CridentialMasterDao;
import com.ovms.entity.CridentialMaster;

@Repository
@Transactional
public class CridentialMasterDaoImpl implements CridentialMasterDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCridential(CridentialMaster cridentialMaster) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(cridentialMaster);
		
	}

	@Override
	public void updateCridential(CridentialMaster cridentialMaster) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().update(cridentialMaster);

	}

	@Override
	public CridentialMaster getByUsername(String username) {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CridentialMaster.class);
		
		criteria.add(Restrictions.eq("username", username));
		
		return (CridentialMaster) criteria.uniqueResult();
	}

}
