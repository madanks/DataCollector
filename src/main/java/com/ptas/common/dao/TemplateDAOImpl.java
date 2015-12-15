package com.ptas.common.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ptas.common.domain.TemplateMaster;


@Repository
public class TemplateDAOImpl extends AbstractDAO<TemplateMaster> implements TemplateDAO {
	
@Autowired
private SessionFactory sessionFactory;
	
	public TemplateDAOImpl() {
		setClazz(TemplateMaster.class);
	}
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
//	public TemplateMaster getTemplateList (Long RecordID) {
//		return findOne(RecordID);	
//	}
	
//	@SuppressWarnings("unchecked")
//	public TemplateMaster getTemplateList() {
//		List<TemplateMaster> TemplateList = new ArrayList<TemplateMaster>();
//		Query query = openSession().createQuery("from templatemaster tm");
//		TemplateList = query.list();
//		if (query.list().size() > 0)
//			return TemplateList.get(0);
//		else	
//			return TemplateList.get(0);	
//	}

	
	public TemplateMaster getTemplate(Long RecordId) {
		return findOne(RecordId);
	}
	

	public TemplateMaster getRecordID(Long RecordID) {
		return findOne(RecordID);
	}

	public void deleteTableDataTM() {
		// TODO Auto-generated method stub
		
	}

	public List<TemplateMaster> getMfgID(String Mfg) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void deleteTableDataTM() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<TemplateMaster> getMfgID(String Mfg) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	/*@Override
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/
}
