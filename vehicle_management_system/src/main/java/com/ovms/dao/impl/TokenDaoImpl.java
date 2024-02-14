package com.ovms.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ovms.dao.TokenDao;
import com.ovms.entity.Token;

@Repository
@Transactional
public class TokenDaoImpl implements TokenDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addToken(String useraname, String token) {
		// TODO Auto-generated method stub
		
		Token token2 = new Token(useraname, token);
			sessionFactory.getCurrentSession().saveOrUpdate(token2);
//			sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteToken(String username) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().delete(getTokenByusername(username));
		
//		sessionFactory.getCurrentSession().dele
		
	}

	@Override
	public Token getTokenByusername(String username) {
		// TODO Auto-generated method stub
//		Token token = sessionFactory.getCurrentSession().get(Token.class, username);
		
		Token token = (Token) sessionFactory.getCurrentSession().createCriteria(Token.class).add(Restrictions.eq("username", username)).uniqueResult();
		return token;
	}

	@Override
	public Boolean tokenAvailable(String username, String token) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Token.class);
		
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("token", token));
		
		
		return criteria.uniqueResult()==null?false:true;
	}

}
