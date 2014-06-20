package com.mins5.share.web.controller.detail;

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
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.web.constant.SystemConstant;
import com.mins5.share.web.controller.base.BaseController;

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
public class DetailController extends BaseController {

	private static final Log log = LogFactory.getLog(DetailController.class);

	@RequestMapping(value = "/goToDetail")
	public String goToDetail(HttpServletRequest request, HttpServletResponse response) {

		initMenu(request, response);
		String id = request.getParameter("id");
		String kind = request.getParameter("k");
		if (!StringUtils.isEmpty(id)) {
			ReturnData<Article> article = articleService.findArticleById(Long.parseLong(id));
			if (article.getResultData() == null) {
				return "error";
			}
			ReturnData<ArticleKind> articleKind = articleService.findArticleKindByPinyin(kind);
			request.setAttribute("article", article.getResultData());
			request.setAttribute("articleKind", articleKind.getResultData());
		}

		// 上一篇，下一篇
		ReturnData<List<Article>> preAndNextArticle = articleService.findPreAndNextArticles(Integer.parseInt(id), kind);
		if (checkQueryDataExist(preAndNextArticle.getReturnCode(), preAndNextArticle.getResultData())) {
			try {
				request.setAttribute("preArticle", preAndNextArticle.getResultData().get(0));
				request.setAttribute("nextArticle", preAndNextArticle.getResultData().get(1));
			} catch (Exception e) {
				log.error("查询上一篇，下一篇文章异常:[" + e.toString() + "]");
			}
		}

		// 热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.DETAIL_POSITION,
				SystemConstant.DETAIL_HOT_RECOMMEND_QUERY_COUNT);
		if (checkQueryDataExist(recommendArticles.getReturnCode(), recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList", recommendArticles.getResultData());
		}
		// 随机阅读
		ReturnData<List<Article>> randomArticles = articleService.findRandomArticle(SystemConstant.RONDOM_READING_QUERY_COUNT);
		if (checkQueryDataExist(randomArticles.getReturnCode(), randomArticles.getResultData())) {
			request.setAttribute("randomReadList", randomArticles.getResultData());
		}
		// 热门标签
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(SystemConstant.HOT_RECOMMEND_QUERY_COUNT);
		if (checkQueryDataExist(articleLabels.getReturnCode(), articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}
		return "detail";
	}
}
