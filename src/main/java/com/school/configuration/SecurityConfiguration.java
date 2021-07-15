package com.school.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.school.constant.SystemConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USER WHERE USERNAME=?")
		.authoritiesByUsernameQuery("SELECT USERNAME, ROLE FROM USER WHERE USERNAME=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole(SystemConstant.ADMIN)
		.and().authorizeRequests().antMatchers("/student/**").hasRole(SystemConstant.STUDENT)
		.and().authorizeRequests().antMatchers("/principal/**").hasRole(SystemConstant.PRINCIPAL)
		.anyRequest().permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
		.usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/login")
		.failureUrl("/login?error=failed").and().exceptionHandling().accessDeniedPage("/login?error=deny");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}
}
