package com.ptas.common.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ptas.common.domain.TempImage;

@Repository
public class TempImageDAOImpl extends AbstractDAO<TempImage> implements TempImageDAO  {
	
	public TempImageDAOImpl() {
		setClazz(TempImage.class);
	}
	
	public TempImage getEntryHeader(String entryHeaderID) {
		return findOne(entryHeaderID);
	}

	public TempImage findOne(String imageName) {
		String hql="FROM TempImage WHERE imageName ='"+ imageName + "'"+"";
		Query que = getCurrentSession().createQuery(hql);
		
		TempImage ur= (TempImage) que.uniqueResult();
		//System.out.println(ur.getUserRoleId());
		return ur;

	}



}
