package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.TemplateDetail;

public interface TemplateDetailDAO {

	public TemplateDetail findOne(Long id);
	public TemplateDetail findOne(String idTemplateType, String idValue) throws Exception;	
	public List<TemplateDetail> findAll();
	public List<TemplateDetail> findAll(String andClause);
	public void create(TemplateDetail entity);
	public void update(TemplateDetail entity);
	public void delete(TemplateDetail entity);
	public void deleteById(Long entityId);
	
}