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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "entryheader")
public class EntryHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "entry_ID", 
		unique = true, nullable = false)
	private Long id;
	
	@Column(name = "Org_ID", nullable = false)
	private Long orgId;
	
	@Column(name = "VIN", nullable = false)
	private String vin;
	
	@Column(name = "Version", nullable = true)
	private String version;
	
	@Column(name = "TemplateType", nullable = false)
	private String temptype;
	
	@Column(name = "TemplateName", nullable = false)
	private String tempname;
	
	@Column(name = "Manufacturer", nullable = false)
	private String manufacturer;
	
	@Column(name = "Category", nullable = true)
	private String category;
	
	@Column(name = "Image", nullable = true)
	private String image;
	
	@Column(name = "Model", nullable = true)
	private String model;
	
	@Column(name = "Year", nullable = true)
	private String year;
	
	@Column(name = "TemplateID", nullable = true)
	private Long tempID;
	
	@Column(name = "UserId", nullable = false)
	private Long uid;
	
	@Column(name = "Note", nullable = true)
	private String note;
	
	@Column(name = "Status", nullable = true)
	private String status;
	
	@Column(name = "Owner", nullable = true)
	private String owner;
	
	@Column(name = "Assignee", nullable = true)
	private String assignee;
	
	@Column(name="deleted", nullable = false)
	private boolean deleted = false;
	
	@Transient
	public String inspected;
	
	@Column(name = "Updated", nullable = false)
	private Long updated;
	
	@Column(name="UpdatedDate", nullable = false)
	private String updatedDate;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "entryHeader")
	@OrderBy("id")  
	private Set<EntryDetails> entryDetails = new HashSet<EntryDetails>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getVersion() {
		return version;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTemptype() {
		return temptype;
	}

	public void setTemptype(String temptype) {
		this.temptype = temptype;
	}

	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getTempID() {
		return tempID;
	}

	public void setTempID(Long tempID) {
		this.tempID = tempID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Set<EntryDetails> getEntryDetails() {
		return entryDetails;
	}

	public void setEntryDetails(Set<EntryDetails> entryDetails) {
		this.entryDetails = entryDetails;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	

	public String getInspected() {
		return inspected;
	}

	public void setInspected(String inspected) {
		this.inspected = inspected;
	}

	
	}
