package com.ptas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptas.common.dao.UserInfoDAO;
import com.ptas.common.domain.UserInfo;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDAO userInfoDAO;

	public UserInfo findOne(Long id) {
		return userInfoDAO.findOne(id);
	}

	public List<UserInfo> findAll() {
		return userInfoDAO.findAll();
	}

	public List<UserInfo> findAll(String andClause) {
		return userInfoDAO.findAll(andClause);
	}

	public void create(UserInfo entity) {
		userInfoDAO.create(entity);
	}

	public void update(UserInfo entity) {
		userInfoDAO.update(entity);		
	}

	public void delete(UserInfo entity) {
		userInfoDAO.delete(entity);		
	}

	public void deleteById(Long entityId) {
		userInfoDAO.deleteById(entityId);
	}
}
