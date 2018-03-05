package com.luotianyi.ssh2.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.FilterRegistration.Dynamic;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class WEBInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		initializerSpring(arg0);
		structInitializer(arg0);
	}

	public void initializerSpring(ServletContext arg0) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		arg0.addListener(new ContextLoaderListener(context));
	}

	public void structInitializer(ServletContext arg0) {
		Dynamic struct2 = arg0.addFilter("struts2", StrutsPrepareAndExecuteFilter.class);
		struct2.addMappingForUrlPatterns(null, false, "/*");
	}
}
