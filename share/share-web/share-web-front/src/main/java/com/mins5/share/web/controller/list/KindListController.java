package com.mins5.share.web.controller.list;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.service.ArticleService;

/**
 * 分类类别
 * @author zhanglin
 * @since 20140317
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/kind")
public class KindListController {
	
	private static final Log log = LogFactory.getLog(KindListController.class);
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/goToKindList")
	public String goToKindList(HttpServletRequest request,HttpServletResponse response){
		log.info("跳转到分类列表页面...");
		String kind = request.getParameter("k");
		
		return "articleList";
	}
	

}
