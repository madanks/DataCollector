package com.ptas.common.dao;

import java.util.List;

import com.ptas.common.domain.TempImage;

public interface TempImageDAO {
	public TempImage findOne(String imageName);
	public List<TempImage> findAll();
	public List<TempImage> findAll(String andClause);
	public void create(TempImage entity);
	public void update(TempImage entity);
	public void delete(TempImage entity);



}
