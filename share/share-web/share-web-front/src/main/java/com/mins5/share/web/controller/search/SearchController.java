package com.mins5.share.web.controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.web.constant.SystemConstant;
import com.mins5.share.web.controller.base.BaseController;

/**
 * <P>
 * 公用搜索
 * </p>
 * <P>
 * 目前采用最简单实现方式，二期建议单独module且采用lucenes实现
 * </p>
 * 
 * @author zhanglin
 * @since 20140328
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/s")
public class SearchController extends BaseController {

	private static final Log log = LogFactory.getLog(SearchController.class);

	@RequestMapping(value = "/s")
	public String search(HttpServletRequest request, HttpServletResponse response) {
		String key = request.getParameter("k");

		initMenu(request, response);

		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);

		if (!StringUtils.isEmpty(key)) {
			ReturnPageData<List<Article>> returnData = searchService.complexSearchArticleData(key, currentPage, pageSize);
			if (checkQueryDataExist(returnData.getReturnCode(), returnData.getResultData())) {
				request.setAttribute("totalArticleCount", returnData.getTotalResults());
				request.setAttribute("articlesList", returnData.getResultData());
			}
			request.setAttribute("searchKey", key);
		}

		// 猜你喜欢
		ReturnData<List<Article>> randomArticles = articleService.findRandomArticle(SystemConstant.GUESS_LIKE_QUERY_COUNT);
		if (checkQueryDataExist(randomArticles.getReturnCode(), randomArticles.getResultData())) {
			request.setAttribute("randomReadList", randomArticles.getResultData());
		}

		// 热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION,
				SystemConstant.HOT_RECOMMEND_QUERY_COUNT);
		if (checkQueryDataExist(recommendArticles.getReturnCode(), recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList", recommendArticles.getResultData());
		}

		// 热门标签
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(SystemConstant.LABEL_QUERY_COUNT);
		if (checkQueryDataExist(articleLabels.getReturnCode(), articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}

		return "searchResult";
	}

	/**
	 * 根据分类拼音查询文章
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/key")
	public void getArticlesBykey(HttpServletRequest request, HttpServletResponse response) {
		int currentPage = 1;
		int pageSize = 10;
		if (!StringUtils.isEmpty(request.getParameter("c"))) {
			currentPage = Integer.parseInt(request.getParameter("c"));
		}
		if (!StringUtils.isEmpty(request.getParameter("p"))) {
			pageSize = Integer.parseInt(request.getParameter("p"));
		}
		String key = request.getParameter("k");
		ReturnPageData<List<Article>> returnData = searchService.complexSearchArticleData(key, currentPage, pageSize);
		if (checkQueryDataExist(returnData.getReturnCode(), returnData.getResultData())) {
			JsonUtils.write(returnData.getResultData(), response);
		} else {
			JsonUtils.write("error", response);
		}
	}

}
