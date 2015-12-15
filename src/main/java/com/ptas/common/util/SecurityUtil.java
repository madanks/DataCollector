package com.ptas.common.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import com.ptas.common.domain.User;
import com.ptas.common.domain.Organization;
import com.ptas.common.domain.UserRole;

public class SecurityUtil {
	private SecurityUtil() {
	}

	public static String getUsername() {
		User user = getAuthenticatedUser();
		return user.getUsername();
	}

	public static Long getUserId() {
		User user = getAuthenticatedUser();
		return user.getUserInfo().getUserId();
	}
	public static String getUserOrg() {
		User user = getAuthenticatedUser();
		return user.getOrganization().getName();
	}
	
	public static List<String> getRoles() {
		User user = getAuthenticatedUser();
		List<String> role= new ArrayList<String>();
		for(UserRole r:user.getUserRole())
		{
			role.add(r.getRole());
		}
		return role;
	}
	
	public static JSONObject getUserInfo() {
		
		try {
			JSONObject userdetails = new JSONObject();
			User user = getAuthenticatedUser();
			userdetails.put("FirstName", user.getUserInfo().getFirstName());
			userdetails.put("LastName", user.getUserInfo().getLastName());
			userdetails.put("Address", user.getUserInfo().getAddress());
			userdetails.put("Email", user.getUserInfo().getEmail());
			userdetails.put("Phone", user.getUserInfo().getPhone());
			userdetails.put("Organisation", user.getOrganization().getName());
			userdetails.put("OrganisationID", SecurityUtil.getUserOrganisation());
			userdetails.put("userId", SecurityUtil.getUserId());
			List<String> role= new ArrayList<String>();
			for(UserRole r:user.getUserRole())
			{
				role.add(r.getRole());
			}
			userdetails.put("Role",role);
			return userdetails;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static Long getUserOrganisation() {
		User user = getAuthenticatedUser();
		Organization o=user.getOrganization();
		return o.getId();
	}
	public static String getFirstName() {
		User user = getAuthenticatedUser();
		return user.getUserInfo().getFirstName();

	}
	public static String getFullName() {
		User user = getAuthenticatedUser();
		String fullName = user.getUserInfo().getFirstName() + " " + user.getUserInfo().getLastName();
		return fullName;
	}

	public static User getAuthenticatedUser() {
		User user = null;
		try {
			WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder
					.getContext().getAuthentication().getDetails();
			if (details.getSessionId() != null)
				if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser"))
				user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
			
			//setUserDetailsFromAuthenticatedUser
		} catch (NullPointerException e) {
			return null;
		}
		return user;
	}
}
