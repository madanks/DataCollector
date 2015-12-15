package com.ptas.common.service;

import java.util.List;

import com.ptas.common.domain.TempImage;

public interface TempImageService {
	public TempImage findOne(String imageName);
	public List<TempImage> findAll();
	public List<TempImage> findAll(String andClause);
	public void create(TempImage entity);
	public void update(TempImage entity);

	public void delete(TempImage entity);

	

}
