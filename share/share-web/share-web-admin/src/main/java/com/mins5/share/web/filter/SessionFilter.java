package com.mins5.share.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionFilter implements Filter {
	private static final Logger logger = Logger.getLogger(ApplicationManager.class);

	/** 门户报错页面 */
	public static String errorPage = "/error.jsp";

	private String[] obviablePath;
	private FilterConfig config;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		String servletPath = ((HttpServletRequest) arg0).getServletPath().trim();
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();

		/*
		 * if (request.getRequestURI().equals("/login/index.mins")) {
		 * arg2.doFilter(arg0, arg1); return; }
		 */

		// 可排除的访问
		logger.info("isObviable(servletPath):结果" + isObviable(servletPath));
		if (isObviable(servletPath)) {
			arg2.doFilter(request, response);
			return;
		}

		if (ApplicationManager.isOnline(session)) {
			arg2.doFilter(arg0, arg1);
		} else {
			this.returnToLogin(response);
		}

	}

	private void returnToLogin(HttpServletResponse response) {
		String str = "<script>alert('您尚未登录或已离线，请您重新登录！');window.open(\"/share-web-admin/login/index.mins\",'_top','top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=yes,channelmode');</script>";

		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isObviable(String servletPath) {
		if (errorPage.equals(servletPath)) {
			return true;
		}

		if (obviablePath == null)
			return false;
		for (int i = 0; i < obviablePath.length; i++) {
			if (servletPath.indexOf(obviablePath[i]) != -1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		String exclude = config.getInitParameter("obviablePath");
		if (exclude != null) {
			obviablePath = exclude.split(",");
		}
	}

	public FilterConfig getFilterConfig() {
		return config;
	}

	public void setFilterConfig(FilterConfig config) {
		try {
			init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
