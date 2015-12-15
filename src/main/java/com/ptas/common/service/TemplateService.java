package com.ptas.common.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.ptas.common.domain.TemplateMaster;

public interface TemplateService {
	public TemplateMaster getRecordID(Long RecordID);
	public TemplateMaster findOne(Long id);
	public TemplateMaster findOne(String idTemplateType, String idValue) throws Exception;	
	public List<TemplateMaster> findAll();
	public List<TemplateMaster> findAll(String andClause);
	public MfgJson findDistinct() throws Exception;
	public String findTemplateName(String m, String t) throws Exception;
	public String findTemplateMainJson(String m, String t, String tn) throws Exception;
	public void findReportContent(Long did,String filePath) throws Exception;
	public void create(TemplateMaster entity);
	public void update(TemplateMaster entity);
	public void delete(TemplateMaster entity);
	public void deleteById(Long entityId);
	public TemplateMaster findTemplate(String manufacturer,
			String templateType, String templatename);
	public String findOfflineJson() throws Exception;
}