package com.ptas.common.service;

import java.util.List;

import com.ptas.common.domain.User;


public interface JSONService {
	public User listInspection(String id);
	public List<User> findAll();
}
