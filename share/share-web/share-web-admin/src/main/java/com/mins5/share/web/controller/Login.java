/**
 * 
 */
package com.mins5.share.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.business.user.service.UserService;

/**
 * @author zhoutian
 * @since 2014-3-2
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/login")
public class Login {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-------------------");
		System.out.println(adminService);
		System.out.println(userService);
		System.out.println("-------------------");
	}

}
