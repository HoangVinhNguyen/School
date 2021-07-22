package com.school.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.school.constant.SystemConstant;
import com.school.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USER WHERE USERNAME=?")
//		.authoritiesByUsernameQuery("SELECT USERNAME, ROLE FROM USER WHERE USERNAME=?");
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole(SystemConstant.ADMIN)
//		.and().authorizeRequests().antMatchers("/student/**").hasRole(SystemConstant.STUDENT)
//		.and().authorizeRequests().antMatchers("/principal/**").hasRole(SystemConstant.PRINCIPAL)
//		.anyRequest().permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login-check")
//		.usernameParameter("username").passwordParameter("password")
//		.defaultSuccessUrl("/home")
//		.failureUrl("/login?error=failed").and().exceptionHandling().accessDeniedPage("/login?error=deny");
		
		http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
        http.authorizeRequests().antMatchers("/student/**").hasAnyAuthority(SystemConstant.STUDENT, SystemConstant.ADMIN);
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority(SystemConstant.ADMIN);
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()
        		.loginPage("/login")
                .loginProcessingUrl("/login-check")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .and().logout().logoutUrl("/logout-check").logoutSuccessUrl("/logout");
 
        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}
	
	// Token stored in Table (Persistent_Logins)
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//	    JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//	    db.setDataSource(this.dataSource);
//	    return db;
//	}
	 
	// Token stored in Memory (Of Web Server).
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}
	
}
