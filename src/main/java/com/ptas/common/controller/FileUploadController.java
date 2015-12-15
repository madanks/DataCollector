package com.ptas.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.FileUpload;
import com.ptas.common.service.ImportService;

@Controller
@RequestMapping("/inspector/fileUpload")
public class FileUploadController {

	@Autowired
	ImportService importService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fileUploadForm(ModelMap model) {
		model.addAttribute("message", "Upload XLS File");
		ModelAndView modelAndView = new ModelAndView("file-upload-form");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView onSubmit(
			@ModelAttribute("uploadForm") FileUpload uploadForm, Model map)
			throws Exception {
		MultipartFile file = uploadForm.getFile();
		String fileName = "";
		ModelAndView modelAndView = new ModelAndView("file-upload-success");
		if (file != null) {
			fileName = file.getOriginalFilename();
			boolean etn=	importService.importFile(uploadForm);
			
			if(etn)
			{
				modelAndView.addObject("Message", "Your Excel Contains Details but its Header doesnot exist please check it out");
			}
			else
			{
					modelAndView.addObject("Message", "");
			}
			
		}
		modelAndView.addObject("fileName", fileName);
		
		
		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleIllegalArgumentException(Exception ex) {

		ModelAndView model = new ModelAndView("file-upload-error");
		model.addObject("exception", ex.getMessage());
		return model;

	}
}