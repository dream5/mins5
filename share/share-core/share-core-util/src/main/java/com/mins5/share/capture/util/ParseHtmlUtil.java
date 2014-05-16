package com.mins5.share.capture.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <p>
 * 解析互联网一些事网站的HTML
 * </p>
 * 
 * @author zhanglin 2014年4月12日 下午8:45:54
 */
public class ParseHtmlUtil {

	private final static Log log = LogFactory.getLog(ParseHtmlUtil.class);

	/**
	 * <p>
	 * 解析HTMl封装成对象
	 * </p>
	 * 
	 * @param html
	 * @return
	 */
	public static Article parseArticleFromHtml(String html) {

		Article article = new Article();
		try {
			String articleHtml = parseHtmlByClassName(html, "artCon");
			String headHtml = parseHtmlByClassName(html, "heading");
			// todo 临时使用替换去掉抓取的错误,后期文章标题抓取要修改
			headHtml = headHtml.replace("延伸阅读关键字热门推荐最新文章热门标签编辑推荐还可以这样关注我们互联网新闻交互设计产品运营电子商务互联网的一些事订阅/投稿关注我们客户端", "");
			headHtml = headHtml.replace("猜你喜欢相关报道热门推荐最新文章热门标签编辑推荐还可以这样关注我们互联网新闻交互设计产品运营电子商务互联网的一些事订阅/投稿关注我们客户端", "");

			String otherHtml = parseAuthorAndSource(html, "meta txt-shadow");
			String imgHtml = parseImgUrl(html);
			String[] temp = otherHtml.split("#");
			article.setArticleContent(articleHtml);
			article.setArticleTitle(headHtml);
			article.setArticleUrl(imgHtml);
			article.setIsOriginal("0");
			article.setStatus("1");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if ("无".equals(temp[0])) {
				article.setCreateTime(new Date());
			} else {
				article.setCreateTime(format.parse(temp[0]));
			}
			article.setArticleFrom(temp[1].trim());
			article.setArticleAuthor(temp[2].trim());
		} catch (ParseException e) {
			log.error("解析HTML异常：[" + e.toString() + "]");
			article = null;
		}
		return article;
	}

	/**
	 * <p>
	 * 解析顶部图片
	 * </p>
	 */
	public static String parseImgUrl(String html) {
		String link = "";
		try {
			Document doc = Jsoup.parse(html, "iso-8859-1");
			Element masthead = doc.select("center > img[src]").first();
			if (masthead == null || "".equals(masthead)) {
				masthead = doc.select("p > img[src]").first();
			}
			link = masthead.attr("src");
			if (!link.startsWith("http")) {
				link = "http://www.yixieshi.com" + link;
			}
		} catch (Exception e) {
			log.error("解析顶部图片异常：[" + e.toString() + "]");
		}
		return link;
	}

	/**
	 * <p>
	 * 根据className查询HTML片段
	 * </p>
	 * 
	 * @param html
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String parseHtmlByClassName(String html, String className) {
		String result = "";
		Document doc = Jsoup.parse(html, "iso-8859-1");
		Elements element = doc.getElementsByClass(className);
		Iterator iterator = element.iterator();
		while (iterator.hasNext()) {
			result = result + ((Element) iterator.next()).html().toString();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static String parseAuthorAndSource(String html, String className) {
		String result = "";
		String time = "";
		String source = "";
		String author = "";
		try {
			html = html.replace(className, "mytag");// 带空格的class，如meta
													// txt-shadow查找不到，先替换再查找
			Document doc = Jsoup.parse(html, "iso-8859-1");
			Elements element = doc.getElementsByClass("mytag");
			Iterator iterator = element.iterator();
			while (iterator.hasNext()) {
				result = result + ((Element) iterator.next()).html().toString();
			}
			// 特殊处理
			result = result.toLowerCase().replace("<p>", "").replace("</p>", "");
			result = result.replace("<span>", "").replace("</span>", "##");
			String[] temp = result.split("##");
			time = temp[0].replace("时间：", "");
			source = temp[1].replace("来源：", "");
			author = temp[2].replace("作者：", "");
		} catch (Exception e) {
			log.error("解析作者信息异常：[" + e.toString() + "]");
			time = "无";
			source = "网络";
			author = "佚名";
		}
		return time + "#" + source + "#" + author;
	}

	public static void main(String[] args) {
		/*
		 * String s =
		 * "<span>时间：2014-04-03 22:20</span> <span>来源：<a href=\"http://www.managershare.com/2014/03/18/the-4-hour-workweek/\" target=\"_blank\">经理人分享</a></span> <span>作者：提摩西·费里斯</span>"
		 * ; String tString = s.replace("<span>", "").replace("</span>", "##");
		 * //System.out.println(tString); String [] strings =
		 * tString.split("##"); String time = strings[0].replace("时间：", "");
		 * String source = strings[1].replace("来源：", ""); String author =
		 * strings[2].replace("作者：", ""); System.out.println(time.trim());
		 * System.out.println(source.trim()); System.out.println(author.trim());
		 */

		String s = "<center><img alt=\"\" src=\"/uploads/allimg/140403/83-14040322163GF.png\" style=\"width: 542px; height: 344px;\" /></center> ";
		Document doc = Jsoup.parse(s);
		Element masthead = doc.select("center > img[src]").first();
		String link = masthead.attr("src");
		System.out.println("link===" + link);
	}

}
