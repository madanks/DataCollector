/**
 * 
 */
package com.ptas.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ptas.common.domain.EntryDetails;
import com.ptas.common.domain.EntryHeader;

/**
 * @author Maddy
 *
 */
public class EntryJson {
	
	private EntryHeader entryheader;
	
	private ArrayList<EntryDetails> listentryDetails;
	
	public EntryHeader getEntryheader() {
		return entryheader;
	}
	public void setEntryheader(EntryHeader entryheader) {
		this.entryheader = entryheader;
	}
	public ArrayList<EntryDetails> getListentryDetails() {
		return listentryDetails;
	}
	public void setListentryDetails(ArrayList<EntryDetails> listentryDetails) {
		this.listentryDetails = listentryDetails;
	}

	
	

}
