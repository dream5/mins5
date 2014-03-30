/**
 * 
 */
package com.mins5.share.web.controller.article;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.service.ArticleLabelService;
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
public class ArticleLabelController {

	@Autowired
	private ArticleLabelService articleLabelService;

	/**
	 * 文章类别列表
	 * 
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/articleLabelList")
	public String articleLabelList() {
		return "article/article_label_list";
	}
	
	/**
	 * ajax查询文章标签
	 * @author zhoutian
	 * @since 2014年3月29日
	 * @param response
	 * @param labelName
	 * @param status
	 * @param beginTime
	 * @param endTime
	 * @param currentPage
	 */
	@RequestMapping(value = "/searchArticleLabel")
	public void searchArticleLabel(HttpServletResponse response, String labelName, String status,
			String beginTime, String endTime, Integer currentPage, Integer onePageSize) {
		
		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;
		
		ReturnPageData<List<ArticleLabel>> returnData = articleLabelService
				.findArticleLabelByLabelNameAndStatusAndCreateTime(StringUtils.parseNull(labelName),
						StringUtils.parseNull(status), DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT), DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT), currentPage, onePageSize);
		
		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), returnData.getResultData());
		JsonUtils.write(data, response);
	}
	
	/**
	 * 跳转到添加文章类别页面
	 * 
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @return
	 */
	@RequestMapping(value = "/toArticleLabelAdd")
	public String toArticleLabelAdd() {
		return "article/article_label_add";
	}

	/**
	 * ajax请求添加文章类别
	 * 
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @param response
	 * @param article
	 */
	@RequestMapping(value = "/articleLabelAdd")
	public void articleLabelAdd(HttpServletResponse response,
			ArticleLabel articleLabel) {
		Date currentDate = new Date();
		articleLabel.setCreateTime(currentDate);
		articleLabel.setStatus("0");
		ReturnData<ArticleLabel> returnData = articleLabelService
				.saveArticleLabel(articleLabel);
		String tip = "添加成功！";
		if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "添加失败！";
		}
		JsonUtils.write(tip, response);
	}
}
