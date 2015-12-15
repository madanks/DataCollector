package com.ptas.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ptas.common.domain.UserRole;

@Repository
public class UserRoleDAOImpl extends AbstractDAO<UserRole> implements UserRoleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRoleDAOImpl() {
		setClazz(UserRole.class);
	}
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	public UserRole getRole(int id) {
		UserRole role = (UserRole) getCurrentSession().load(UserRole.class, id);
		return role;
	}

	public UserRole findId(String username) {
		String hql="FROM TemplateMaster WHERE username ='"+ username + "'"+"";
		Query que = getCurrentSession().createQuery(hql);
		
		UserRole ur= (UserRole) que.uniqueResult();
		//System.out.println(ur.getUserRoleId());
		return ur;
	}



}
