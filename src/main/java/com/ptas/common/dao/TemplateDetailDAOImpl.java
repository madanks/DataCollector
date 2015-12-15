package com.ptas.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ptas.common.domain.TemplateDetail;

@Repository
public class TemplateDetailDAOImpl extends AbstractDAO<TemplateDetail> implements TemplateDetailDAO{
	
@Autowired
private SessionFactory sessionFactory;
	
	public TemplateDetailDAOImpl() {
		setClazz(TemplateDetail.class);
	}

public	List<TemplateDetail> fullQueryBody(String query) throws Exception {
		
		String hql = query;
		Query que = getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<TemplateDetail> results = que.list();
		return results;	
	}
	
	public TemplateDetail getTemplate(Long RecordId) {
		return findOne(RecordId);
	}
	
	public TemplateDetail getRecordID(Long RecordID) {
		return findOne(RecordID);
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
