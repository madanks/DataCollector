package com.ptas.common.util;

public class Paginator {

	/* page size, number of records to fetch from beginRecord */
	private int numberOfRecordsToFetch = 20;
	/* total number of records */
	private long totalRecordCount;
	/* current page number in the request */
	private int currentPage = 0;	

	/**
	 * @return the beginRecord
	 */
	public int getBeginRecord() {
		return (currentPage) * numberOfRecordsToFetch +1 ;
	}

	/**
	 * @return the numberOfRecordsToFetch
	 */
	public int getNumberOfRecordsToFetch() {
		return numberOfRecordsToFetch;
	}

	/**
	 * @param numberOfRecordsToFetch
	 *            the numberOfRecordsToFetch to set
	 */
	public void setNumberOfRecordsToFetch(int numberOfRecordsToFetch) {
		this.numberOfRecordsToFetch = numberOfRecordsToFetch;
	}

	/**
	 * @return the totalRecordCount
	 */
	public long getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * @param totalRecordCount
	 *            the totalRecordCount to set
	 */
	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	

}
