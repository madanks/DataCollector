package com.ptas.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.User;
import com.ptas.common.service.EntryDetailService;
import com.ptas.common.service.EntryHeaderService;
import com.ptas.common.service.TemplateDetailService;
import com.ptas.common.service.TemplateService;
import com.ptas.common.service.UserService;
import com.ptas.common.util.SecurityUtil;

@Controller
public class SecurityNavigation {
	@Autowired
	TemplateDetailService templatedetailService;

	@Autowired
	TemplateService templateService;
	
	@Autowired
	UserService userService;

	@Autowired
	EntryDetailService entrydetail;

	@Autowired
	EntryHeaderService entryheaderservice;

	@RequestMapping(value = "/user-login", method = RequestMethod.GET)
	public ModelAndView loginForm() {

		return new ModelAndView("login-form");
	}
	
	@RequestMapping(value = "/user-signup", method = RequestMethod.GET)
	public ModelAndView SignupForm() {

		return new ModelAndView("signup-form");
	}
	
	@RequestMapping(value = "/user-signup", method = RequestMethod.POST)
	public void SignupFormPost(@RequestBody String signup,
		HttpServletResponse response) throws IOException, JSONException, ParseException, InvalidFormatException {
		
	JSONObject signupData = new JSONObject(signup.trim());
	userService.UserSignup(signupData);
	//String date = entryDetailService.insert(jsonArrayDetail);
	response.setContentType("application/json; charset=UTF-8");
	PrintWriter printout = response.getWriter();
	printout.print("{\"Date\":\"success\"}");
	printout.flush();
	//return new ModelAndView("login-form");
	}
	
	@RequestMapping(value = "/user-signup/CheckUname/{username}", method = RequestMethod.GET)
	public void deleteOrganization_get(@PathVariable String username, HttpServletResponse response) throws IOException {
		System.out.println("AND username='"+username+"'");
		List<User> u= userService.findAll("AND username='"+username+"'");
		String s=null;
		if(u.size()==0)
		{
			s="unique";
		}
		else
		{
			s="dublicate";
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print("{\"Status\":\""+s+"\"}");
		printout.flush();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/user-login");
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public ModelAndView invalidLogin() {
		ModelAndView modelAndView = new ModelAndView("login-form");
		modelAndView.addObject("error", true);
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/jsonEntryDetail", method = RequestMethod.GET)
	public void JsonOfEntrydetail_get(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String td = entrydetail.getRecordJson();
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(td);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/success-Mob", method = RequestMethod.GET)
	public void successMobileLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		User user = SecurityUtil.getAuthenticatedUser();
		//return new ModelAndView("success-login");
		JSONObject LoginStatus = new JSONObject();
		if (user != null) {
			LoginStatus.put("Status","success");
			LoginStatus.put("UserName",SecurityUtil.getFullName());
			LoginStatus.put("UserId",SecurityUtil.getUserId());
			LoginStatus.put("UserInfo",SecurityUtil.getUserInfo());
			
			 response.setContentType("application/json; charset=UTF-8");
		        PrintWriter printout = response.getWriter();	      
		        printout.print(LoginStatus);
		        printout.flush();
		 
			
			//return new ModelAndView("home");
		}
		else
		{
			
				LoginStatus.put("Status","Failed");
				response.setContentType("application/json; charset=UTF-8");
		        PrintWriter printout = response.getWriter();	      
		        printout.print(LoginStatus);
		        printout.flush();
		}
		//return new ModelAndView("login-form");
	}

	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public ModelAndView successLogin() {
		User user = SecurityUtil.getAuthenticatedUser();
		//return new ModelAndView("success-login");
		if (user != null) {
			ModelAndView mv = new ModelAndView("redirect:/inspector/inspection/home");
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("login-form");
			return mv;
		}
		
		
	}

}