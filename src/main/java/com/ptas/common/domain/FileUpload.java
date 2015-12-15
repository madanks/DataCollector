package com.ptas.common.domain;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private MultipartFile file;

	/**
	 * @return the files
	 */
	public MultipartFile getFile() {
	
		return file;
	}

	/**
	 * @param files the files to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
