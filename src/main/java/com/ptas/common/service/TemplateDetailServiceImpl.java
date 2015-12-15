package com.ptas.common.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ptas.common.dao.TemplateDAO;
import com.ptas.common.dao.TemplateDetailDAO;
import com.ptas.common.domain.TemplateDetail;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.util.SecurityUtil;

@Service
@Transactional
public class TemplateDetailServiceImpl implements TemplateDetailService {

	@Autowired
	private TemplateDetailDAO templatedetailDAO;
	
	@Autowired
	private TemplateDAO templateDAO;

	public TemplateDetail getRecordID(Long RecordID) {
		return templatedetailDAO.findOne(RecordID);
	}

	public TemplateDetail findByID(Long id) {
		return templatedetailDAO.findOne(id);
	}

	public TemplateDetail findOne(String idTemplateType, String idValue)
			throws Exception {
		return templatedetailDAO.findOne(idTemplateType, idValue);
	}

	public List<TemplateDetail> findAll() {
		return templatedetailDAO.findAll();
	}


	public List<TemplateDetail> findAll(String andClause) {
		return templatedetailDAO.findAll(andClause);
	}

	public void create(TemplateDetail entity) {
		templatedetailDAO.create(entity);
	}

	public void update(TemplateDetail entity) {
		templatedetailDAO.update(entity);
	}

	public void delete(TemplateDetail entity) {
		templatedetailDAO.delete(entity);
	}

	public void deleteById(Long entityId) {
		templatedetailDAO.deleteById(entityId);
	}

	public TemplateDetail findOne(Long id) {
		return templatedetailDAO.findOne(id);
	}

	public void updateDetail(TemplateDetail entity) {
		
		
		try {
			//System.out.println("-----------------ok----------------");
			TemplateMaster tmObj=entity.getTemplateMaster();
			System.out.println(tmObj.getTemplateNumber());
			//TemplateMaster tm=templateDAO.find("AND templateNumber="+tmObj.getTemplateNumber());
			TemplateMaster tm=templateDAO.findOne(tmObj.getTemplateNumber());
			entity.setTemplateMaster(tm);
			entity.setUpdated(SecurityUtil.getUserId());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			Date date = dateFormat.parse(dateFormat.format(cal.getTime()));
			entity.setUpdatedDate(date);
			templatedetailDAO.update(entity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
	}

}
