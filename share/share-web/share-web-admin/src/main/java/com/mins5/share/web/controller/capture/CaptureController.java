package com.mins5.share.web.controller.capture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mins5.share.capture.util.Article;
import com.mins5.share.capture.util.Capture;
import com.mins5.share.capture.util.CaptureThreads;
import com.mins5.share.capture.util.DBUtil;
import com.mins5.share.util.ApplicationContextUtil;
import com.mins5.share.util.JsonUtils;
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
	
	/**
	 * 开始抓取
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/beginCapture")
	public void beginCapture(HttpServletResponse response,String captureUrl){
		if (!StringUtils.isEmpty(captureUrl)) {
			try {
				String [] urls =  captureUrl.trim().split(";");
				Capture tools = new Capture();
				List<String> target = new ArrayList<String>();
				Collections.addAll(target, urls);
				if (tools.beginCapture(target)) {
					//数据入库
					ComboPooledDataSource dataSources = (ComboPooledDataSource)ApplicationContextUtil.getBean("dataSource");
					DBUtil dbUtil = new DBUtil();
					dbUtil.batchInsertBean(CaptureThreads.articles,dataSources.getConnection());
					CaptureThreads.articles = new ArrayList<Article>();//必须，否则出现静态变量出现并发问题
					JsonUtils.write("已成功完成抓取！", response);
				}
			} catch (Exception e) {
				log.error("抓取文章异常：["+e.toString()+"]");
				JsonUtils.write("抓取错误！", response);
			}
		}else{
			JsonUtils.write("参数错误！", response);
		}
	}

}
