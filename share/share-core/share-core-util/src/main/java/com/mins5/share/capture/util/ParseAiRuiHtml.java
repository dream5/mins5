package com.mins5.share.capture.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

/**
 * 解析艾瑞网HTML
 * 
 * @author zhanglin
 * @since 2014年6月6日
 */
public class ParseAiRuiHtml extends BaseParseUtil {

	private final static Log log = LogFactory.getLog(ParseAiRuiHtml.class);

	// 解析HTML，返回文章实例
	public Article getArticleByParseHtml(String html) {
		Article article = new Article();
		article.setArticleTitle(getAiruiArticleTitile(html));
		String[] author = getAiruiArticleAuthor(html);
		article.setArticleAuthor(author[1].trim());
		article.setArticleFrom(author[0].trim());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtils.isEmpty(author[2])) {
			try {
				article.setCreateTime(sdf.parse(author[2].trim()));
			} catch (ParseException e) {
				article.setCreateTime(new Date());
				log.error("解析发表时间异常：[" + e.toString() + "]");
			}
		}
		article.setIsOriginal("0");
		article.setStatus("0");
		article.setArticleContent(getAiruiArticleContent(html));
		return article;

	}

	// 获取文章标题
	private String getAiruiArticleTitile(String html) {
		return parseHtmlByClassName(html, "content_title", "iso-8859-1");
	}

	// 获取文章来源，发表时间，作者等信息
	private String[] getAiruiArticleAuthor(String html) {
		String[] result = new String[3];
		try {
			String temp = parseHtmlByClassName(html, "content_titleinfoa", "iso-8859-1");
			temp = temp.toLowerCase().replace("<p>", "").replace("</p>", "");
			temp = temp.replace("<span>", "").replace("</span>", "##");
			result = temp.split("##");
			result[0] = result[0].replace("来源：", "");
			result[1] = result[1].replace("作者：", "");
			result[2] = result[2].replace("时间：", "");
		} catch (Exception e) {
			log.error("解析文章来源，发表时间，作者等信息异常：[" + e.toString() + "]");
			result = new String[3];
		}
		return result;
	}

	// 获取文章内容
	private String getAiruiArticleContent(String html) {
		html = html.replace("content_Article a_width", "mycontent");
		String result = parseHtmlByClassName(html, "mycontent", "iso-8859-1");
		String introduction = parseHtmlByClassName(html, "review", "iso-8859-1");
		result = result.replace(introduction, "");// 去除导读部分
		return result;
	}

	public static void main(String[] args) {
		String html = "<div class='content_box'>"
				+ "<h1 class='content_title' align='center'>马云：公司最难过的关是创始人的境界</h1>"
				+ " <div class='content_titleinfoa'><span>来源：创事记</span><span>作者：马云</span><span>2014-5-23 9:35:27</span><span>行业：<a href='http://news.iresearch.cn/lists/oec/'>电子商务</a></span></div>"
				+ "<div id='con_div' class='content_Article a_width'>"
				+ "<div class='review'><strong>导读：</strong>绝大部分公司创始者天性害怕失去对公司的控制权，这无可厚非，因为各种不安全感总是围绕着创业者，对未来的不安全感是他们成功的主要因素之一。尤其是越成功的创始老板会越来越有莫名的担心。</div>"
				+ " <!--content_start-->"
				+ "<a href='http://ec.iresearch.cn/' target='_blank'></a><p style='text-align:center;'><img src='http://pic.iresearch.cn/news/201405/888d60cb-c931-402e-9314-18fc8e573bc8.jpg' title='dgsg.jpg' style='border-top-style:none;border-right-style:none;border-bottom-style:none;border-left-style:none;padding:0px;margin:0px;' /></p><p>　　绝大部分<a href='http://www.iresearch.cn/search/gongsi/' target='_blank'>公司</a>创始者天性害怕失去对公司的控制权，这无可厚非，因为各种不安全感总是围绕着<a href='http://www.iresearch.cn/search/chuangye/' target='_blank'>创业</a>者，对未来的不安全感是他们成功的主要因素之一。尤其是越成功的创始老板会越来越有莫名的担心。</p><p>　　创始人对公司的控制主要有两大误区。1。总认为自己比公司里任何人都爱这家公司，最了解这家公司。2。觉得只有通过控制股份来控制公司的决定权。</p><p>　　其实这两大误区会大大阻碍你公司的发展和成长。除非你只是想做一家小规模的家庭企业，你也许可以勉强做到长期控制。如果你希望把企业做好，做强，做大，做成基业长青的公众<a href='http://www.iresearch.cn/search/shangshi/' target='_blank'>上市</a>公司，这两条思想是致命的障碍。</p><p>　　公司的发展基本依靠引进优秀的人才，特别是比自己优秀的人才，这就需要建立各类人才共建共享的文化价值观体系，更需要引入各类<a href='http://www.iresearch.cn/search/touzizhe/' target='_blank'>投资者</a>，而股份的稀释是天经地 义的。企业领导者对自己公司的控制应该靠的是勇气，智慧，担当，领导力和公正透明的管理能力。靠股份控制下的员工多的是奴才型的，很难维系公司长久的发展。</p><p>　　公司创始人会糊涂，会老，会死，会有各种突发意外，成也创始人，败也创始人的例子比比皆是。顽固，自大，一意孤行是创始人的通病。相信直觉是他们成功的原因，但太信直觉和过度自信也是他们失败的主要原因。</p><p>　　所以保持公司基业长青的很重要的措施之一就是尽早建立接班人制度。创始人要在年富力强的时候寻找和培养接班人，这就象生孩子，你需要身体最棒的时候生而不是年老体衰的时候。年老体衰时培养接班人你的心态已经很不一样了。</p><p>　　当然光靠一个或几个接班人肯定是不行的，需要建立一套制度和文化保障体系。因为没有人是完美的，也不可能有一套制度是可以解决所有问题的，因此人，制度，文化机制的配合才有可能让你的公司持久。</p><p>　　真正保障创始人利益的不是绝对的控股权，更不是自我感觉良好的至高无上的“权威”，而是一批你悉心招聘培训出来的靠谱的人，一种健康积极的文化和透明开放有效的机制，这三者的结合才有可能的基础。</p><p>　　创始人建立的文化，制度和人才必须要严防公司未来的领导者成为资本的代理人，只讲流程的经理人，甚至是言听计从的跟班们。未来的领导者必须是以公司主人的心态做决定。所以，创始人必须是以找自己未来老板的心态和眼光去找接班人。</p><p>　　公司最难过的关是创始人的境界，胸怀和品行关。决定成功的是细节，而决定失败的是格局。真的很难。</p>"
				+ "<!--content_end-->" + "</div>";

		ParseAiRuiHtml parse = new ParseAiRuiHtml();
		parse.getArticleByParseHtml(html);
	}
}
