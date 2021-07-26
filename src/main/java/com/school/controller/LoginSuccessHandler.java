package com.school.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.school.constant.SystemConstant;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
		User userDetails = (User) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
         
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority(SystemConstant.ADMIN))) {
            redirectURL = "admin/";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority(SystemConstant.STUDENT))) {
            redirectURL = "home";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority(SystemConstant.PRINCIPAL))) {
        	redirectURL = "principal/";
        }
        response.sendRedirect(redirectURL);
    }
}
