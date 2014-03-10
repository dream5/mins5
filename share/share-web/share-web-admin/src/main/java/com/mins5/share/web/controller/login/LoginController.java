/**
 * 
 */
package com.mins5.share.web.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.admin.service.AdminService;

/**
 * @author zhoutian
 * @since 2014年3月11日
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/login")
public class LoginController {
	
	private static final Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(value="/login")
	public String login(String username, String password) {
		
		return "redirect:main.mins";
	}
	
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		adminService.findAdminById(1L);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("main");
		
		return mav;
	}
}
