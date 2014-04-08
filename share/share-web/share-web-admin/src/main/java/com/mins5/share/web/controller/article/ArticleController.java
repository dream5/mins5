/**
 * 
 */
package com.mins5.share.web.controller.article;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.enums.ARTICLE_STATUS;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.DateUtils;
import com.mins5.share.util.EasyUIUtils;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.util.StringUtils;

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
	public String articleList() {
		return "article/article_list";
	}
	
	/**
	 * 文章列表查询
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param response
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 */
	@RequestMapping(value = "/searchArticle")
	public void searchArticle(HttpServletResponse response,
			String articleTitle, String status, String beginTime,
			String endTime, String isOriginal, Integer currentPage,
			Integer onePageSize) {
		
		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;

		ReturnPageData<List<Article>> returnData = articleService
				.findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(
						StringUtils.parseNull(articleTitle),
						StringUtils.parseNull(status),
						DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT),
						DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT),
						StringUtils.parseNull(isOriginal), currentPage,
						onePageSize);

		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), returnData.getResultData());
		JsonUtils.write(data, response);
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
