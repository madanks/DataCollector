package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.NarrativeLibrary;
import com.ptas.common.domain.TemplateMaster;

public interface NarrativeLibraryDAO {
	public NarrativeLibrary findOne(Long id);
	public NarrativeLibrary findOne(String idOrganizationype, String idValue) throws Exception;	
	public NarrativeLibrary find(String andClause);
	public List<NarrativeLibrary> findAll();
	public List<NarrativeLibrary> findAll(String andClause);
	public void create(NarrativeLibrary entity);
	public void update(NarrativeLibrary entity);
	public void delete(NarrativeLibrary entity);
	public void deleteById(Long entityId);

}
