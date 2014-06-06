package com.mins5.share.capture.util;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 解析HTML超类
 * 
 * @author zhanglin
 * @since 2014年6月6日
 */
public abstract class BaseParseUtil {

	/**
	 * 根据样式属性获取内容
	 * 
	 * @param html 目标HTML代码片段
	 * @param className class属性对应值
	 * @param charset 解析编码
	 * @return
	 * 
	 * @author zhanglin
	 * @since 2014年6月6日
	 */
	@SuppressWarnings("rawtypes")
	protected String parseHtmlByClassName(String html, String className, String charset) {
		String result = "";
		Document doc = Jsoup.parse(html, charset);
		Elements element = doc.getElementsByClass(className);
		Iterator iterator = element.iterator();
		while (iterator.hasNext()) {
			result = result + ((Element) iterator.next()).html().toString();
		}
		return result;
	}

}
