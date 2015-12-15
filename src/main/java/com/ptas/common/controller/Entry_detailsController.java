package com.ptas.common.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.EntryDetails;
import com.ptas.common.service.EntryDetailService;

@Controller
@RequestMapping("/inspector/EntryDetails")
public class Entry_detailsController {
	
	@Autowired
	EntryDetailService entryDetailService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOfOrganization_get(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("create-entry");
		List<EntryDetails> organizations = entryDetailService.findAll();
		modelAndView.addObject("organizations", organizations);

		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editOrganization_get(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("create-entry-edit");
		modelAndView.addObject("entry", entryDetailService.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editOrganization_post(
			@ModelAttribute EntryDetails EntryDetails, BindingResult errors,
			HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			return null;
		}
		entryDetailService.update(EntryDetails);
		String message = "Organization successfully edited.";
		return getRedirectView(message);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrganization_get(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("create-entry-delete");
		modelAndView.addObject("entry", entryDetailService.findOne(id));
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteOrganization_post(
			@ModelAttribute EntryDetails EntryDetails, BindingResult errors,
			HttpServletRequest request) throws Exception {

		if (errors.hasErrors()) {
			return null;
		}
		entryDetailService.delete(EntryDetails);
		String message = "Entry Details successfully edited.";
		return getRedirectView(message);
	}

	@RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json" }, value = "/EntryDetailjson")
	public void InsertEntryDetailJson(@RequestBody String entryDetailJson,
			HttpServletResponse response) throws IOException, JSONException {
		JSONArray jsonArrayDetail = new JSONArray(entryDetailJson.trim());
		String date = entryDetailService.insert(jsonArrayDetail);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print(date);
		printout.flush();
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json" }, value = "/InsertHeaderDetailJson")
	public void InsertHeaderDetailJson(@RequestBody String UpdateDetailJson,
			HttpServletResponse response) throws IOException, JSONException, SerialException, SQLException {
		JSONObject jsonDetail = new JSONObject(UpdateDetailJson.trim());
		String IdDate = entryDetailService.InsertHeaderDetailJson(jsonDetail);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print(IdDate);
		printout.flush();
	}

	@RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json" }, value = "/UpdateDetailjson")
	public void UpdateEntryDetailJson(@RequestBody String UpdateDetailJson,
			HttpServletResponse response) throws IOException, JSONException {
		JSONObject jsonDetail = new JSONObject(UpdateDetailJson.trim());
		String date = entryDetailService.UpdateJson(jsonDetail);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print("{\"Date\":\""+date+"\"}");
		printout.flush();
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json" }, value = "/UpdateNewDetailjson")
	public void UpdateNewEntryDetailJson(@RequestBody String UpdateDetailJson,
			HttpServletResponse response) throws IOException, JSONException, SQLException {
		JSONObject jsonDetail = new JSONObject(UpdateDetailJson.trim());
		String date = entryDetailService.UpdateNewJson(jsonDetail);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print("{\"Date\":\""+date+"\"}");
		printout.flush();
	}	
	 @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	   public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		return entryDetailService.SaveImage(request);
	 }

	private ModelAndView getRedirectView(String message) {
		ModelAndView modelAndView = new ModelAndView("redirect:/inspector/Entry_Details/list");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
