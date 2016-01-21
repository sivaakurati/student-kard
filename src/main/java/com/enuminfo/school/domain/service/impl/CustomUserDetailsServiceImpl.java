/**
 * 
 */
package com.enuminfo.school.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enuminfo.school.domain.service.UserService;
import com.enuminfo.school.hibernate.model.Role;
import com.enuminfo.school.hibernate.model.User;

/**
 * @author Kumar
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired UserService userService;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.loadByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		} else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
					true, true, true, true, getGrantedAuthorities(user));
		}
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}
}
