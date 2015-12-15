package com.ptas.common.service;

import java.util.List;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ptas.common.domain.NarrativeLibrary;

public interface NarrativeLibraryService {
	
	public NarrativeLibrary findOne(Long id);
	public NarrativeLibrary findOne(String idOrganizationype, String idValue) throws Exception;	
	public List<NarrativeLibrary> findAll();
	public List<NarrativeLibrary> findAll(String andClause);
	public void create(NarrativeLibrary entity);
	public void update(NarrativeLibrary entity);
	public void delete(NarrativeLibrary entity);
	public void deleteById(Long entityId);
	public String getValues(String libraryName) throws JsonProcessingException;
	public String findAllValue() throws JSONException, JsonProcessingException;

}
