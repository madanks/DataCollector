package com.ptas.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tempImage")
public class TempImage implements Serializable {

	private static final long serialVersionUID = -4777955221411728412L;

	@Id
	@Column(unique = true, nullable = false)
	private String imageName;
	
	@Column(name = "UpdatedBy", nullable = false)
	private Long updatedBy;
	
	@Column(name = "UpdatedDate", nullable = false)
	private String updatedDate;



	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}
