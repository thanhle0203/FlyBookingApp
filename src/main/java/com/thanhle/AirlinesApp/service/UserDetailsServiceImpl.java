package com.thanhle.AirlinesApp.service;

import java.util.Collection;
import java.util.HashSet;

import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = (User) userService.findByEmail(email);
		 
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		
		Collection<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			
		}
		
	}

	
	
	return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);

}
