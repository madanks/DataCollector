package com.ptas.common.domain;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

@SuppressWarnings("serial")
public class Pagination<E> extends PagedListHolder<E> {

	int numberOfElements;
	
	String sortField;
	String order;
	
	public Pagination(List<E> source) {
		super(source);
	}
	
	/**
	 * Return a sub-list representing the current page.
	 */
	public List<E> getPageList() {
		return getSource();
	}
	
	/**
	 * Return the total number of elements in the source list.
	 */
	public int getNrOfElements() {
		return numberOfElements;
	}

	/**
	 * @param count the count to set
	 */
	public void setNumberOfElements(int count) {
		this.numberOfElements = count;
	}

	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	
}
