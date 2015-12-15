package com.ptas.common.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="Entry_details")
public class EntryDetails implements Serializable {
	

	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RecordID")
		private Long eRecordID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id", nullable = false)
	private EntryHeader entryHeader;

	@Column(name = "CheckItem")
	private String eCheckItem;	

	@Column(name = "TemplateDetailID", nullable = false)
	private String templateDetailID;
	
	@Column(name = "Type")
	private String eType;	

	@Column(name = "Value")
	private String eValue;	

	@Column(name = "Comment")
	private String eComment;	

	@Column(name = "Pic")
	private String ePic;
	
	@Column(name="Image1")
	private String image1;
	
	@Column(name="Image2")
	private String image2;
	
	@Column(name="Image3")
	private String image3;
	
	@Column(name="Image4")
	private String image4;
	
	@Column(name="Image5")
	private String image5;

	@Column(name = "valuetype")
	private String evaluetype;

	public Long geteRecordID() {
		return eRecordID;
	}

	public void seteRecordID(Long eRecordID) {
		this.eRecordID = eRecordID;
	}

	public String geteCheckItem() {
		return eCheckItem;
	}

	public void seteCheckItem(String eCheckItem) {
		this.eCheckItem = eCheckItem;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String geteValue() {
		return eValue;
	}

	public void seteValue(String eValue) {
		this.eValue = eValue;
	}

	public String geteComment() {
		return eComment;
	}

	public void seteComment(String eComment) {
		this.eComment = eComment;
	}

	public String getePic() {
		return ePic;
	}

	public void setePic(String ePic) {
		this.ePic = ePic;
	}

	public String getEvaluetype() {
		return evaluetype;
	}

	public void setEvaluetype(String evaluetype) {
		this.evaluetype = evaluetype;
	}

	public EntryHeader getEntryHeader() {
		return entryHeader;
	}

	public void setEntryHeader(EntryHeader entryHeader) {
		this.entryHeader = entryHeader;
	}

	public String getTemplateDetailID() {
		return templateDetailID;
	}

	public void setTemplateDetailID(String templateDetailID) {
		this.templateDetailID = templateDetailID;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}
	

}
