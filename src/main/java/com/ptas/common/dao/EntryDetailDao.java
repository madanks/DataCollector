package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.EntryDetails;

public interface EntryDetailDao {
	public EntryDetails getOrganization(Long organizationId);
	public EntryDetails findOne(Long id);
	public EntryDetails findOne(String idOrganizationype, String idValue) throws Exception;	
	public List<EntryDetails> findAll();
	public List<EntryDetails> findAll(String andClause);
	public void create(EntryDetails entity);
	public void update(EntryDetails entity);
	public void delete(EntryDetails entity);
	public void deleteById(Long entityId);

}
