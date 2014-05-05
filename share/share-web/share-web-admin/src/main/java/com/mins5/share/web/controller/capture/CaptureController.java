package com.mins5.share.web.controller.capture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.web.controller.article.ArticleController;

/**
 * <p>文章抓取</p>
 * @author zhanglin
 * 2014年5月5日 下午10:38:44
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/capture")
public class CaptureController {
	
	private static final Log log = LogFactory.getLog(ArticleController.class);
	
	@RequestMapping(value = "/init")
	public String init(){
		return "capture/capture_add";
	}

}
