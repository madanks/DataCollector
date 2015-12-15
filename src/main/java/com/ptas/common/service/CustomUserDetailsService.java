package com.ptas.common.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptas.common.dao.UserDAO;
import com.ptas.common.domain.UserRole;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;	

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		com.ptas.common.domain.User domainUser = userDAO.getUser(userName);
		List<? extends GrantedAuthority> authorities = 
                                      buildUserAuthority(domainUser.getUserRole());
 
		domainUser.setAuthorities(authorities);
		//return buildUserForAuthentication(domainUser, authorities);
		return domainUser;
	}
	
	/*private User buildUserForAuthentication(com.ptas.common.domain.User domainUser, 
			List<? extends GrantedAuthority> authorities) {
			return new User(domainUser.getUsername(), domainUser.getPassword(), 
				domainUser.isEnabled(), true, true, true, authorities);
	}*/
	
	private List<? extends GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		for(UserRole userRole : userRoles) {
			List<String> roles = getRoles(userRole);
			for (String role : roles) {
				setAuths.add(new SimpleGrantedAuthority(role));
			}
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
	
	public List<String> getRoles(UserRole role) {

		List<String> roles = new ArrayList<String>();

		if ("ADMIN".equals(role.getRole())) {
			roles.add("ROLE_USER");
			roles.add("ROLE_MODERATOR");
			roles.add("ROLE_ADMIN");
		}else if ("SUPERVISOR".equals(role.getRole())) {
			roles.add("ROLE_MODERATOR");
			roles.add("ROLE_USER");
		} else if ("INSPECTOR".equals(role.getRole())) {
			roles.add("ROLE_USER");
		}
		return roles;
	}
}
