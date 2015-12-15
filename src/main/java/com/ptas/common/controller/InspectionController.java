package com.ptas.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.EntryHeader;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.domain.UserInfo;
import com.ptas.common.service.EntryHeaderService;
import com.ptas.common.service.MfgJson;
import com.ptas.common.service.OrganizationService;
import com.ptas.common.service.TemplateService;
import com.ptas.common.service.UserInfoService;
import com.ptas.common.service.UserService;
import com.ptas.common.util.SecurityUtil;

@Controller
@RequestMapping("/inspector/inspection")
public class InspectionController {

	@Autowired
	TemplateService templateService;

	@Autowired
	EntryHeaderService entryHeaderservice;

	@Autowired
	UserInfoService userinfo;

	@Autowired
	UserService userservice;

	@Autowired
	OrganizationService org;

	@Autowired
	private HttpServletRequest servletRequest;

	@RequestMapping(value = "/inspect/{m}/{t}/{tn}/{tnum}", method = RequestMethod.GET)
	public ModelAndView BackEndMainJsonTemplateDetailJson_get(@PathVariable(value = "m") String manufacturer,
			@PathVariable(value = "t") String templateType, @PathVariable(value = "tn") String templatename,
			@PathVariable(value = "tnum") String tempnum, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		ModelAndView modelAndView = new ModelAndView("template-inspection");
		String JsonMain = templateService.findTemplateMainJson(manufacturer, templateType, templatename);
		TemplateMaster approv = templateService.findTemplate(manufacturer, templateType, templatename);
		String endata = entryHeaderservice.findOneID(Long.parseLong(tempnum));
		System.out.println(endata);
		modelAndView.addObject("templateJson", JsonMain);
		modelAndView.addObject("data", endata);
		String s = null;
		if (!approv.isApproval()) {
			// modelAndView.addObject("approval","Completed" );
			s = "COMPLETED";
		} else {
			// modelAndView.addObject("approval","Pending" );
			s = "Pending";
		}
		modelAndView.addObject("approval", "{\"status\":\"" + s + "\"}");
		return modelAndView;
	}

	@RequestMapping(value = "/pinspect/{m}/{t}/{tn}/{tnum}", method = RequestMethod.GET)
	public ModelAndView BackEndMainJsonpTemplateDetailJson_get(@PathVariable(value = "m") String manufacturer,
			@PathVariable(value = "t") String templateType, @PathVariable(value = "tn") String templatename,
			@PathVariable(value = "tnum") String tempnum, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		ModelAndView modelAndView = new ModelAndView("pending-inspection");
		String JsonMain = templateService.findTemplateMainJson(manufacturer, templateType, templatename);
		String endata = entryHeaderservice.findOneID(Long.parseLong(tempnum));
		modelAndView.addObject("templateJson", JsonMain);
		modelAndView.addObject("data", endata);
		return modelAndView;
	}

	@RequestMapping(value = "/cinspect/{m}/{t}/{tn}/{tnum}", method = RequestMethod.GET)
	public ModelAndView BackEndcMainJsonpTemplateDetailJson_get(@PathVariable(value = "m") String manufacturer,
			@PathVariable(value = "t") String templateType, @PathVariable(value = "tn") String templatename,
			@PathVariable(value = "tnum") String tempnum, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		ModelAndView modelAndView = new ModelAndView("completed-inspection");
		String JsonMain = templateService.findTemplateMainJson(manufacturer, templateType, templatename);
		String endata = entryHeaderservice.findOneID(Long.parseLong(tempnum));
		modelAndView.addObject("templateJson", JsonMain);
		modelAndView.addObject("data", endata);
		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOfentryheader_get(HttpServletRequest request) {
		boolean flag = false;
		List<String> rol = SecurityUtil.getRoles();
		for (int i = 0; i < rol.size(); i++) {
			if (rol.get(i).equalsIgnoreCase("ADMIN") || rol.get(i).equalsIgnoreCase("SUPERVISOR")) {
				flag = true;
			}
		}
		if (flag) {
			List<EntryHeader> eh = entryHeaderservice
					.findAll("AND deleted=false AND status='DRAFT' AND orgId=" + SecurityUtil.getUserOrganisation());
			for (EntryHeader ee : eh) {
				UserInfo u = userinfo.findOne(ee.getUid());
				ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
			}
			ModelAndView modelAndView = new ModelAndView("list-inspection");
			String message = request.getParameter("message");
			String messageType = request.getParameter("messageType");
			modelAndView.addObject("draft", eh);
			modelAndView.addObject("message", message);
			modelAndView.addObject("messageType", messageType);
			return modelAndView;
		}
		List<EntryHeader> eh = entryHeaderservice.findAll("AND deleted=false AND status='DRAFT' AND uid="
				+ SecurityUtil.getUserId() + " AND orgId=" + SecurityUtil.getUserOrganisation());
		for (EntryHeader ee : eh) {
			UserInfo u = userinfo.findOne(ee.getUid());
			ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
		}
		ModelAndView modelAndView = new ModelAndView("list-inspection");
		String message = request.getParameter("message");
		String messageType = request.getParameter("messageType");
		modelAndView.addObject("draft", eh);
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homeheader_get(HttpServletRequest request) {
		boolean flag = false;
		List<String> rol = SecurityUtil.getRoles();
		for (int i = 0; i < rol.size(); i++) {
			if (rol.get(i).equalsIgnoreCase("ADMIN") || rol.get(i).equalsIgnoreCase("SUPERVISOR")) {
				flag = true;
			}
		}
		if (flag) {
			List<EntryHeader> eh = entryHeaderservice
					.findAll("AND deleted=false AND status='DRAFT' AND orgId=" + SecurityUtil.getUserOrganisation());
			for (EntryHeader ee : eh) {
				UserInfo u = userinfo.findOne(ee.getUid());
				ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
			}
			ModelAndView modelAndView = new ModelAndView("home-list");
			String message = request.getParameter("message");
			String messageType = request.getParameter("messageType");
			modelAndView.addObject("draft", eh);
			modelAndView.addObject("message", message);
			modelAndView.addObject("messageType", messageType);
			return modelAndView;
		}
		List<EntryHeader> eh = entryHeaderservice.findAll("AND deleted=false AND status='DRAFT' AND uid="
				+ SecurityUtil.getUserId() + " AND orgId=" + SecurityUtil.getUserOrganisation());
		for (EntryHeader ee : eh) {
			UserInfo u = userinfo.findOne(ee.getUid());
			ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
		}
		ModelAndView modelAndView = new ModelAndView("home-list");
		String message = request.getParameter("message");
		String messageType = request.getParameter("messageType");
		modelAndView.addObject("draft", eh);
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}

	@RequestMapping(value = "/listp", method = RequestMethod.GET)
	public ModelAndView listOffentryheader_get(HttpServletRequest request) {
		boolean flag = false;
		List<String> rol = SecurityUtil.getRoles();
		for (int i = 0; i < rol.size(); i++) {
			if (rol.get(i).equalsIgnoreCase("ADMIN") || rol.get(i).equalsIgnoreCase("SUPERVISOR")) {
				flag = true;
			}
		}
		if (flag) {
			List<EntryHeader> eh = entryHeaderservice
					.findAll("AND deleted=false AND status='PENDING' AND orgId=" + SecurityUtil.getUserOrganisation());
			for (EntryHeader ee : eh) {
				UserInfo u = userinfo.findOne(ee.getUid());
				ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
			}
			ModelAndView modelAndView = new ModelAndView("listp-inspection");
			String message = request.getParameter("message");
			String messageType = request.getParameter("messageType");
			modelAndView.addObject("draft", eh);
			modelAndView.addObject("message", message);
			modelAndView.addObject("messageType", messageType);
			return modelAndView;
		}
		List<EntryHeader> eh = entryHeaderservice.findAll("AND deleted=false AND status='PENDING' AND uid="
				+ SecurityUtil.getUserId() + " AND orgId=" + SecurityUtil.getUserOrganisation());
		for (EntryHeader ee : eh) {
			UserInfo u = userinfo.findOne(ee.getUid());
			ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
		}
		ModelAndView modelAndView = new ModelAndView("listp-inspection");
		String message = request.getParameter("message");
		String messageType = request.getParameter("messageType");
		modelAndView.addObject("draft", eh);
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}

	@RequestMapping(value = "/listc", method = RequestMethod.GET)
	public ModelAndView listOffentryheader_getinspection(HttpServletRequest request) {
		boolean flag = false;
		List<String> rol = SecurityUtil.getRoles();
		for (int i = 0; i < rol.size(); i++) {
			if (rol.get(i).equalsIgnoreCase("ADMIN") || rol.get(i).equalsIgnoreCase("SUPERVISOR")) {
				flag = true;
			}
		}
		if (flag) {
			List<EntryHeader> eh = entryHeaderservice.findAll(
					"AND deleted=false AND status='COMPLETED' AND orgId=" + SecurityUtil.getUserOrganisation());
			for (EntryHeader ee : eh) {
				UserInfo u = userinfo.findOne(ee.getUid());
				ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
			}
			ModelAndView modelAndView = new ModelAndView("listc-inspection");
			String message = request.getParameter("message");
			String messageType = request.getParameter("messageType");
			modelAndView.addObject("draft", eh);
			modelAndView.addObject("message", message);
			modelAndView.addObject("messageType", messageType);
			return modelAndView;
		}
		List<EntryHeader> eh = entryHeaderservice.findAll("AND deleted=false AND status='COMPLETED' AND uid="
				+ SecurityUtil.getUserId() + " AND orgId=" + SecurityUtil.getUserOrganisation());
		for (EntryHeader ee : eh) {
			UserInfo u = userinfo.findOne(ee.getUid());
			ee.setInspected(u.getFirstName() + ' ' + u.getLastName());
		}
		ModelAndView modelAndView = new ModelAndView("listc-inspection");
		String message = request.getParameter("message");
		String messageType = request.getParameter("messageType");
		modelAndView.addObject("draft", eh);
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addentryheader_get(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("add-inspection");
		MfgJson td = templateService.findDistinct();
		List<String> tm = td.getManufacturer();// templateService.findAll("AND
												// deleted=false AND
												// status='PUBLISHED'");
		List<String> userorg = userservice.findUsers();
		modelAndView.addObject("MFG", tm);
		modelAndView.addObject("users", userorg);

		modelAndView.addObject("entryheader", new EntryHeader());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addentryheader_post(@ModelAttribute EntryHeader entryHeader, BindingResult errors,
			HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			// logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String gmtTime = sdf.format(new Date());
		entryHeader.setUpdatedDate(gmtTime);
		entryHeader.setUpdated(SecurityUtil.getUserId());
		entryHeader.setUid(SecurityUtil.getUserId());
		entryHeader.setStatus("DRAFT");
		if (entryHeader.getImage() == "") {
			entryHeader.setImage(null);
		}
		entryHeader.setOrgId(SecurityUtil.getUserOrganisation());
		entryHeaderservice.create(entryHeader);

		ModelAndView modelAndView = new ModelAndView("redirect:inspect/" + entryHeader.getManufacturer() + "/"
				+ entryHeader.getTemptype() + "/" + entryHeader.getTempname() + "/" + entryHeader.getId());
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editentryheader_get(@PathVariable Long id) throws Exception {
		ModelAndView modelAndView = new ModelAndView("edit-inspection");
		List<String> userorg = userservice.findUsers();
		modelAndView.addObject("users", userorg);
		modelAndView.addObject("entryHeader", entryHeaderservice.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editentryheader_post(@ModelAttribute EntryHeader entryheader, BindingResult errors,
			HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String gmtTime = sdf.format(new Date());
		entryheader.setUpdatedDate(gmtTime);
		entryheader.setUpdated(SecurityUtil.getUserId());
		entryheader.setUid(SecurityUtil.getUserId());
		if (entryheader.getImage() == "") {
			entryheader.setImage(null);
		}
		entryHeaderservice.update(entryheader);
		String message = "Draft edited successfully.";
		String messagetype = "success";
		return getRedirectView(message, messagetype);
	}

	@RequestMapping(value = "/editp/{id}", method = RequestMethod.GET)
	public ModelAndView editinspection_get(@PathVariable Long id) throws Exception {
		ModelAndView modelAndView = new ModelAndView("edit-inspectionp");
		List<String> status = new ArrayList<String>();
		status.add("PUBLISHED");
		status.add("DRAFT");
		modelAndView.addObject("status", status);
		List<String> userorg = userservice.findUsers();
		modelAndView.addObject("users", userorg);
		modelAndView.addObject("entryHeader", entryHeaderservice.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/editp", method = RequestMethod.POST)
	public ModelAndView editinspection_post(@ModelAttribute EntryHeader entryheader, BindingResult errors,
			HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			// logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String gmtTime = sdf.format(new Date());
		entryheader.setUpdatedDate(gmtTime);
		entryheader.setUpdated(SecurityUtil.getUserId());
		entryheader.setUid(SecurityUtil.getUserId());
		entryHeaderservice.update(entryheader);
		String message = "Draft edited successfully.";
		String messagetype = "success";
		return getRedirectView1(message, messagetype);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteentryheader_get(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("delete-inspection");
		modelAndView.addObject("entryHeader", entryHeaderservice.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteentryheader_post(@ModelAttribute EntryHeader entryheader, BindingResult errors,
			HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			// logger.error("Error: ", errors.getAllErrors());
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String gmtTime = sdf.format(new Date());
		entryheader.setUpdatedDate(gmtTime);
		entryheader.setUpdated(SecurityUtil.getUserId());
		entryheader.setUid(SecurityUtil.getUserId());
		entryheader.setDeleted(true);
		entryHeaderservice.update(entryheader);
		String message = "Draft deleted successfully.";
		String messagetype = "warning";
		return getRedirectView(message, messagetype);
	}

	@RequestMapping(value = "/report/{did}", method = RequestMethod.GET)
	public ModelAndView report(@PathVariable(value = "did") Long did, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = did + "" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		String filePath = servletRequest.getRealPath("/") + "/resources/Reports/" + fileName;
		templateService.findReportContent(did, filePath);
		String a = "redirect:/resources/Reports/" + fileName;
		return new ModelAndView(a);

	}

	private ModelAndView getRedirectView(String message, String messageType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/inspection/list");
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}

	private ModelAndView getRedirectView1(String message, String messageType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/inspection/listp");
		modelAndView.addObject("message", message);
		modelAndView.addObject("messageType", messageType);
		return modelAndView;
	}

}
