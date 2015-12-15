package com.ptas.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.EntryDetails;
import com.ptas.common.domain.EntryHeader;
import com.ptas.common.service.EntryHeaderService;
import com.ptas.common.service.TemplateService;
import com.ptas.common.service.UserInfoService;
import com.ptas.common.service.UserService;
import com.ptas.common.util.SecurityUtil;

@Controller
public class ApproveInspection {
	
	
	@Autowired
	TemplateService templateService;
	
	@Autowired
	EntryHeaderService entryHeaderservice;
	
	@Autowired
	UserInfoService userinfo;
	
	@Autowired
	UserService userservice;
	
	
	@RequestMapping(value="/supervisor/inspection/approve/{id}", method=RequestMethod.GET)
	public ModelAndView editentryheader_get(@PathVariable Long id){		
		ModelAndView modelAndView = new ModelAndView("approve-inspection");
		modelAndView.addObject("entryHeader", entryHeaderservice.findOne(id));		
		return modelAndView;
	}
	
	@RequestMapping(value="/supervisor/inspection/approve", method=RequestMethod.POST)
	public ModelAndView editentryheader_post(@ModelAttribute EntryHeader entryheader,
			BindingResult errors, HttpServletRequest request)
			throws Exception {
		if (errors.hasErrors()) {
			//logger.error("Error: ", errors.getAllErrors());
			return null;
		}
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String gmtTime = sdf.format(new Date());
	    entryheader.setUpdatedDate(gmtTime);
	    entryheader.setUpdated(SecurityUtil.getUserId());
	    entryheader.setUid(SecurityUtil.getUserId());
	    entryheader.setStatus("COMPLETED");
		entryHeaderservice.update(entryheader);
		String message = "Draft edited successfully.";		
		String messagetype = "success";	
		return getRedirectView(message,messagetype);
	}
	private ModelAndView getRedirectView(String message, String messageType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/inspection/listp");
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}
	@RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json" }, value = "/inspector/user/updateUserPassword")
	public void UpdateAppUserPassword(@RequestBody String changedPasswordjson,
			HttpServletResponse response) throws IOException, JSONException {
		JSONObject userService1 = new JSONObject(changedPasswordjson.trim());
		String m = userservice.ChangePassword(userService1);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print(m);
		printout.flush();
	}
	
	@RequestMapping(value = "/inspector/user/profile", method = RequestMethod.GET)
	public ModelAndView listOfOrganization_get(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("profile");

		return modelAndView;
	}
	@RequestMapping(value="/inspector/user/checkpass/{pass}", method=RequestMethod.GET)
	public void editOrganization_get(@PathVariable String pass, HttpServletResponse response) throws IOException{		
		
		String stat= userservice.checkpass(pass.trim());
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print(stat);
		printout.flush();
	}
}
