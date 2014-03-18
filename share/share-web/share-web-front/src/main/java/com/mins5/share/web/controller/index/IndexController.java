package com.mins5.share.web.controller.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.business.article.service.ArticleLabelService;
import com.mins5.share.business.article.service.ArticleRecommendService;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.JsonUtils;

/**
 * <p>首页控制器</p>
 * @author zhanglin
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/index")
public class IndexController {
	
	private static final Log log = LogFactory.getLog(IndexController.class);
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleLabelService articleLabelService;
	@Autowired
	private ArticleRecommendService recommendService;
	
	/**
	 * 跳转至首页
	 * @return
	 */
	@RequestMapping(value = "/goToIndex")
	public String goToIndex(HttpServletRequest request,HttpServletResponse response){
		//查询所有文章
		int totalArticleCount = articleService.findAllArticlesCount();
		request.setAttribute("totalArticleCount", totalArticleCount);
		
		
		//热门推荐
		ReturnData<List<Article>> recommendArticles = recommendService.findRecommendByPositionAndCount(RECOMMEND_POSITION.INDEX_POSITION, 7);
		if (recommendArticles.getReturnCode()==200&&!StringUtils.isEmpty(recommendArticles.getResultData())) {
			request.setAttribute("recommendArticlesList", recommendArticles.getResultData());
		}
		//随机阅读
		int amount = 8;
		ReturnData<List<Article>> randomArticles = articleService.findRandomArticle(amount);
		if (randomArticles.getReturnCode()==200&&!StringUtils.isEmpty(randomArticles.getResultData())) {
			request.setAttribute("randomReadList", randomArticles.getResultData());
		}
		//热门标签
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(amount);
		if (articleLabels.getReturnCode()==200&&!StringUtils.isEmpty(articleLabels.getResultData())) {
			request.setAttribute("hotLabelList", articleLabels.getResultData());
		}
		
		return  "index";
	}
	
	/**
	 * 获取分页文章
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getArticlesByPageNo")
	public void getArticlesByPageNo(HttpServletRequest request,HttpServletResponse response){
		int currentPage = 1;
		int pageSize = 15;
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		ReturnPageData<List<Article>> returnData = articleService.findArticleByCondition(null, currentPage, pageSize);
		if (returnData.getReturnCode()==200&&!StringUtils.isEmpty(returnData.getResultData())) {
			 JSONArray artcles = JSONArray.fromObject(returnData.getResultData()); 
			 JsonUtils.write(artcles.toString(), response);
		}else {
			JsonUtils.write("error", response);
		}
	}
	
	
	/**
	 * 初始化导航菜单
	 */
	@RequestMapping(value = "/initNav")
	public void initNavigate(HttpServletResponse response) {
		log.info("加载主页导航菜单...");
		ReturnData<List<ArticleKind>> articleKinds = articleService.findAllArticleKind();
		if (articleKinds.getReturnCode()==200&&!StringUtils.isEmpty(articleKinds.getResultData())) {
			 JSONArray articleKind = JSONArray.fromObject(articleKinds.getResultData()); 
			 JsonUtils.write(articleKind.toString(), response);
		}else {
			JsonUtils.write("error", response);
		}
	}

	
	
	/**
	 * 随机阅读
	 */
	@RequestMapping(value = "/randomRead")
	public void randomRead(HttpServletRequest request,HttpServletResponse response) {
		log.info("加载随机阅读文章...");
		int amount = 8;//默认取八篇
		if (!StringUtils.isEmpty(request.getParameter("a"))) {
			amount = Integer.parseInt(request.getParameter("a"));
		}
		ReturnData<List<Article>> articles = articleService.findRandomArticle(amount);
		if (articles.getReturnCode()==200&&!StringUtils.isEmpty(articles.getResultData())) {
			 JSONArray article = JSONArray.fromObject(articles.getResultData()); 
			 JsonUtils.write(article.toString(), response);
		}else {
			 JsonUtils.write("error", response);
		}
	}
	
	/**
	 * 热门标签
	 */
	@RequestMapping(value = "/hotLabel")
	public void hotLabel(HttpServletRequest request,HttpServletResponse response){
		log.info("加载热门标签...");
		int amount = 8;//默认取八篇
		if (!StringUtils.isEmpty(request.getParameter("a"))) {
			amount = Integer.parseInt(request.getParameter("a"));
		}
		ReturnData<List<ArticleLabel>> articleLabels = articleLabelService.findArticleLabelByNum(amount); 
		if (articleLabels.getReturnCode()==200&&!StringUtils.isEmpty(articleLabels.getResultData())) {
			 JSONArray articleLabel = JSONArray.fromObject(articleLabels.getResultData()); 
			 JsonUtils.write(articleLabel.toString(), response);
		}else {
			 JsonUtils.write("error", response);
		}
		
	}
	
	
	
	
}
