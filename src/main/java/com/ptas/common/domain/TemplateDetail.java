package com.ptas.common.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="templatedetail")
public class TemplateDetail implements Serializable
{
	private static final long serialVersionUID = -2591018811076337086L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DRecordID", 
		unique = true, nullable = false)
	private Long drecordID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mrecordID", nullable = false)
	private TemplateMaster templateMaster;

	@Column(name = "CheckItem", nullable = false)
	private String checkItem;
	
	@Column(name = "GroupName", nullable = true)
	private String group;
	
	@Column(name = "Section", nullable = true)
	private String section;

	@Column(name = "Type", nullable = true)
	private String type;

	@Column(name = "Library", nullable = true)
	private String library;	
	
	@Column(name = "FieldName", nullable = true)
	private String fieldName;	
	
	@Column(name = "ValueType", nullable = true)
	private String valueType;
	
	@Column(name = "ValueOption", nullable = true)
	private String valueOption;
	
	@Column(name = "AllowComment", nullable = false)
	private boolean allcomment = false;
	
	@Column(name = "AllowPic", nullable = false)
	private boolean allowPic = false;
	
	@Column(name = "AllowVideo", nullable = false)
	private boolean allowVideo = false;
	
	@Column(name = "TextInstruction", nullable = true)
	private String textInstruction;
	
	@Column(name = "PictureInstruction", nullable = true)
	private String pictureInstruction;
	
	@Column(name = "VideoInstruction", nullable = true)
	private String videoInstruction;
	
		
	@Column(name="deleted", nullable = false)
	private boolean deleted = false;
	
	@Column(name = "Updated", nullable = true)
	private Long updated;
	
	@Column(name="UpdatedDate", nullable = false)
	private Date updatedDate;

	public Long getDrecordID() {
		return drecordID;
	}

	public void setDrecordID(Long drecordID) {
		this.drecordID = drecordID;
	}

	public TemplateMaster getTemplateMaster() {
		return templateMaster;
	}

	public void setTemplateMaster(TemplateMaster templateMaster) {
		this.templateMaster = templateMaster;
	}

	public String getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getValueOption() {
		return valueOption;
	}

	public void setValueOption(String valueOption) {
		this.valueOption = valueOption;
	}

	public boolean isAllcomment() {
		return allcomment;
	}

	public void setAllcomment(boolean allcomment) {
		this.allcomment = allcomment;
	}

	public boolean isAllowPic() {
		return allowPic;
	}

	public void setAllowPic(boolean allowPic) {
		this.allowPic = allowPic;
	}

	public boolean isAllowVideo() {
		return allowVideo;
	}

	public void setAllowVideo(boolean allowVideo) {
		this.allowVideo = allowVideo;
	}

	public String getTextInstruction() {
		return textInstruction;
	}

	public void setTextInstruction(String textInstruction) {
		this.textInstruction = textInstruction;
	}

	public String getPictureInstruction() {
		return pictureInstruction;
	}

	public void setPictureInstruction(String pictureInstruction) {
		this.pictureInstruction = pictureInstruction;
	}

	public String getVideoInstruction() {
		return videoInstruction;
	}

	public void setVideoInstruction(String videoInstruction) {
		this.videoInstruction = videoInstruction;
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

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
}