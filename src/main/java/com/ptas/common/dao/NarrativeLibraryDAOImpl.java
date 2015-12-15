package com.ptas.common.dao;

import org.springframework.stereotype.Repository;

import com.ptas.common.domain.NarrativeLibrary;

@Repository
public class NarrativeLibraryDAOImpl extends AbstractDAO<NarrativeLibrary> implements NarrativeLibraryDAO {
	
	public NarrativeLibraryDAOImpl()
	{
		setClazz(NarrativeLibrary.class);
	}
	

}
