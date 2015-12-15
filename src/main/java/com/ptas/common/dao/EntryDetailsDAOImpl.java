package com.ptas.common.dao;

import org.springframework.stereotype.Repository;

import com.ptas.common.domain.EntryDetails;;

@Repository
public class EntryDetailsDAOImpl extends AbstractDAO<EntryDetails> implements EntryDetailDao {
	public EntryDetailsDAOImpl() {
		setClazz(EntryDetails.class);
	}
	
	public EntryDetails getOrganization(Long eRecordID) {
		return findOne(eRecordID);
	}
}
