package com.ptas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptas.common.dao.UserDAO;
import com.ptas.common.domain.User;

@Service
@Transactional
public class JSONServiceImpl implements JSONService {
	
	@Autowired
	private UserDAO userDAO;

	public User listInspection(String id) {
		try {
			return userDAO.findOne("username", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<User> findAll() {
return userDAO.findAll();		
	}
}
