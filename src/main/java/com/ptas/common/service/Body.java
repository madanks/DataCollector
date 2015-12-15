package com.ptas.common.service;
import java.util.ArrayList;

public class Body {
	private Long id;
	private String CheckItem;
	private String ValueType;
	private  ArrayList<String> ValueOption;
	private String TextInstruction;
	private String Library;
	private String FieldName;
	private String PictureInstruction;
	private String VideoInstruction;

	private  ArrayList<String> Controls;
	public Body(){
		super();
		this.Controls=new ArrayList<String>();
	}
	
	
	public String getLibrary() {
		return Library;
	}


	public void setLibrary(String library) {
		Library = library;
	}


	public String getFieldName() {
		return FieldName;
	}


	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}


	/**
	 * @return the checkItem
	 */
	public String getCheckItem() {
		return CheckItem;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the valueOptionArray
	 */
	public ArrayList<String> getValueOption() {
		return ValueOption;
	}


	/**
	 * @param valueOptionArray the valueOptionArray to set
	 */
	public void setValueOption(ArrayList<String> valueOptionArray) {
		ValueOption = valueOptionArray;
	}


	/**
	 * @param checkItem the checkItem to set
	 */
	public void setCheckItem(String checkItem) {
		CheckItem = checkItem;
	}


	/**
	 * @return the valueType
	 */
	public String getValueType() {
		return ValueType;
	}


	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(String valueType) {
		ValueType = valueType;
	}



	/**
	 * @return the textInstruction
	 */
	public String getTextInstruction() {
		return TextInstruction;
	}


	/**
	 * @param textInstruction the textInstruction to set
	 */
	public void setTextInstruction(String textInstruction) {
		TextInstruction = textInstruction;
	}


	/**
	 * @return the pictureInstruction
	 */
	public String getPictureInstruction() {
		return PictureInstruction;
	}


	/**
	 * @param pictureInstruction the pictureInstruction to set
	 */
	public void setPictureInstruction(String pictureInstruction) {
		PictureInstruction = pictureInstruction;
	}


	/**
	 * @return the videoInstruction
	 */
	public String getVideoInstruction() {
		return VideoInstruction;
	}


	/**
	 * @param videoInstruction the videoInstruction to set
	 */
	public void setVideoInstruction(String videoInstruction) {
		VideoInstruction = videoInstruction;
	}


	/**
	 * @return the controls
	 */
	public ArrayList<String> getControls() {
		return Controls;
	}


	public void setControls(ArrayList<String> controls) {
		this.Controls = controls;
	}
	
	

}
