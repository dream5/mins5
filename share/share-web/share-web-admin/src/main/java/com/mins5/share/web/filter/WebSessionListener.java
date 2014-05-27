package com.mins5.share.web.filter;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class WebSessionListener implements HttpSessionListener {

	private static final Logger logger = Logger.getLogger(WebSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		logger.info(" 新加入会话session ID : " + session.getId());

		ServletContext application = event.getSession().getServletContext();
		logger.info("application : " + application.getContextPath());
		Map userMap = (Map) application.getAttribute("nowUsers");
		userMap.put(session.getId(), session.getCreationTime());

		logger.info("在线用户数：" + userMap.size());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		try {
			HttpSession session = event.getSession();
			if (session == null) {
				logger.info("已经不存在[HttpSession is null]");
				return;
			}
			logger.info(" Destroy会话session ID ：" + session.getId());
			ServletContext application = event.getSession().getServletContext();
			Map userMap = (Map) application.getAttribute("nowUsers");
			userMap.remove(session.getId());
			logger.info("在线用户数：" + userMap.size());
		} catch (Exception e) {
			logger.error("异常信息: " + e);
		}
	}

}
