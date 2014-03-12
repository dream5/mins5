package com.mins5.share.web.controller.index;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnData;
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

}
