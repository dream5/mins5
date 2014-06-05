/**
 * 
 */
package com.mins5.share.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mins5.share.util.MyStringUtils;

public class ActionFilter implements Filter {
//	private final String errorPage = ServiceConstants.errorPage;
//	private final String errorPageCrm = ServiceConstants.errorPageCrm;

	private String[] obviablePath;

	public static final String SESSION_KEY_INSTANCE_ID = "WORKFLOW_INSTANCEID";

	private static final String DEFAULT_OVERRIDE_REQUEST_PARAMETER = "instanceId";

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		String exclude = config.getInitParameter("obviablePath");
		if (exclude != null) {
			obviablePath = exclude.split(",");
		}
	}

	private boolean isObviable(String servletPath) {
//		if (errorPage.equals(servletPath) ||
//				errorPageCrm.equals(servletPath)) { return true; }

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String servletPath = MyStringUtils.parseEmpty(((HttpServletRequest) request).getServletPath());
		HttpSession session = ((HttpServletRequest) request).getSession();
		// 可排除的访问
		if (isObviable(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		CommonResult crt = new CommonResult();
		// 在线判断
		if (!ApplicationManager.isOnline(session)) {
			String optorsn = ApplicationManager.getOptorsn2(session);
			if (optorsn == null) {
				crt.setMessage("未登录系统或已离线，请您登录！");
				crt.setErrcode("SYS-90800");
//				if (appType.equals(ConstantsWeb.webCtx)) {
//					crt.setHrefUrl(ConstantsWeb.webUrl + "/manager");
//				}
//				crt.setIndex(appType);
			} else {
				String remoteHost = ApplicationManager.getRemoteHost(optorsn);
				if (remoteHost == null) {
					crt.setMessage("未登录系统或已离线，请您登录！");
					crt.setErrcode("SYS-90800");
//					if (appType.equals(ConstantsWeb.webCtx)) {
//						crt.setHrefUrl(ConstantsWeb.webUrl + "/manager");
//					}
//					crt.setIndex(appType);
				} else {
					crt.setMessage("您使用的帐号已经从另一机器登录!");
					crt.setErrcode("SYS-90800");
					//crt.setIndex(appType);
				}
			}
			CommonResultHelper.fillCommonResult(crt);
			request.setAttribute("", crt);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher(null);

			rd.forward(request, response);
			return;
		}

	}

	@Override
	public void destroy() {
	}

	private FilterConfig config;

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