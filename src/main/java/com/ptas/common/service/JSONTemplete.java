package com.ptas.common.service;



import java.util.ArrayList;

public class JSONTemplete {
	
	private String Group;
	private Long id;
	private ArrayList<Body> GroupItems;
	
	public JSONTemplete(){
		super();
		this.GroupItems=new ArrayList<Body>();
		
	}
	
	/**
	 * @return the groupItems
	 */
	public ArrayList<Body> getGroupItems() {
		return GroupItems;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param groupItems the groupItems to set
	 */
	public void setGroupItems(ArrayList<Body> groupItems) {
		GroupItems = groupItems;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return Group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		Group = group;
	}
	public void addBody(Body bd){
		this.GroupItems.add(bd);
	}
	
		
}

