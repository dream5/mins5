package com.mins5.share.web.controller.detail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
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
import com.mins5.share.web.controller.list.KindListController;

/**
 * <p>
 * 详情页控制器
 * </p>
 * 
 * @author zhanglin
 * @since 20140324
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/detail")
public class DetailController {

	private static final Log log = LogFactory.getLog(DetailController.class);

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleLabelService articleLabelService;
	@Autowired
	private ArticleRecommendService recommendService;

	@RequestMapping(value = "/goToDetail")
	public String goToDetail(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("跳转到详情页面...");
		String id = request.getParameter("id");
		String kind = request.getParameter("k");
		if (!StringUtils.isEmpty(id)) {
			ReturnData<Article> article  = articleService.findArticleById(Long.parseLong(id));
			if (article.getResultData()==null) {
				return "error";
			}
			request.setAttribute("article", article);
		}
		
		// 热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(
						RECOMMEND_POSITION.INDEX_POSITION, 7);
		if (recommendArticles.getReturnCode() == 200&& !StringUtils.isEmpty(recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList",recommendArticles.getResultData());
		}
		// 随机阅读
		int amount = 8;
		ReturnData<List<Article>> randomArticles = articleService.findRandomArticle(amount);
		if (randomArticles.getReturnCode() == 200&& !StringUtils.isEmpty(randomArticles.getResultData())) {
			request.setAttribute("randomReadList",randomArticles.getResultData());
		}
		// 热门标签
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(amount);
		if (articleLabels.getReturnCode() == 200&& !StringUtils.isEmpty(articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}
		return "detail";
	}
}
