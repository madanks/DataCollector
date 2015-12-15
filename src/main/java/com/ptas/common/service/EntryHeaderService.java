package com.ptas.common.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.ptas.common.domain.EntryHeader;


public interface EntryHeaderService {
	public EntryHeader getEntryHeader(Long entryheaderId);
	public EntryHeader findOne(Long id);
	public String findOneID(Long id);
	public String findOneMobID(Long id);
	public EntryHeader findOne(String idOrganizationype, String idValue) throws Exception;	
	public List<EntryHeader> findAll();
	public List<EntryHeader> findAll(String andClause);
	public String getRecordJson() throws Exception;
	public String getPendingJson() throws Exception;
	public void create(EntryHeader entity);
	public void update(EntryHeader entity);
	public String insertEH(JSONObject EH);
	public void delete(EntryHeader entity);
	public void deleteById(Long entityId);
	public String findImageBlobString(String imageName) throws IOException;

}
