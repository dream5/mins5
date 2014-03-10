/**
 * 
 */
package com.mins5.share.web.controller.login;

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
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(value="/login")
	public String login(String username, String password) {
		
		return "redirect:main.mins";
	}
	
	@RequestMapping(value="/main")
	public ModelAndView main() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("main");
		
		return mav;
	}
}
