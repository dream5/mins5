package com.mins5.share.web.controller.search;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.service.ArticleLabelService;
import com.mins5.share.business.article.service.ArticleService;

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
	
	@RequestMapping(value = "/s")
	public String search(){
		return null;
	}
	
}
