package com.ptas.common.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author i80674
 *
 */
@Entity
@Table(name = "organizations")
public class Organization implements Serializable {

	/**
	 **/
	private static final long serialVersionUID = -2591018811076337086L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "organization_id", 
		unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "Country", nullable = true)
	private String country;
	
	@Column(name = "CreatedDate", nullable = true)
	private String createdDate;
	
	@Column(name = "SubscriptionType", nullable = true)
	private String subscriptionType;
	
	@Column(name = "SubscriptionStartDate", nullable = true)
	private String subscriptionStartDate;
	
	@Column(name = "PaymentDue", nullable = true)
	private String paymentDue;
	
	@Column(name = "InspectionCreatedCount", nullable = true)
	private String inspectionCreatedCount;
	
	@Column(name = "primary_contact_name", nullable = false)
	private String primaryContact;

	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "deleted", nullable = false)
	private boolean deleted = false;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<User>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the primaryContact
	 */
	public String getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getSubscriptionStartDate() {
		return subscriptionStartDate;
	}

	public void setSubscriptionStartDate(String subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
	}

	public String getPaymentDue() {
		return paymentDue;
	}

	public void setPaymentDue(String paymentDue) {
		this.paymentDue = paymentDue;
	}

	public String getInspectionCreatedCount() {
		return inspectionCreatedCount;
	}

	public void setInspectionCreatedCount(String inspectionCreatedCount) {
		this.inspectionCreatedCount = inspectionCreatedCount;
	}
	
	
}
