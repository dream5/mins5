/**
 * 
 */
package com.mins5.share.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/article")
public class ArticleController {

	/**
	 * 文章列表
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		System.out.println("list()");
		
		ModelAndView mav = new ModelAndView();
		                        
		mav.setViewName("article/article_list");
		return mav;
	} 
}
