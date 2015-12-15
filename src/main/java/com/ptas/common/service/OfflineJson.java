package com.ptas.common.service;

import java.util.ArrayList;

public class OfflineJson {
	
	private String Manufacturer;
	private String TemplateType;
	private String TemplateName;
	private Long TemplateId;
	private ArrayList<Section> Json;
	
	
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public String getTemplateType() {
		return TemplateType;
	}
	public void setTemplateType(String templateType) {
		TemplateType = templateType;
	}
	public String getTemplateName() {
		return TemplateName;
	}
	public void setTemplateName(String templateName) {
		TemplateName = templateName;
	}
	public Long getTemplateId() {
		return TemplateId;
	}
	public void setTemplateId(Long templateId) {
		TemplateId = templateId;
	}
	public ArrayList<Section> getJson() {
		return Json;
	}
	public void setJson(ArrayList<Section> json) {
		Json = json;
	}
	


}
