/**
 * 
 */
package com.mins5.share.web.controller.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.ehcache.EHCacheManager;
import com.mins5.share.common.cache.CacheManager;
import com.mins5.share.common.enums.GENDER;
import com.mins5.share.util.ApplicationContextUtil;

/**
 * @author zhoutian
 * @since 2014-3-3
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/demo")
public class DemoController {

	/**
	 * 跳转到指定页面
	 * 
	 * @since 2014-3-6
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String main() {
		System.out.println("A.a1()");
		CacheManager ehcacheManager = (EHCacheManager) ApplicationContextUtil
				.getBean("cacheManager");
		Map map = ehcacheManager.getCacheMap("table.cache.idlist.vdomain");
		return "demo_chen/admin_main";
	}

	/**
	 * 指定get方式访问
	 * 
	 * @since 2014-3-6
	 * @return
	 */
	@RequestMapping(value = "/a2", method = RequestMethod.GET)
	public String a2() {
		System.out.println("A.a2()");
		return "demo/a2";
	}

	/**
	 * 获取request、response、session和页面参数方式
	 * 
	 * @since 2014-3-6
	 * @param request
	 * @param response
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/a3")
	public String a3(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, String username, String password) {
		System.out.println("A.a3()");
		System.out.println("username = " + username);
		System.out.println("password = " + password);
		return "demo/a3";
	}

	/**
	 * 以对象形式获取参数
	 * 
	 * @since 2014-3-6
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/a4")
	public String a4(TestUser user) {
		System.out.println("A.a4()");
		System.out.println("user.getUsername() = " + user.getUsername());
		System.out.println("user.getPassword() = " + user.getPassword());
		return "demo/a4";
	}

	/**
	 * 返回数据到页面
	 * 
	 * @since 2014-3-6
	 * @return
	 */
	@RequestMapping(value = "/a5")
	public ModelAndView a5() {
		System.out.println("A.a5()");

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

	/**
	 * 重定向
	 * 
	 * @since 2014-3-6
	 * @return
	 */
	@RequestMapping(value = "/a6")
	public String a6() {
		System.out.println("A.a6()");
		return "redirect:a1.mins";
	}

	/**
	 * 转发
	 * 
	 * @since 2014-3-6
	 * @return
	 */
	@RequestMapping(value = "/a7")
	public String a7() {
		System.out.println("A.a7()");
		return "forward:a1.mins";
	}

	/**
	 * freemarker
	 * 
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/f1")
	public ModelAndView f1() {
		System.out.println("A.f1()");

		ModelAndView mav = new ModelAndView();

		TestUser user = new TestUser();
		user.setUsername("张三");
		user.setPassword("111111");
		user.setGender(GENDER.SECRET);

		List<TestAdmin> adminList = new ArrayList<TestAdmin>();
		for (int i = 1; i <= 10; i++) {
			TestAdmin admin = new TestAdmin();
			admin.setUsername("李四" + i);
			admin.setPassword("password" + i);
			adminList.add(admin);
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("currentTime", new Date());
		paramMap.put("b1", true);
		paramMap.put("b2", false);

		mav.addObject("user", user);
		mav.addObject("paramMap", paramMap);
		mav.addObject("adminList", adminList);

		mav.setViewName("demo/f1");
		return mav;
	}
}
