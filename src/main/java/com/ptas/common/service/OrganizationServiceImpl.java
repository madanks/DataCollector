package com.ptas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptas.common.dao.OrganizationDAO;
import com.ptas.common.domain.Organization;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationDAO organizationDAO;

	public Organization getOrganization(Long organizationId) {
		return organizationDAO.findOne(organizationId);
	}

	public Organization findOne(Long id) {
		return organizationDAO.findOne(id);
	}

	public Organization findOne(String idOrganizationype, String idValue)
			throws Exception {
		return organizationDAO.findOne(idOrganizationype, idValue);
	}

	public List<Organization> findAll() {
		return organizationDAO.findAll();
	}

	public List<Organization> findAll(String andClause) {
		return organizationDAO.findAll(andClause);
	}

	public void create(Organization entity) {
		organizationDAO.create(entity);
	}

	public void update(Organization entity) {
		organizationDAO.update(entity);		
	}

	public void delete(Organization entity) {
		organizationDAO.delete(entity);		
	}

	public void deleteById(Long entityId) {
		organizationDAO.deleteById(entityId);
	}
}
