package com.ptas.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.ptas.common.domain.EntryHeader;
import com.ptas.common.service.EntryHeaderService;
import com.ptas.common.service.UserService;

@Controller
@RequestMapping("/inspector/entryheader")
public class EntryHeaderController {

	@Autowired
	EntryHeaderService ehs;
	
	@Autowired
	UserService userservice;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOfOrganization_get(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("list-EntryHeader-form");
		List<EntryHeader> eh = ehs.findAll("AND deleted=false");
		modelAndView.addObject("entryHeader", eh);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editOrganization_get(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("edit-EntryHeader-form");
		modelAndView.addObject("ehEdit", ehs.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editOrganization_post(@ModelAttribute EntryHeader eh,
			BindingResult errors, HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			// logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		ehs.update(eh);
		String message = "EntryHeader successfully edited.";
		return getRedirectView(message);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrganization_get(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("delete-EntryHeader-form");
		modelAndView.addObject("ehdelete", ehs.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteOrganization_post(@ModelAttribute EntryHeader eh,
			BindingResult errors, HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			// logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		eh.setDeleted(true);
		ehs.update(eh);
		String message = "EntryHeader successfully edited.";
		return getRedirectView(message);
	}

	@RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json" }, value = "/EntryHeaderjson")
	public void InsertHeaderJson(@RequestBody String entryHeaderJson,
			HttpServletResponse response) throws IOException, JSONException {
		JSONObject EH = new JSONObject(entryHeaderJson.trim());
		String date = ehs.insertEH(EH);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print(date);
		printout.flush();
	}

	@RequestMapping(value = "/jsonEntryHeader", method = RequestMethod.GET)
	public void JsonOfEntryheader_get(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String td = ehs.getRecordJson();
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(td);
			printout.flush();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/cjsonEntryHeader", method = RequestMethod.GET)
	public void JsonOfCEntryheader_get(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String td = ehs.getPendingJson();
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(td);

			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/assignUsers", method = RequestMethod.GET)
	public void AssignUsers(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String td = userservice.AssignUserJSON();
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(td);

			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/TemplateNumber/{N}", method = RequestMethod.GET)
	public void listOfTemplateMaster_get(
			@PathVariable(value = "N") String templateNum,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String endata = ehs.findOneID(Long.parseLong(templateNum));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(endata);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/TemplateNumberMob/{N}", method = RequestMethod.GET)
	public void listOfTemplateMasterMob_get(
			@PathVariable(value = "N") String templateNum,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String endata = ehs.findOneMobID(Long.parseLong(templateNum));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(endata);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/ImageName", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public void ImageName_get(@RequestBody String ImageName,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject EH = new JSONObject(ImageName.trim());
			System.out.println(EH.getString("name"));
			String endata = ehs.findImageBlobString(EH.getString("name"));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(endata);
			printout.flush();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private ModelAndView getRedirectView(String message) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/entryheader/list");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
