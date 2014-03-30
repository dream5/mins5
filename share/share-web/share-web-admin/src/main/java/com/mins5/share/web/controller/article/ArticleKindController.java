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

import com.mins5.share.business.article.domain.ArticleKind;
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
public class ArticleKindController {
	
	@Autowired
	private ArticleService articleService;

	/**
	 * 文章类别列表
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/articleKindList")
	public ModelAndView articleKindList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("article/article_kind_list");
		return mav;
	}
	
	/**
	 * 跳转到添加文章类别页面
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @return
	 */
	@RequestMapping(value = "/toArticleKindAdd")
	public String toArticleKindAdd() {
		return "article/article_kind_add";
	}
	
	/**
	 * ajax请求添加文章类别
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @param response
	 * @param article
	 */
	@RequestMapping(value = "/articleKindAdd")
	public void articleKindAdd(HttpServletResponse response, ArticleKind articleKind) {
		Date currentDate = new Date();
		articleKind.setKindPinyin(articleKind.getKindPinyin().toLowerCase());
		articleKind.setAdminId(0L);
		articleKind.setCreateTime(currentDate);
		articleKind.setParentKindId(0L);
		articleKind.setStatus("0");
		articleKind.setUpdateTime(currentDate);
		ReturnData<ArticleKind> returnData = articleService.saveArticleKind(articleKind);
		String tip = "添加成功！";
		if(returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "添加失败！";
		}
		JsonUtils.write(tip, response);
	}
}
