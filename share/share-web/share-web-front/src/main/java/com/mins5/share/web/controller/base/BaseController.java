package com.mins5.share.web.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleLabelService;
import com.mins5.share.business.article.service.ArticleRecommendService;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.business.article.service.SearchService;
import com.mins5.share.common.service.ReturnData;

/**
 * <p>
 * 前端控制器基类
 * <p>
 * 
 * @author zhanglin
 * @date 20140417 15:48
 */
@Controller
@Scope("prototype")
public class BaseController {

	protected int currentPage = 1;
	protected int pageSize = 10;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Autowired
	protected ArticleService articleService;
	@Autowired
	protected SearchService searchService;
	@Autowired
	protected ArticleLabelService articleLabelService;
	@Autowired
	protected ArticleRecommendService recommendService;

	/**
	 * 初始化菜单
	 * 
	 * @param request
	 * @param response
	 */
	protected void initMenu(HttpServletRequest request, HttpServletResponse response) {
		// 初始化菜单
		ReturnData<List<ArticleKind>> articleKinds = articleService.findAllArticleKind();
		if (articleKinds.getReturnCode() == 200 && !StringUtils.isEmpty(articleKinds.getResultData())) {
			request.setAttribute("articleKinds", articleKinds.getResultData());
		}
	}
}
