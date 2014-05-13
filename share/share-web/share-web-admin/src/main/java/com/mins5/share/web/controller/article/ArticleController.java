/**
 * 
 */
package com.mins5.share.web.controller.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.enums.ARTICLE_STATUS;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.DateUtils;
import com.mins5.share.util.EasyUIUtils;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.util.StringUtils;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/article")
public class ArticleController {

	private static final Log log = LogFactory.getLog(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	/**
	 * 文章列表
	 * 
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/articleList")
	public String articleList(HttpServletRequest request) {
		ARTICLE_STATUS[] articleStatusArray = ARTICLE_STATUS.values();
		request.setAttribute("articleStatusArray", articleStatusArray);
		return "article/article_list";
	}

	/**
	 * 文章列表查询
	 * 
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param response
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 */
	@RequestMapping(value = "/searchArticle")
	public void searchArticle(HttpServletResponse response, String articleTitle, String status, String beginTime, String endTime, String isOriginal,
			Integer currentPage, Integer onePageSize) {

		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;

		ReturnPageData<List<Article>> returnData = articleService.findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(
				StringUtils.parseNull(articleTitle), StringUtils.parseNull(status), DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT),
				DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT), StringUtils.parseNull(isOriginal), currentPage, onePageSize);

		List<Article> articleList = returnData.getResultData();
		List<Map<String, Object>> articleMapList = new ArrayList<Map<String, Object>>();
		if (articleList != null) {
			for (Article article : articleList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("articleId", article.getArticleId());
				map.put("articleTitle", article.getArticleTitle());
				map.put("createTime", article.getCreateTime());
				map.put("isOriginal", article.getIsOriginal());
				map.put("status", article.getStatus().getName());
				articleMapList.add(map);
			}
		}

		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), articleMapList);
		JsonUtils.write(data, response);
	}

	/**
	 * 抓取文章列表查询
	 * 
	 * @author zhanglin copy from searchArticle方法
	 * @since 2014年4月8日
	 * @param response
	 * @param articleTitle 文章标题
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 */
	@RequestMapping(value = "/searchCaptureArticle")
	public void searchCaptureArticle(HttpServletResponse response, String articleTitle, String beginTime, String endTime, Integer currentPage,
			Integer onePageSize) {

		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;

		ReturnPageData<List<Article>> returnData = articleService.findAllCaptureArticlesByCondition(StringUtils.parseNull(articleTitle),
				DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT), DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT), currentPage, onePageSize);

		List<Article> articleList = returnData.getResultData();
		List<Map<String, Object>> articleMapList = new ArrayList<Map<String, Object>>();
		if (articleList != null) {
			for (Article article : articleList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("articleId", article.getArticleId());
				map.put("articleTitle", article.getArticleTitle());
				map.put("createTime", article.getCreateTime());
				map.put("articleAuthor", article.getArticleAuthor());
				map.put("articleFrom", article.getArticleFrom());
				map.put("articleSts", article.getStatus());
				articleMapList.add(map);
			}
		}

		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), articleMapList);
		JsonUtils.write(data, response);
	}

	/**
	 * 跳转到添加文章页面
	 * 
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @return
	 */
	@RequestMapping(value = "/toArticleAdd")
	public String toArticleAdd() {
		return "article/article_add";
	}

	/**
	 * ajax请求添加文章
	 * 
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @param response
	 * @param article
	 */
	@RequestMapping(value = "/articleAdd")
	public void articleAdd(HttpServletResponse response, Article article, String articleKind, String articleLabel) {

		try {

			Date currentDate = new Date();
			article.setCreateTime(currentDate);
			article.setUpdateTime(currentDate);
			article.setStatus(ARTICLE_STATUS.WAITING_CHECK);

			String[] articleKindIdArray = articleKind.split(",");
			List<Long> articleKindIdList = new ArrayList<Long>();
			for (String articleKindId : articleKindIdArray) {
				articleKindIdList.add(Long.parseLong(articleKindId));
			}

			String[] articleLabelIdArray = articleLabel.split(",");
			List<Long> articleLabelIdList = new ArrayList<Long>();
			for (String articleLabelId : articleLabelIdArray) {
				articleLabelIdList.add(Long.parseLong(articleLabelId));
			}

			ReturnData<Article> returnData = articleService.saveArticle(article, articleKindIdList, articleLabelIdList);

			String tip = "添加成功！";
			if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
				tip = "添加失败！";
			}
			JsonUtils.write(tip, response);
		} catch (Exception e) {
			log.error("添加文章失败！", e);
			JsonUtils.write("添加失败！", response);
		}
	}

	/**
	 * 查看文章详细信息
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param articleId
	 */
	@RequestMapping(value = "/articleDetail")
	public void articleDetail(HttpServletResponse response, Long articleId) {
		ReturnData<Article> returnData = articleService.findArticleById(articleId);
		Article article = returnData.getResultData();
		JsonUtils.write(article, response);
	}

	/**
	 * 跳转到文章修改页面
	 * 
	 * @author zhoutian
	 * @since 2014年4月9日
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "/toArticleEdit")
	public ModelAndView toArticleEdit(Long articleId) {
		ModelAndView mv = new ModelAndView();
		Article article = articleService.findArticleById(articleId).getResultData();
		mv.addObject("article", article);
		mv.setViewName("article/article_edit");
		return mv;
	}

	public void articleEdit() {

	}

	/**
	 * 删除文章
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param articleId
	 */
	@RequestMapping(value = "/articleDelete")
	public void articleDelete(HttpServletResponse response, Long articleId) {
		String tip = "删除成功！";
		try {
			articleService.deleteArticleById(articleId);
		} catch (Exception e) {
			tip = "删除失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 发布文章
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param articleId
	 */
	@RequestMapping(value = "/publishedArticle")
	public void publishedArticle(HttpServletResponse response, Long articleId) {
		String tip = "发布成功！";
		try {
			articleService.publishedArticle(articleId);
		} catch (Exception e) {
			tip = "发布失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * <p>
	 * 将临时表数据推送到正式表
	 * </p>
	 * 
	 * @param response
	 * @param articleId
	 * @author zhanglin
	 * @since 2014年5月13日
	 */
	@RequestMapping(value = "/publishedArticleToTable")
	public void publishedArticleToTable(HttpServletResponse response, Long articleId) {
		String tip = "发布成功！";
		try {
			articleService.publishedArticleToTable(articleId);
		} catch (Exception e) {
			tip = "发布失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 下架文章
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param articleId
	 */
	@RequestMapping(value = "/removedArticle")
	public void removedArticle(HttpServletResponse response, Long articleId) {
		String tip = "下架成功！";
		try {
			articleService.removedArticle(articleId);
		} catch (Exception e) {
			tip = "下架失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 查看抓取文章详细信息
	 * 
	 * @author zhanglin
	 * @since 20140506
	 * @param response
	 * @param articleId
	 */
	@RequestMapping(value = "/captureArticleDetail")
	public void captureArticleDetail(HttpServletResponse response, Long articleId) {
		ReturnData<Article> returnData = articleService.findCaptureArticleById(articleId);
		Article article = returnData.getResultData();
		JsonUtils.write(article, response);
	}

}
