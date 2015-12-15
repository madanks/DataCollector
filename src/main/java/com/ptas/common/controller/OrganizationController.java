package com.ptas.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.Organization;
import com.ptas.common.domain.User;
import com.ptas.common.service.OrganizationService;
import com.ptas.common.service.UserService;
import com.ptas.common.util.SecurityUtil;

@Controller
@RequestMapping("/admin/organization")
public class OrganizationController {
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	UserService userService;
	

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listOfOrganization_get(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("list-organization-form");	
		String message=request.getParameter("message");
		String messageType=request.getParameter("messageType");
		System.out.println(message);
		System.out.println(messageType);
		List<Organization> organizations = organizationService.findAll("AND deleted=false AND id="+SecurityUtil.getUserOrganisation());
		modelAndView.addObject("organizations", organizations);
		modelAndView.addObject("message",message);
		modelAndView.addObject("messageType",messageType);
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addOrganization_get(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("create-organization-form");
		modelAndView.addObject("organization", new Organization());		
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addOrganization_post(@ModelAttribute Organization organization,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		
		organizationService.create(organization);
		String message = "Organization successfully added.";		
		String messagetype = "success";	
		return getRedirectView(message,messagetype);
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editOrganization_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("edit-organization-form");
		modelAndView.addObject("organization", organizationService.findOne(id));		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public ModelAndView editOrganization_post(@ModelAttribute Organization organization,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		organizationService.update(organization);
		String message = "Organization edited successfully.";		
		String messagetype = "success";	
		return getRedirectView(message,messagetype);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOrganization_get(@PathVariable Long id){	
		List<User> userList= userService.findAll("AND organization="+id+"AND enabled="+true);
		System.out.println(userList.size());
		if(userList.size()!=0)
		{
			String message = "Organization linked with Users";		
			String messagetype = "warning";	
			return getRedirectView(message,messagetype);
		}
		else
		{
			ModelAndView modelAndView = new ModelAndView("delete-organization-form");
			modelAndView.addObject("organization", organizationService.findOne(id));
			return modelAndView;
		}
		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deleteOrganization_post(@ModelAttribute Organization organization,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			
			//logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		organization.setDeleted(true);
		organizationService.update(organization);
		String message = "Organization successfully Deleted.";
		String messagetype = "success";	
		return getRedirectView(message,messagetype);
	}
	
	private ModelAndView getRedirectView(String message, String messageType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/organization/list");
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}
}
