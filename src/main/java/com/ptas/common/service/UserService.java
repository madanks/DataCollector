package com.ptas.common.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ptas.common.domain.User;

public interface UserService {
	public User getUser(String login);
	public User findOne(String idUserype, String idValue) throws Exception;	
	public List<User> findAll();
	public List<User> findAll(String andClause);
	public void create(User entity);
	public void update(User entity);
	public void deactivate(User entity);
	public void delete(User entity);
	public void deleteById(Long entityId);
	public String ChangePassword(JSONObject userService);
	public void create1(User user1);
	public void UserSignup(JSONObject signupData) throws JSONException, IOException, ParseException, InvalidFormatException;
	public List<String> findUsers();
	public String AssignUserJSON() throws JsonProcessingException;
	public String checkpass(String trim);
}
