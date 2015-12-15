package com.ptas.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ptas.common.domain.TemplateDetail;
import com.ptas.common.domain.TemplateMaster;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public abstract class AbstractDAO<T extends Serializable> {

	private Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	public final void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T findOne(Long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public T findOne(String idType, String idValue) throws Exception {
		T result = null;
		try {
			final Query query = getCurrentSession().createQuery(
					"select gType from " + clazz.getName() + " gType where "
							+ idType + "=:idValue");
			query.setParameter("idValue", idValue);
			result = (T) query.uniqueResult();
		} catch (final HibernateException e) {
			throw new Exception(e);

		}
		return (T) result;
	}
	
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	public List<T> findAll(String andClause) {
		return getCurrentSession().createQuery(
				"from " + clazz.getName() + " WHERE 1 = 1 " + andClause).list();
	}

	public List<String> findAllDisRow(String andClause, String row) {
		return getCurrentSession().createQuery(
				"select distinct " + row + " from " + clazz.getName()
						+ " WHERE 1 = 1 " + andClause).list();
	}

	public T find(String andClause) {
		return (T) getCurrentSession().createQuery(
				"from " + clazz.getName() + " WHERE 1 = 1 " + andClause)
				.uniqueResult();
	}

	public void create(T entity) {
		getCurrentSession().persist(entity);

	}

	public void insert(T entity) {

		getCurrentSession().persist(entity);
	}

	public void update(T entity) {
		getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
		//getCurrentSession().flush();
	}

	public void deleteIdR(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
		getCurrentSession().flush();
	}

	// protected abstract Session getCurrentSession();
	// protected abstract SessionFactory getSessionFactory();
	// protected abstract void setSessionFactory(SessionFactory sessionFactory);

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