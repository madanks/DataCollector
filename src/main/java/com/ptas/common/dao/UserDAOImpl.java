package com.ptas.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ptas.common.domain.Organization;
import com.ptas.common.domain.User;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl() {
		setClazz(User.class);
	}
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public User getUser(String username) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.username = :username");
		query.setParameter("username", username);
		userList = query.list();
		if (query.list().size() > 0)
			return userList.get(0);
		else	
			return null;	
	}
	
	
//	@Override
//	protected Session getCurrentSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	@Override
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	@Override
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
}
