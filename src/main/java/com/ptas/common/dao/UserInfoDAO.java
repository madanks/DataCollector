package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.UserInfo;

public interface UserInfoDAO {
	public UserInfo findOne(Long id);
	public List<UserInfo> findAll();
	public List<UserInfo> findAll(String andClause);
	public void create(UserInfo entity);
	public void update(UserInfo entity);
	public void delete(UserInfo entity);
	public void deleteById(Long entityId);


}
