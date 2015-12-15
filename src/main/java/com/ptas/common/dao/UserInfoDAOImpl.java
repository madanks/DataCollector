package com.ptas.common.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ptas.common.domain.UserInfo;

@Repository
public class UserInfoDAOImpl extends AbstractDAO<UserInfo> implements UserInfoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
		
		public UserInfoDAOImpl() {
			setClazz(UserInfo.class);
		}
	

}
