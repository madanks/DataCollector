package com.ptas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptas.common.dao.UserRoleDAO;
import com.ptas.common.domain.UserRole;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDAO roleDAO;

	public UserRole getRole(int userRoleId) {
		return roleDAO.getRole(userRoleId);
	}
	
	public List<UserRole> findAll() {
		return roleDAO.findAll();
	}
	
	public UserRole findOne(String idUserype, String idValue)
			throws Exception {
		return roleDAO.findOne(idUserype, idValue);
	}

	public void create(UserRole entity) {
		roleDAO.create(entity);
	}

	public void update(UserRole entity) {
		roleDAO.update(entity);		
	}

	public void delete(UserRole entity) {
		roleDAO.delete(entity);		
	}

	public void deleteById(Long entityId) {
		roleDAO.deleteById(entityId);
	}

	public UserRole findId(String username) {
		return roleDAO.findId(username);
		
	}

}
