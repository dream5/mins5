package com.mins5.share.web.controller.list;

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
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.web.controller.base.BaseController;

/**
 * 分类类别
 * 
 * @author zhanglin
 * @since 20140317
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/kind")
public class KindListController extends BaseController {

	private static final Log log = LogFactory.getLog(KindListController.class);

	@RequestMapping(value = "/goToKindList")
	public String goToKindList(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("跳转到分类列表页面...");
		
		initMenu(request, response);
		
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		String kindPinyin = request.getParameter("k");
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("pageSize",pageSize);
		request.setAttribute("kind",kindPinyin);
		
		ReturnPageData<List<Article>> returnData = articleService.findArticlesByKindPinyin(kindPinyin, currentPage, pageSize);
		if (returnData.getReturnCode()==200&&!StringUtils.isEmpty(returnData.getResultData())) {
			request.setAttribute("totalArticleCount", returnData.getTotalResults());
			request.setAttribute("articlesList", returnData.getResultData());
		}

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

		return "articleList";
	}
	
	/**
	 * 根据分类拼音查询文章
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getArticlesByPinyin")
	public void getArticlesByPinyin(HttpServletRequest request,
			HttpServletResponse response){
		int currentPage = 1;
		int pageSize = 10;
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		String kindPinyin  = request.getParameter("k");
		ReturnPageData<List<Article>> returnData = articleService.findArticlesByKindPinyin(kindPinyin, currentPage, pageSize);
		if (returnData.getReturnCode()==200&&!StringUtils.isEmpty(returnData.getResultData())) {
			 JsonUtils.write(returnData.getResultData(), response);
		}else {
			JsonUtils.write("error", response);
		}
	}
	

}
