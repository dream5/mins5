package com.mins5.share.web.controller.index;

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
import com.mins5.share.web.controller.base.BaseController;

/**
 * <p>
 * 首页控制器
 * </p>
 * 
 * @author zhanglin
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/index")
public class IndexController extends BaseController {

	private static final Log log = LogFactory.getLog(IndexController.class);

	/**
	 * 跳转至首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/goToIndex")
	public String goToIndex(HttpServletRequest request, HttpServletResponse response) {

		initMenu(request, response);

		// 查询所有文章
		if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);

		ReturnPageData<List<Article>> returnData = articleService.findArticlesByKindPinyin(null, currentPage, pageSize);
		if (returnData.getReturnCode() == 200 && !StringUtils.isEmpty(returnData.getResultData())) {
			request.setAttribute("totalArticleCount", returnData.getTotalResults());
			request.setAttribute("articlesList", returnData.getResultData());
		}

		// banner
		ReturnData<List<Article>> banner = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION, 8);
		if (banner.getReturnCode() == 200 && !StringUtils.isEmpty(banner.getResultData())) {
			request.setAttribute("bannerList", banner.getResultData());
		}

		// 热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION_RIGHT, 8);
		if (recommendArticles.getReturnCode() == 200 && !StringUtils.isEmpty(recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList", recommendArticles.getResultData());
		}
		// 随机阅读
		int amount = 8;
		ReturnData<List<Article>> randomArticles = articleService.findRandomArticle(amount);
		if (randomArticles.getReturnCode() == 200 && !StringUtils.isEmpty(randomArticles.getResultData())) {
			request.setAttribute("randomReadList", randomArticles.getResultData());
		}
		// 热门标签
		amount = 30;
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(amount);
		if (articleLabels.getReturnCode() == 200 && !StringUtils.isEmpty(articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}

		return "index";
	}

}
