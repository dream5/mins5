/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mins5.share.business.article.domain.ArticleHot;
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
//		ArticleHot articleHot = new ArticleHot();
//		articleHot.setArticleId(0L);
//		articleHot.setReprintCount(100L);
//		ReturnData<ArticleHot> returnData = articleHotService.saveArticleHot(articleHot);
//		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
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
