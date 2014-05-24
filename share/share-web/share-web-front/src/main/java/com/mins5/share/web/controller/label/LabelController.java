package com.mins5.share.web.controller.label;

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
import com.mins5.share.web.controller.list.KindListController;
/**
 * <p>
 * 标签页面页控制器
 * </p>
 * @author zhanglin
 * @since 20140524
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/label")
public class LabelController extends BaseController {
	
	private static final Log log = LogFactory.getLog(LabelController.class);

	@RequestMapping(value = "/goToLabelList")
	public String goToLabelList(HttpServletRequest request,
			HttpServletResponse response) {
		initMenu(request, response);
		
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		String labelName = request.getParameter("n");
		String labelId = request.getParameter("labelId");
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("pageSize",pageSize);
		
		ReturnPageData<List<Article>> returnData = articleService.findArticlesByLabelName(labelId,labelName, currentPage, pageSize);
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

}
