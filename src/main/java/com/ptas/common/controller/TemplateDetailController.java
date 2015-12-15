package com.ptas.common.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.TemplateDetail;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.service.TemplateDetailService;
import com.ptas.common.service.TemplateService;
import com.ptas.common.util.SecurityUtil;

@Controller
@RequestMapping("/inspector/templatedetail")
public class TemplateDetailController {
	
	@Autowired
	TemplateDetailService templatedetailService;
	
	@Autowired
	TemplateService templateService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listOfTemplatedetail_get1(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("list-templatedetail-form");	
		List<TemplateDetail> templatedetail = templatedetailService.findAll("AND deleted=false");
		modelAndView.addObject("objlisttempdet", templatedetail);

		return modelAndView;
	}
	
	@RequestMapping(value="/details/{id}", method=RequestMethod.GET)
	public ModelAndView listtemplateDetail_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("list-templatedetail-form");
		TemplateMaster tm= templateService.getRecordID(id);
		modelAndView.addObject("objlisttempdet",tm.getTemplateDetail());			
		return modelAndView;
	}
	@RequestMapping(value="/detailsc/{id}", method=RequestMethod.GET)
	public ModelAndView listtemplateDetaial_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("list-templatedetailp-form");
		TemplateMaster tm= templateService.getRecordID(id);
		modelAndView.addObject("objlisttempdet",tm.getTemplateDetail());			
		return modelAndView;
	}
	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public ModelAndView addtemplatedetail_get(HttpServletRequest request){
//		ModelAndView modelAndView = new ModelAndView("create-templatedetail-form");
//		modelAndView.addObject("addtempdet", new TemplateDetail());		
//		return modelAndView;
//	}
//
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public ModelAndView addtemplateheaderdetail_post(@ModelAttribute TemplateDetail templatedetail,
//			BindingResult errors, HttpServletRequest request)
//			throws Exception {
//		if (errors.hasErrors()) {		
//			return null;
//		}
//		templatedetailService.create(templatedetail);
//		String message = "Template Detail successfully added.";		
//		return getRedirectView(message);
//	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edittemplatedetail_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("edit-templatedetail-form");
		modelAndView.addObject("tm",templateService.findAll("AND deleted=false AND status='Draft'"));
		List<String> yn = new ArrayList<String>();
		yn.add("True");
		yn.add("False");
		modelAndView.addObject("objedittempdet", templatedetailService.getRecordID(id));
		modelAndView.addObject("YN", yn);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public ModelAndView edittemplateheader_post(@ModelAttribute TemplateDetail templatedetail,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		TemplateMaster tmObj=templatedetail.getTemplateMaster();
		
		templatedetailService.updateDetail(templatedetail);
		String message = "Template Detail successfully edited.";		
		return getRedirectViewl(message,tmObj.getTemplateNumber());
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deletetemplatedetail_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("delete-templatedetail-form");
		modelAndView.addObject("deltempdet", templatedetailService.findOne(id));
		return modelAndView;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deleteTemplatedetail_post(@ModelAttribute TemplateDetail templatedetail,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			System.out.println("---------------------"+errors.getAllErrors()+"---------------------");
			return null;
		}
		TemplateMaster tmObj=templatedetail.getTemplateMaster();
		Long headerid=tmObj.getMrecordID();
		//System.out.println(idddddd);
		templatedetail.setUpdated(SecurityUtil.getUserId());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = dateFormat.parse(dateFormat.format(cal.getTime()));
		templatedetail.setUpdatedDate(date);
//		templatedetail.setDeleted(true);
//		
//		System.out.println("---------------------------------------------------------------------");
		templatedetailService.delete(templatedetail);
		String message = "Template Detail successfully edited.";		
		return getRedirectViewl(message,headerid);
	}
	
//	private ModelAndView getRedirectView(String message) {
//		ModelAndView modelAndView = new ModelAndView("redirect:/user/template/list");
//		modelAndView.addObject("message", message);
//		return modelAndView;
//	}
	private ModelAndView getRedirectViewl(String message,Long tn) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/templatedetail/details/"+tn);
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}

