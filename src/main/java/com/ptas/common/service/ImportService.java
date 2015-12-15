package com.ptas.common.service;

import com.ptas.common.domain.FileUpload;

public interface ImportService {
	public boolean importFile(FileUpload file) throws Exception;
}
