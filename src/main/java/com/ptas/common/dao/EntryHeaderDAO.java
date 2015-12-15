package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.EntryHeader;;

public interface EntryHeaderDAO {
	public EntryHeader getEntryHeader(Long entryheaderId);
	public EntryHeader findOne(Long id);
	public EntryHeader findOne(String idOrganizationype, String idValue) throws Exception;	
	public List<EntryHeader> findAll();
	public List<EntryHeader> findAll(String andClause);
	public void create(EntryHeader entity);
	public void update(EntryHeader entity);
	public void delete(EntryHeader entity);
	public void deleteById(Long entityId);
}
