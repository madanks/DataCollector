package com.ptas.common.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.service.TemplateDetailService;

@RestController
@RequestMapping("/admin/ShowJson")
public class JSONController {

	@Autowired
	TemplateDetailService templatedetailService;

	@RequestMapping(value="/js/{hello}", method=RequestMethod.GET)
	public void listOfTemplatedetail_get(@PathVariable String hello, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Got request param: " + hello);  
		
		ModelAndView modelAndView = new ModelAndView("list-user-json");	
		
		try {
			
			modelAndView.addObject("objlisttempdet1", 1);
			 response.setContentType("application/json; charset=UTF-8");
		        PrintWriter printout = response.getWriter();	      
		        printout.print(1);
		        printout.flush();
		
			} catch (Exception e) {
			
			e.printStackTrace();
		}	
		//return modelAndView;
	}
	
}
