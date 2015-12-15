package com.ptas.common.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ptas.common.dao.NarrativeLibraryDAO;
import com.ptas.common.domain.NarrativeLibrary;
import com.ptas.common.util.SecurityUtil;

@Service
@Transactional
public class NarrativeLibraryServiceImpl implements NarrativeLibraryService {
	
	@Autowired
	private NarrativeLibraryDAO nld;
	
	public  NarrativeLibrary getOrganization(Long organizationId) {
		return nld.findOne(organizationId);
	}

	public  NarrativeLibrary findOne(Long id) {
		return nld.findOne(id);
	}

	public  NarrativeLibrary findOne(String idOrganizationype, String idValue)
			throws Exception {
		return nld.findOne(idOrganizationype, idValue);
	}

	public List< NarrativeLibrary> findAll() {
		return nld.findAll();
	}

	public List< NarrativeLibrary> findAll(String andClause) {
		return nld.findAll(andClause);
	}

	public void create( NarrativeLibrary entity) {
		nld.create(entity);
	}

	public void update( NarrativeLibrary entity) {
		nld.update(entity);		
	}

	public void delete( NarrativeLibrary entity) {
		nld.delete(entity);		
	}

	public void deleteById(Long entityId) {
		nld.deleteById(entityId);
	}

	public String getValues(String libraryName) throws JsonProcessingException {
		
		List<NarrativeLibrary> values = nld.findAll("AND library='" + libraryName + "' AND oId="+ SecurityUtil.getUserOrganisation());
		ArrayList<String> v= new ArrayList<String>();
		for(NarrativeLibrary nv:values)
		{
			v.add(nv.getValue());
		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(v);
		return json;
		
	}

	public String findAllValue() throws JSONException, JsonProcessingException {
		List<NarrativeLibrary> values = nld.findAll("AND oId="+ SecurityUtil.getUserOrganisation());
		ArrayList<NarrativeLibrary> al= new ArrayList<NarrativeLibrary>();
		for(NarrativeLibrary nv:values)
		{
			al.add(nv);
		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(al);
		return json;
	}
	

}
