package com.mins5.share.web.filter;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class WebApplicationLisener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(WebApplicationLisener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		if (null == application) {
			logger.info("Destroyed ServletContext ：" + application.toString());
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		if (application.getAttribute("nowUsers") == null) {
			application.setAttribute("nowUsers", new HashMap());
		}
		logger.info("application info：" + application.getServerInfo().toString());
	}

}
