package com.ptas.common.dao;

import org.springframework.stereotype.Repository;

import com.ptas.common.domain.EntryHeader;;

@Repository
public class EntryHeaderDAOImpl extends AbstractDAO<EntryHeader> implements EntryHeaderDAO {
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	public EntryHeaderDAOImpl() {
		setClazz(EntryHeader.class);
	}
	
	public EntryHeader getEntryHeader(Long entryHeaderID) {
		return findOne(entryHeaderID);
	}
	
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
