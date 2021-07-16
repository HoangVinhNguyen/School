package com.school.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(SchoolConfiguration.class);

		// Dispatcher Servlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setInitParameter("contextClass", appContext.getClass().getName());

		servletContext.addListener(new ContextLoaderListener(appContext));

		// UTF8 Charactor Filter.
//		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//
//		fr.setInitParameter("encoding", "UTF-8");
//		fr.setInitParameter("forceEncoding", "true");
//		fr.addMappingForUrlPatterns(null, true, "/*");

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
		servletContext.addFilter("encodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");
	}

}
