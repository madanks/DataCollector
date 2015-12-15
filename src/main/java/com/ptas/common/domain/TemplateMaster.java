package com.ptas.common.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
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

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="templatemaster")
public class TemplateMaster implements Serializable
{
	private static final long serialVersionUID = -2591018811076337087L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MRecordID", 
		unique = true, nullable = false)
	private Long mrecordID;
	
	@Column(name = "TemplateNumber", nullable = false)
	private Long templateNumber;

	@Column(name = "Version", nullable = false)
	private String version;
	
	@Column(name = "Org_ID", nullable = false)
	private Long org_ID;
	
	@Column(name = "Temp_Type", nullable = false)
	private String temp_type;

	@Column(name = "Temp_Name", nullable = false)
	private String temp_name;

	@Column(name = "Category", nullable = false)
	private String category;
	
	@Column(name = "Manufacturer", nullable = false)
	private String manufacturer;
	
	@Column(name = "Approval", nullable = false)
	private boolean approval;
	
	@Column(name = "Model", nullable = false)
	private String model;
	
	@Column(name = "Year", nullable = false)
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date year;
	
	@Column(name = "Status", nullable = false)
	private String status;
	
	@Column(name="deleted", nullable = false)
	private boolean deleted = false;
	
	@Column(name = "Updated", nullable = false)
	private Long updated;
	
	@Column(name="UpdatedDate", nullable = false)
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date updatedDate;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "templateMaster")
	@OrderBy("mrecordID")  
	private Set<TemplateDetail> templateDetail = new HashSet<TemplateDetail>(0);

	public Long getMrecordID() {
		return mrecordID;
	}

	public void setMrecordID(Long mrecordID) {
		this.mrecordID = mrecordID;
	}
	

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Long getTemplateNumber() {
		return templateNumber;
	}

	public void setTemplateNumber(Long templateNumber) {
		this.templateNumber = templateNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getOrg_ID() {
		return org_ID;
	}

	public void setOrg_ID(Long org_ID) {
		this.org_ID = org_ID;
	}

	public String getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}

	public String getTemp_name() {
		return temp_name;
	}

	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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



	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<TemplateDetail> getTemplateDetail() {
		return templateDetail;
	}

	public void setTemplateDetail(Set<TemplateDetail> templateDetail) {
		this.templateDetail = templateDetail;
	}
	
	
	
}