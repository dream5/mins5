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

import org.springframework.web.bind.ServletRequestUtils;

import com.mins5.share.web.common.ServiceConstants;

public class ActionFilter implements Filter {
	private String errorPage = ServiceConstants.errorPage;
	private String errorPageCrm = ServiceConstants.errorPageCrm;

	private String[] obviablePath;

	public static final String SESSION_KEY_INSTANCE_ID = "WORKFLOW_INSTANCEID";

	private static final String DEFAULT_OVERRIDE_REQUEST_PARAMETER = "instanceId";

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		String exclude = config.getInitParameter("obviablePath");
		if (exclude != null) {
			obviablePath = exclude.split(",");
		}
	}

	private boolean isObviable(String servletPath) {
		if (errorPage.equals(servletPath) || errorPageCrm.equals(servletPath)) {
			return true;
		}
		
		if (obviablePath == null)
			return false;
		for (int i = 0; i < obviablePath.length; i++) {
			if (servletPath.indexOf(obviablePath[i])!=-1) {
				return true;
			}
		}
		return false;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String servletPath = StringUti.getNotNullString(((HttpServletRequest) request).getServletPath());
		//String appType = ActionUtils.getAppType(request);
		HttpSession session = ((HttpServletRequest) request).getSession();
		//if (servletPath.indexOf("foreignbusi")==-1&&servletPath.indexOf("foreignultimate")==-1&&servletPath.indexOf("terminal")==-1) {//�����ⲿ���ӵ������ַ
			// ���ų�ķ���
			if (isObviable(servletPath)) {
				chain.doFilter(request, response);
				return;
			}
			CommonResult crt = new CommonResult();
			// �����ж�
			if (!ApplicationManager.isOnline(session)) {
				crt.setBusiname("Ȩ�޹���");
				// ����Ա����XXX(IP)��½
				String optorsn = ApplicationManager.getOptorsn2(session);
				if (optorsn == null) {
					crt.setMessage("δ��¼ϵͳ�������ߣ������¼��");
					crt.setErrcode("SYS-90800");
					if (appType.equals(ConstantsWeb.webCtx)) {
						crt.setHrefUrl(ConstantsWeb.webUrl+"/manager");
					}
					crt.setIndex(appType);
				} else {
					String remoteHost = ApplicationManager
							.getRemoteHost(optorsn);
					if (remoteHost == null) {
						crt.setMessage("δ��¼ϵͳ�������ߣ������¼��");
						crt.setErrcode("SYS-90800");
						if (appType.equals(ConstantsWeb.webCtx)) {
							crt.setHrefUrl(ConstantsWeb.webUrl+"/manager");
						}
						crt.setIndex(appType);
					} else {
						crt.setMessage("��ʹ�õ��ʺ��Ѿ�����һ������¼!");
						crt.setErrcode("SYS-90800");
						crt.setIndex(appType);
					}
				}
				CommonResultHelper.fillCommonResult(crt);
				request.setAttribute(Constants.COMMONRESULT, crt);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher(ActionUtils
						.getErrPage(appType));

				rd.forward(request, response);
				return;
			}

			// Ӧ��session�ж�
			if (appType.equals(ConstantsWeb.webCtx)) {
				if (ApplicationManager.isOnline(session)) {
					Authorization auth = ApplicationManager
							.getAuthorization(session);
					if (auth.getOperator() == null) {
						crt.pushErrorInfo("δ��¼ϵͳ�������ߣ������¼��");
						crt.setIndex(appType);
						CommonResultHelper.fillCommonResult(crt);
						request.setAttribute(Constants.COMMONRESULT, crt);
						RequestDispatcher rd = request
								.getRequestDispatcher(ActionUtils
										.getErrPage(appType));
						rd.forward(request, response);
						return;
					}
				}
			} else if (appType.equals("manager")) {
				if (ApplicationManager.isOnline(session)) {
					Authorization auth = ApplicationManager
							.getAuthorization(session);
					if (auth.getManagerOptor() == null) {
						crt.pushErrorInfo("δ��¼ϵͳ�������ߣ������½��");
						crt.setIndex(appType);
						CommonResultHelper.fillCommonResult(crt);
						request.setAttribute(Constants.COMMONRESULT, crt);
						RequestDispatcher rd = request
								.getRequestDispatcher(ActionUtils
										.getErrPage(appType));
						rd.forward(request, response);
						return;
					}
				}
			}

			/*
			 * //Ȩ���ж� String menuId = MenuManager.getMenuID(servletPath);
			 * if(menuId == null){ String command =
			 * request.getParameter("command"); if(command != null){ menuId =
			 * MenuManager.getMenuID(servletPath + "?command=" + command); } }
			 * 
			 * if(menuId != null){ try { Authorization auth =
			 * ApplicationManager.getAuthorization(session); Permissions perms =
			 * PermissionsManager.getInstance(auth).getFunctionPermissions();
			 * if(!perms.hasPermission(menuId)){ crt.setMessage("����Աû���������Ȩ�ޣ�");
			 * crt.setErrcode("SYS-90802");
			 * CommonResultHelper.fillCommonResult(crt);
			 * request.setAttribute(Constants.COMMONRESULT, crt);
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher(this.getErrPage(appType));
			 * rd.forward(request, response); return; } } catch
			 * (NotAnyPermissionsException e) {
			 * request.setAttribute(Constants.COMMONRESULT,
			 * CommonResultHelper.converter(e)); RequestDispatcher rd =
			 * request.getRequestDispatcher(this.getErrPage(appType));
			 * rd.forward(request, response); return; } } // Pass control on to
			 * the next filter
			 * 
			 */
			// chain.doFilter(request, response);
		
			try {
				Authorization operator = ApplicationManager
						.getAuthorization(session);

				OperatorContext operatorContext = OperatorContextHolder
						.getOperatorContext();
				operatorContext.setManagerOptor(operator.getManagerOptor());

				OsWorkflowContext context = OsWorkflowContextHolder
						.getWorkflowContext();
				context.setCaller(operator.getOperatorId());
				long instanceIdFromRequest = ServletRequestUtils
						.getLongParameter(request,
								DEFAULT_OVERRIDE_REQUEST_PARAMETER,
								Long.MIN_VALUE);
				if (instanceIdFromRequest != Long.MIN_VALUE) {
					System.out.println("Setting instance id");
					context.setInstanceId(instanceIdFromRequest);
				}

				chain.doFilter(request, response);

				// session.setAttribute(SESSION_KEY_INSTANCE_ID, new
				// Long(context.getInstanceId()));

			} catch (NullPointerException e) {
				crt.pushErrorInfo("����Ա�Ѿ����߻���δ��¼��");
				CommonResultHelper.fillCommonResult(crt);
				request.setAttribute(Constants.COMMONRESULT, crt);
				RequestDispatcher rd = request.getRequestDispatcher(ActionUtils
						.getErrPage(appType));
				rd.forward(request, response);
				return;
			} finally {
				OperatorContextHolder.clear();
			}

	}

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