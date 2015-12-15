package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.TemplateMaster;

public interface TemplateDAO {
	public TemplateMaster getRecordID(Long RecordID);
	public TemplateMaster findOne(Long id);
	public TemplateMaster findOne(String idTemplateType, String idValue) throws Exception;	
	public List<TemplateMaster> findAll();
	public List<TemplateMaster> findAll(String andClause);
	public List<String> findAllDisRow(String andClause, String row );
	public TemplateMaster find(String andClause);
	public void create(TemplateMaster entity);
	public void update(TemplateMaster entity);
	public void delete(TemplateMaster entity);
	public void deleteTableDataTM();
	public List<TemplateMaster> getMfgID(String Mfg);
	public void deleteById(Long entityId);
}