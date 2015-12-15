package com.ptas.common.service;

import java.util.List;

import com.ptas.common.domain.Organization;

public interface OrganizationService {
	public Organization getOrganization(Long organizationId);
	public Organization findOne(Long id);
	public Organization findOne(String idOrganizationype, String idValue) throws Exception;	
	public List<Organization> findAll();
	public List<Organization> findAll(String andClause);
	public void create(Organization entity);
	public void update(Organization entity);
	public void delete(Organization entity);
	public void deleteById(Long entityId);
}
