package com.mins5.share.capture.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * 获取线程池
 * </p>
 * 
 * @author zhanglin 2014年4月12日 下午6:51:41
 */
public class CaptureThreads extends Thread {

	private final static Log log = LogFactory.getLog(CaptureThreads.class);

	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpGet;
	private final int id;
	public static List<Article> articles = new ArrayList<Article>();

	public CaptureThreads(CloseableHttpClient httpClient, HttpGet httpGet, int id) {
		this.httpClient = httpClient;
		this.context = new BasicHttpContext();
		this.httpGet = httpGet;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			log.info("线程[" + id + "]将要访问的URL是： " + httpGet.getURI());
			CloseableHttpResponse response = httpClient.execute(httpGet, context);
			try {
				log.info("访问状态码：" + response.getStatusLine().getStatusCode());
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String html = EntityUtils.toString(entity);
						html = new String(html.getBytes("iso-8859-1"), "GB2312");
						Article article = new Article();
						if (httpGet.getURI().toString().indexOf("yixieshi") > 0) {
							article = ParseHtmlUtil.parseArticleFromHtml(html);
						} else {
							ParseAiRuiHtml airui = new ParseAiRuiHtml();
							article = airui.getArticleByParseHtml(html);
						}
						articles.add(article);
					}
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			log.error("线程[" + id + "]抓取数据异常：[" + e.toString() + "]");
		}
	}
}
