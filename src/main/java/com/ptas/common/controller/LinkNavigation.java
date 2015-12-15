package com.ptas.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.service.OrganizationService;
import com.ptas.common.service.TemplateService;
import com.ptas.common.service.UserService;

@Controller
public class LinkNavigation {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private TemplateService templateService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView("redirect:/inspector/inspection/home");
		return mv;
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView("redirect:/inspector/inspection/home");
		return mv;
	}
	
	@RequestMapping(value="/sec/moderation", method=RequestMethod.GET)
	public ModelAndView moderatorPage() {
		return new ModelAndView("moderation");
	}
	
	@RequestMapping(value="/admin/first", method=RequestMethod.GET)
	public ModelAndView firstAdminPage() {	
		return new ModelAndView("admin-first");
	}
	
	@RequestMapping(value="/admin/second", method=RequestMethod.GET)
	public ModelAndView secondAdminPage() {
		return new ModelAndView("admin-second");
	}	
	
}
