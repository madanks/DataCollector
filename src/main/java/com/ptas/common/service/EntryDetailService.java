package com.ptas.common.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ptas.common.domain.EntryDetails;

public interface EntryDetailService {
	public EntryDetails getOrganization(Long organizationId);
	public EntryDetails findOne(Long id);
	public EntryDetails findOne(String idOrganizationype, String idValue) throws Exception;	
	public List<EntryDetails> findAll();
	public List<EntryDetails> findAll(String andClause);
	public void create(EntryDetails entity);
	public String getRecordJson() throws Exception;
	public String insert(JSONArray jsonArray);
	public String UpdateJson(JSONObject jsonObject);
	public void update(EntryDetails entity);
	public void delete(EntryDetails entity);
	public void deleteById(Long entityId);
	public String SaveImage(MultipartHttpServletRequest request) throws IllegalStateException, IOException;
	public String UpdateNewJson(JSONObject jsonDetail) throws SQLException, IOException;
	public String InsertHeaderDetailJson(JSONObject jsonDetail) throws SerialException, SQLException, IOException;

}
