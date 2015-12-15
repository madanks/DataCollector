package com.ptas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptas.common.dao.TempImageDAO;
import com.ptas.common.domain.TempImage;

public class TempImageServiceImpl implements TempImageService {

	@Autowired
	private TempImageDAO tempImageDAO;
	
	public TempImage findOne(String imageName) {
		
		return tempImageDAO.findOne(imageName);
	}

	@Override
	public List<TempImage> findAll() {
		// TODO Auto-generated method stub
		return tempImageDAO.findAll();
	}

	@Override
	public List<TempImage> findAll(String andClause) {
		// TODO Auto-generated method stub
		return tempImageDAO.findAll(andClause);
	}

	@Override
	public void create(TempImage entity) {
		tempImageDAO.create(entity);
		
	}

	@Override
	public void update(TempImage entity) {
		tempImageDAO.update(entity);
		
	}

	
	public void delete(TempImage entity) {
		tempImageDAO.delete(entity);
		
	}

}
