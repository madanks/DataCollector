package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.UserRole;

public interface UserRoleDAO {
	
	public UserRole getRole(int id);
	public List<UserRole> findAll();
	public UserRole findOne(String idUserype, String idValue) throws Exception;	
	public void create(UserRole entity);
	public UserRole findId(String username);
	public void update(UserRole entity);
	public void delete(UserRole entity);
	public void deleteById(Long entityId);
	public void deleteIdR(Long entityId);
}
