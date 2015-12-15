package com.ptas.common.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.service.MfgJson;
import com.ptas.common.service.TemplateService;
import com.ptas.common.util.SecurityUtil;

@Controller
@RequestMapping("/inspector/template/")
public class TemplateController {
	
	@Autowired
	TemplateService templateService;

	@RequestMapping(value="/templateheader", method=RequestMethod.GET)
	public ModelAndView listOfTemplate_get(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("list-template-form");	
		List<TemplateMaster> templatemaster = templateService.findAll("AND deleted=false AND status='DRAFT' AND org_ID="+SecurityUtil.getUserOrganisation());
		modelAndView.addObject("objListTemplateHeaders", templatemaster);
		
		return modelAndView;
	}
	@RequestMapping(value="/templateheaderc", method=RequestMethod.GET)
	public ModelAndView listOfTemplatec_get(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("list-templatec-form");	
		List<TemplateMaster> templatemaster = templateService.findAll("AND deleted=false AND status='PUBLISHED'  AND org_ID="+SecurityUtil.getUserOrganisation());
		modelAndView.addObject("objListTemplateHeaders", templatemaster);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edittemplateheader_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("edit-template-form");
		List<String> status= new ArrayList();
		status.add("PUBLISHED");
		status.add("DRAFT");
		modelAndView.addObject("templatemaster", templateService.getRecordID(id));		
		modelAndView.addObject("status",status);		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public ModelAndView edittemplateheader_post(@ModelAttribute TemplateMaster templatemaster,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			System.out.println( errors.getAllErrors());
			return null;
		}
		String Status=templatemaster.getStatus();
		templatemaster.setStatus(Status.toUpperCase());
		templateService.update(templatemaster);
		String message = "Template successfully edited.";		
		return getRedirectView(message);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deletetemplateheader_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("delete-template-form");
		modelAndView.addObject("templatemaster", templateService.findOne(id));
		return modelAndView;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deleteOrganization_post(@ModelAttribute TemplateMaster templatemaster,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		templatemaster.setDeleted(true);
		templateService.update(templatemaster);
		String message = "Template successfully edited.";		
		return getRedirectView(message);
	}

	@RequestMapping(value = "/manufacturer", method = RequestMethod.GET)
	public void JsonOfCreateInspection(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			
			MfgJson td = templateService.findDistinct();
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String j = ow.writeValueAsString(td);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(j);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	

	@RequestMapping(value = "/TemplateName/{m}/{t}", method = RequestMethod.GET)
	public void listOfTemplateMaster_get(
			@PathVariable(value = "m") String manufacturer,
			@PathVariable(value = "t") String templateType,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String templateNames = templateService.findTemplateName(
					manufacturer, templateType);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(templateNames);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/TempName/{tn}", method = RequestMethod.GET)
	public void FindTempIDTemplateMaster_get(
			@PathVariable(value = "tn") String tenpName,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			TemplateMaster tm= templateService.findOne("temp_name", tenpName);
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String j = ow.writeValueAsString(tm.getMrecordID());
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(j);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/MainJson/{m}/{t}/{tn}", method = RequestMethod.GET)
	public void MainJsonTemplateDetailJson_get(
			@PathVariable(value = "m") String manufacturer,
			@PathVariable(value = "t") String templateType,
			@PathVariable(value = "tn") String templatename,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String JsonMain = templateService.findTemplateMainJson(manufacturer,
					templateType, templatename);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(JsonMain);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/offlineJson", method = RequestMethod.POST)
	public void offlineJson(HttpServletRequest request, HttpServletResponse response) {

		try {
			String JsonMain = templateService.findOfflineJson();
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(JsonMain);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	private ModelAndView getRedirectView(String message) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/template/templateheader");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
