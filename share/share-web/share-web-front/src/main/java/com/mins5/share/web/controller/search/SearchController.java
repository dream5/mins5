package com.mins5.share.web.controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.business.article.service.ArticleLabelService;
import com.mins5.share.business.article.service.ArticleRecommendService;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.business.article.service.SearchService;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.JsonUtils;

/**
 * <P>公用搜索</p>
 * <P>目前采用最简单实现方式，二期建议单独module且采用lucenes实现</p>
 * @author zhanglin
 * @since 20140328
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/s")
public class SearchController {
	
	private static final Log log = LogFactory.getLog(SearchController.class);
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleLabelService articleLabelService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private ArticleRecommendService recommendService;
	
	@RequestMapping(value = "/s")
	public String search(HttpServletRequest request,HttpServletResponse response){
		log.info("搜索开始...");
		String key = request.getParameter("k");
		int totalArticleCount = searchService.complexSearchArticleDataCount(key);
		request.setAttribute("totalArticleCount", totalArticleCount);

		// 猜你喜欢
		int amount = 6;
		ReturnData<List<Article>> randomArticles = articleService
				.findRandomArticle(amount);
		if (randomArticles.getReturnCode() == 200
				&& !StringUtils.isEmpty(randomArticles.getResultData())) {
			request.setAttribute("randomReadList",
					randomArticles.getResultData());
		}
		
		//热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION, 10);
		if (recommendArticles.getReturnCode()==200&&!StringUtils.isEmpty(recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList", recommendArticles.getResultData());
		}
		

		// 热门标签
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(amount);
		if (articleLabels.getReturnCode() == 200&& !StringUtils.isEmpty(articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}

		return "searchResult";
	}
	
	
	/**
	 * 根据分类拼音查询文章
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/key")
	public void getArticlesBykey(HttpServletRequest request,
			HttpServletResponse response){
		int currentPage = 1;
		int pageSize = 10;
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		String key  = request.getParameter("k");
		ReturnPageData<List<Article>> returnData = searchService.complexSearchArticleData(key, currentPage, pageSize);
		if (returnData.getReturnCode()==200&&!StringUtils.isEmpty(returnData.getResultData())) {
			 JsonUtils.write(returnData.getResultData(), response);
		}else {
			JsonUtils.write("error", response);
		}
	}
	
	
}
