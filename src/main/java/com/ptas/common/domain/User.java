package com.ptas.common.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author i80674
 * 
 */
@Entity
@Table(name = "users", catalog = "Survey", 
	uniqueConstraints = @UniqueConstraint(
		columnNames = {"user_name" }))
public class User implements Serializable, UserDetails, CredentialsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5948965812658232097L;

	@Id
	@Column(unique = true, nullable = false, name = "user_name")
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "updated_by", nullable = true)
	private Long updated;
	
	@Transient
	public String creater;
	
	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date createdDate;

	@Column(name = "enabled")
	private boolean enabled=true;

	@Column(name = "account_non_expired")
	private boolean accountNonExpired= true;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired= true;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked= true;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	public String getUsername() {
		return username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public void eraseCredentials() {
		password = "";
		//username = "";
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}



	/**
	 * @return the userRole
	 */
	public Set<UserRole> getUserRole() {
		return userRole;
	}


	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}


	public boolean isEnabled() {
		return enabled;
	}

	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @return the updated
	 */
	public Long getUpdated() {
		return updated;
	}


	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Long updated) {
		this.updated = updated;
	}


	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}


	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the userDetail
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userDetail
	 *            the userDetail to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @param accountNonExpired the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	/**
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	/**
	 * @param accountNonLocked the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	
}
