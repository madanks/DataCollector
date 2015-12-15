package com.ptas.common.service;

import java.util.ArrayList;

public class Section {
	
	private String group;
	private Long id;
	
	private ArrayList<JSONTemplete> GroupItems;
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArrayList<JSONTemplete> getGroupItems() {
		return GroupItems;
	}
	public void setGroupItems(ArrayList<JSONTemplete> groupItems) {
		GroupItems = groupItems;
	}

}
