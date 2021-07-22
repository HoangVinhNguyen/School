package com.school.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.model.RoleModel;
import com.school.model.UserModel;
import com.school.service.IRoleService;
import com.school.service.IUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userService.findByUserName(username);
		if (user == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		// [ROLE_USER, ROLE_ADMIN,..]
        RoleModel roleModel = roleService.findOne(user.getRoledId());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleModel != null) {
	        GrantedAuthority authority = new SimpleGrantedAuthority(roleModel.getCode());
	        grantList.add(authority);
        }
        UserDetails userDetails = (UserDetails) new User(user.getEmail(), user.getPassword(), grantList);
		return userDetails;
	}

}
