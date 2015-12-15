package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.EntryHeader;
import com.ptas.common.domain.Organization;
import com.ptas.common.domain.User;

public interface UserDAO {
	
	public User getUser(String login);
	public User findOne(String idUserype, String idValue) throws Exception;	
	public List<User> findAll();
	public void create(User entity);
	public List<User> findAll(String andClause);
	public void update(User entity);
	public void delete(User entity);
	public void deleteById(Long entityId);
	public User findOne(Long id);

}
