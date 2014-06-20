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
import com.mins5.share.web.constant.SystemConstant;
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

		ReturnPageData<List<Article>> articleData = articleService.findArticlesByKindPinyin(null, currentPage, pageSize);
		if (checkQueryDataExist(articleData.getReturnCode(), articleData.getResultData())) {
			request.setAttribute("totalArticleCount", articleData.getTotalResults());
			request.setAttribute("articlesList", articleData.getResultData());
		}

		// banner
		ReturnData<List<Article>> banner = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION,
				SystemConstant.BANNER_QUERY_COUNT);
		if (checkQueryDataExist(banner.getReturnCode(), banner.getResultData())) {
			request.setAttribute("bannerList", banner.getResultData());
		}

		// 热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION_RIGHT,
				SystemConstant.HOT_RECOMMEND_QUERY_COUNT);
		if (checkQueryDataExist(recommendArticles.getReturnCode(), recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList", recommendArticles.getResultData());
		}
		// 随机阅读
		ReturnData<List<Article>> randomArticles = articleService.findRandomArticle(SystemConstant.RONDOM_READING_QUERY_COUNT);
		if (checkQueryDataExist(randomArticles.getReturnCode(), randomArticles.getResultData())) {
			request.setAttribute("randomReadList", randomArticles.getResultData());
		}
		// 热门标签
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(SystemConstant.LABEL_QUERY_COUNT);
		if (checkQueryDataExist(articleLabels.getReturnCode(), articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}
		return "index";
	}

}
