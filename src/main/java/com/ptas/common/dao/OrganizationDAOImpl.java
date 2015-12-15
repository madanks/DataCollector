package com.ptas.common.dao;

import org.springframework.stereotype.Repository;

import com.ptas.common.domain.Organization;

@Repository
public class OrganizationDAOImpl extends AbstractDAO<Organization> implements OrganizationDAO {

	public OrganizationDAOImpl() {
		setClazz(Organization.class);
	}
	
	public Organization getOrganization(Long organizationId) {
		return findOne(organizationId);
	}

}
