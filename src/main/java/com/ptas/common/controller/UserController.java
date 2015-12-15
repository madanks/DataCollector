package com.ptas.common.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ptas.common.domain.User;
import com.ptas.common.domain.UserInfo;
import com.ptas.common.domain.UserRole;
import com.ptas.common.service.OrganizationService;
import com.ptas.common.service.UserInfoService;
import com.ptas.common.service.UserRoleService;
import com.ptas.common.service.UserService;
import com.ptas.common.util.SecurityUtil;

@Controller
@RequestMapping("/admin/user/")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userroleservice;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	OrganizationService organizationService;

	// this list is used to list the user
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOfUser_get(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("list-user-form");
		List<User> users = userService.findAll("AND organization="
				+ SecurityUtil.getUserOrganisation());
		for (User u : users) {
			UserInfo ui = userInfoService.findOne(u.getCreatedBy());
			String fn = ui.getFirstName() + " " + ui.getLastName();
			u.setCreater(fn);
		}
		modelAndView.addObject("users", users);
		return modelAndView;
	}

	// add user
	@RequestMapping(value = "/addsuper", method = RequestMethod.GET)
	public ModelAndView addUserinfo_get(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("create-user1-form");
		modelAndView.addObject("organizations",
				organizationService.findAll("AND deleted=false"));
		UserRole admin = new UserRole();
		UserRole user = new UserRole();
		UserRole mod = new UserRole();
		admin.setRole("ADMIN");
		mod.setRole("SUPERVISOR");
		user.setRole("INSPECTOR");

		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(admin);
		roles.add(mod);
		roles.add(user);
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(value = "/addsuper", method = RequestMethod.POST)
	public ModelAndView addUser_post(@Validated @ModelAttribute User user1,
			BindingResult errors, HttpServletRequest request) throws Exception {

		if (errors.hasErrors()) {
			return null;
		}
		User u = userService.findOne("username", user1.getUsername());
		if (u != null) {
			ModelAndView modelAndView = new ModelAndView("create-user1-form");
			modelAndView.addObject("organizations",
					organizationService.findAll("AND deleted=false"));
			UserRole admin = new UserRole();
			UserRole user = new UserRole();
			UserRole mod = new UserRole();
			admin.setRole("ADMIN");
			mod.setRole("SUPERVISOR");
			user.setRole("INSPECTOR");

			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(admin);
			roles.add(mod);
			roles.add(user);
			modelAndView.addObject("roles", roles);
			modelAndView.addObject("user", user1);
			modelAndView.addObject("cv", "Username already exist");
			return modelAndView;
		}
		userService.create1(user1);
		String message = "user information successfully added.";
		return getRedirectView(message);

	}
	// add user
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public ModelAndView addUserbysuperinfo_get(HttpServletRequest request) {
			ModelAndView modelAndView = new ModelAndView("create-user-form");
			modelAndView.addObject("organizations",
					organizationService.findAll("AND deleted=false"));
			UserRole admin = new UserRole();
			UserRole user = new UserRole();
			UserRole mod = new UserRole();
			admin.setRole("ADMIN");
			mod.setRole("SUPERVISOR");
			user.setRole("INSPECTOR");

			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(admin);
			roles.add(mod);
			roles.add(user);
			modelAndView.addObject("roles", roles);
			modelAndView.addObject("user", new User());
			return modelAndView;
		}

		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView addbuperadminysuser_post(@Validated @ModelAttribute User user1,
				BindingResult errors, HttpServletRequest request) throws Exception {

			if (errors.hasErrors()) {
				return null;
			}
			User u = userService.findOne("username", user1.getUsername());
			if (u != null) {
				ModelAndView modelAndView = new ModelAndView("create-user-form");
				modelAndView.addObject("organizations",
						organizationService.findAll("AND deleted=false"));
				UserRole admin = new UserRole();
				UserRole user = new UserRole();
				UserRole mod = new UserRole();
				admin.setRole("ADMIN");
				mod.setRole("SUPERVISOR");
				user.setRole("INSPECTOR");

				Set<UserRole> roles = new HashSet<UserRole>();
				roles.add(admin);
				roles.add(mod);
				roles.add(user);
				modelAndView.addObject("roles", roles);
				modelAndView.addObject("user", user1);
				modelAndView.addObject("cv", "Username already exist");
				return modelAndView;
			}
			userService.create(user1);
			String message = "user information successfully added.";
			return getRedirectView(message);

		}

	@RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
	public ModelAndView editUser_get(@PathVariable String username)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("edit-user-form");

		User u = userService.findOne("username", username);
		UserRole admin = new UserRole();
		UserRole user = new UserRole();
		UserRole mod = new UserRole();
		admin.setRole("ADMIN");
		mod.setRole("SUPERVISOR");
		user.setRole("INSPECTOR");
		Set<UserRole> roles = new HashSet<UserRole>();

		roles.add(admin);
		roles.add(mod);
		roles.add(user);
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("organizations",
				organizationService.findAll("AND deleted=false"));
		modelAndView.addObject("user", u);

		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editUser_post(@Validated @ModelAttribute User user,
			BindingResult errors, HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			System.out.println(errors.getAllErrors());
			return null;
		}

		userService.update(user);
		String message = "User successfully edited.";
		return getRedirectView(message);
	}

	@RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
	public ModelAndView deleteUser_get(@PathVariable String username) {
		ModelAndView modelAndView = new ModelAndView("delete-user-form");
		try {
			modelAndView.addObject("organizations",
					organizationService.findAll("AND deleted=false"));
			User u = userService.findOne("username", username);
			modelAndView.addObject("user", u);
		} catch (Exception e) {
		}
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView dactivateUser_post(@ModelAttribute User user,
			BindingResult errors, HttpServletRequest request) throws Exception {
		if (errors.hasErrors()) {
			// logger.error("Error: ", errors.getAllErrors());
			return null;
		}
		userService.deactivate(user);
		String message = "User successfully deactivated";
		return getRedirectView(message);
	}

	

	private ModelAndView getRedirectView(String message) {
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/admin/user/list");
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(UserRole.class, "userRole",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						UserRole role = new UserRole();
						role.setRole(text);
						setValue(role);
					}
				});
	}
}
