package com.ptas.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ptas.common.service.NarrativeLibraryService;

@Controller
@RequestMapping("/inspector/library")
public class NarrativeLibraryController {
	
	@Autowired
	NarrativeLibraryService nls;
	
	
	
	@RequestMapping(value = "/value/{ln}", method = RequestMethod.GET)
	public void valueLibrary(@PathVariable(value = "ln") String libraryName,
			HttpServletRequest request, HttpServletResponse response) throws IOException {


			String td = nls.getValues(libraryName);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter printout = response.getWriter();
			printout.print(td);

			printout.flush();

	
	}
	
	@RequestMapping(value = "/LibraryValue", method = RequestMethod.GET)
	public void responseLibraryvalueformobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
	
		String JsonLibraryValue = nls.findAllValue();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		printout.print(JsonLibraryValue);
		printout.flush();
	}

}
