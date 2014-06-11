/**
 * 
 */
package com.mins5.share.web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.util.MD5;
import com.mins5.share.web.common.Configger;
import com.mins5.share.web.common.Constants;
import com.mins5.share.web.filter.ApplicationManager;

/**
 * 
 * @author chenry
 * @since 2014年6月5日
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/login")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/index")
	public String index(String username, String password) {
		return "login/index";
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, String userName, String password, String random) {
		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setPassword(password);

		ModelAndView view = new ModelAndView("redirect:main.mins");
		view.addObject("systemLongUrl", Configger.getString("system.login.url"));

		ReturnData<Admin> returnData = adminService.findByUserName(userName);
		if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			view = new ModelAndView("login/index");
			view.addObject("msg", "查询出错!");
			//commonResult.setHrefUrl(ConstantsWeb.webUrl + "/manager");
			return view;
		}

		// 判断登陆密码
		String adminPassword = returnData.getResultData().getPassword();
		if (!MD5.encode(password).equals(adminPassword)) {
			view = new ModelAndView("login/index");
			view.addObject("msg", "登录失败,您输入的登陆密码错误!");
			view.addObject(admin);
			return view;
		}

		// 判断随机码 begin
		HttpSession session = request.getSession();
		String randomToken = (String) session.getAttribute(Constants.RANDOM_TOKEN);
		session.removeAttribute(Constants.RANDOM_TOKEN);
		if (!randomToken.equals(random)) {
			view = new ModelAndView("login/index");
			view.addObject("msg", "登录失败,您输入的随机码错误!");
			view.addObject(admin);
			return view;
		}
		// 判断随机码 end

		// 验证		
//		String hostIP = InetAddress.getLocalHost().getHostAddress();
//		int hostPort = ApplicationContext.getServerPort();
//		String hostIPPort = hostIP + ":" + hostPort;
//		log.info("IPPORT:" + hostIPPort);

		log.info(userName + "登陆成功!");
		session.setAttribute("loginUserName", userName);
		ApplicationManager.put(userName, request.getSession().getId());
		return view;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		log.info(session.getAttribute("loginUserName") + "退出!");
		session.removeAttribute("loginUserName");
		ApplicationManager.remove2(session);
		ModelAndView view = new ModelAndView("login/index");
		return view;
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}

	/**
	 * 验证用户名的唯一性
	 * 
	 * @param response
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "login/checkUserName", method = RequestMethod.POST)
	public void checkUserName(HttpServletResponse response, String userName) {
		boolean isHave = (adminService.checkUserName(userName)).getResultData();
		String result = "";
		PrintWriter out = null;
		try {
			response.setContentType("text/plain;charset=utf-8");
			out = response.getWriter();
			out.print(result);
		} catch (IOException ex) {
		} finally {
			out.close();
		}
	}

}
