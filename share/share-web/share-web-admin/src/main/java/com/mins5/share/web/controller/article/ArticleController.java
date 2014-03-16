/**
 * 
 */
package com.mins5.share.web.controller.article;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.enums.ARTICLE_STATUS;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.util.JsonUtils;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	/**
	 * 文章列表
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/articleList")
	public ModelAndView articleList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("article/article_list");
		return mav;
	}
	
	/**
	 * 跳转到添加文章页面
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @return
	 */
	@RequestMapping(value = "/toArticleAdd")
	public String toArticleAdd() {
		return "article/article_add";
	}
	
	/**
	 * ajax请求添加文章
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @param response
	 * @param article
	 */
	@RequestMapping(value = "/articleAdd")
	public void articleAdd(HttpServletResponse response, Article article) {
		Date currentDate = new Date();
		article.setCreateTime(currentDate);
		article.setUpdateTime(currentDate);
		article.setStatus(ARTICLE_STATUS.WAITING_CHECK);
		ReturnData<Article> returnData = articleService.saveArticle(article);
		String tip = "添加成功！";
		if(returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "添加失败！";
		}
		JsonUtils.write(tip, response);
	}
}
