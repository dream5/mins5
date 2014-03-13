/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleHot;
import com.mins5.share.business.article.enums.ARTICLE_STATUS;
import com.mins5.share.business.article.service.ArticleHotService;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
@Transactional
public class ArticleHotServiceImplTest {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleHotService articleHotService;
	
	@Test
	public void testSaveArticleHot() {
		
		Article article = new Article();
		article.setArticleAuthor("单元测试作者");
		article.setArticleContent("文章内容");
		article.setArticleFrom("文章来源");
		article.setArticleTitle("文章标题");
		article.setArticleUrl("http://www.mins5.com");
		article.setCreateTime(new Date());
		article.setIsOriginal("1");
		article.setStatus(ARTICLE_STATUS.WAITING_CHECK);
		article.setUpdateTime(new Date());
		ReturnData<Article> saveArticleReturnData = articleService.saveArticle(article);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), saveArticleReturnData.getReturnCode());
		
		Long articleId = article.getArticleId();
		
		ArticleHot articleHot = new ArticleHot();
		articleHot.setArticleId(articleId);
		articleHot.setReprintCount(100L);
		ReturnData<ArticleHot> saveArticleHotReturnData = articleHotService.saveArticleHot(articleHot);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), saveArticleHotReturnData.getReturnCode());
	}
	
	@Test
	public void testDeleteArticleHotById() {
		ReturnData<VOID> returnData = articleHotService.deleteArticleHotById(0L);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testUpdateArticleHot() {
		ArticleHot articleHot = new ArticleHot();
		articleHot.setHotId(0L);
		articleHot.setArticleId(1L);
		articleHot.setReprintCount(100L);
		ReturnData<ArticleHot> returnData = articleHotService.updateArticleHot(articleHot);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testFindArticleHotById() {
		ReturnData<ArticleHot> returnData = articleHotService.findArticleHotById(1L);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
}
