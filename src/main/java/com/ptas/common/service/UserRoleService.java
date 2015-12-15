package com.ptas.common.service;

import java.util.List;

import com.ptas.common.domain.UserRole;

public interface UserRoleService {
	
	public UserRole getRole(int id);
	public List<UserRole> findAll();
	public UserRole findOne(String idUserype, String idValue) throws Exception;	
	public void create(UserRole entity);
	public UserRole findId(String username);
	public void update(UserRole entity);
	public void delete(UserRole entity);
	public void deleteById(Long entityId);

}
