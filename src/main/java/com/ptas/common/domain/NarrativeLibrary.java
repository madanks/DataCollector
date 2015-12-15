package com.ptas.common.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "narrativeLibrary")
public class NarrativeLibrary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7571279337778639774L;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", 
		unique = true, nullable = false)
	private Long id;
	
	@Column(name = "orgnId", nullable = false)
	private Long oId;
	
	@Column(name = "Library", nullable = false)
	private String library;

	@Column(name = "value", nullable = false)
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getoId() {
		return oId;
	}

	public void setoId(Long oId) {
		this.oId = oId;
	}
	

}
