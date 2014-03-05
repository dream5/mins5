/**
 * 
 */
package com.mins5.share.web.controller.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.common.enums.GENDER;

/**
 * @author zhoutian
 * @since 2014-3-3
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/a")
public class A {

	@RequestMapping(value = "/a1")
	public String a1() {
		System.out.println("A.a1()");
		return "demo/a1";
	}

	@RequestMapping(value = "/a2", method = RequestMethod.GET)
	public String a2() {
		System.out.println("A.a2()");
		return "demo/a2";
	}

	@RequestMapping(value = "/a3")
	public String a3(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, String username, String password) {
		System.out.println("A.a3()");
		System.out.println("username = " + username);
		System.out.println("password = " + password);
		return "demo/a3";
	}
	
	@RequestMapping(value = "/a4")
	public String a4(TestUser user) {
		System.out.println("A.a4()");
		System.out.println("user.getUsername() = " + user.getUsername());
		System.out.println("user.getPassword() = " + user.getPassword());
		System.out.println(user.getGender());
		return "demo/a4";
	}

	@RequestMapping(value = "/a5")
	public ModelAndView a5() {
		ModelAndView mav = new ModelAndView();
		
		TestUser user = new TestUser();
		user.setUsername("张三");
		user.setPassword("111111");
		user.setGender(GENDER.SECRET);
		
		TestAdmin admin = new TestAdmin();
		admin.setUsername("李四");
		admin.setPassword("222222");
		
		mav.addObject("user", user);
		mav.addObject("admin", admin);
		mav.setViewName("demo/a5");
		return mav;
	}
}
