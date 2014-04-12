/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
@Transactional
public class ArticleServiceImplTest {
	
	@Autowired
	private ArticleService articleService;
	
	@Test
	public void testFindAllArticleKind() {
		ReturnData<List<ArticleKind>> returnData = articleService.findAllArticleKind();
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testSaveArticleKind() {
		ArticleKind articleKind = new ArticleKind();
		articleKind.setAdminId(1L);
		articleKind.setCreateTime(new Date());
		articleKind.setKindName("测试类别");
		articleKind.setParentKindId(0L);
		articleKind.setStatus("1");
		articleKind.setUpdateTime(new Date());
		ReturnData<ArticleKind> returnData = articleService.saveArticleKind(articleKind);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testDeleteArticleKindById() {
		ReturnData<VOID> returnData = articleService.deleteArticleKindById(0L);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testUpdateArticleKind() {
		ArticleKind articleKind = new ArticleKind();
		articleKind.setArticleKindId(0L);
		articleKind.setAdminId(2L);
		articleKind.setCreateTime(new Date());
		articleKind.setKindName("测试类别修改");
		articleKind.setParentKindId(1L);
		articleKind.setStatus("0");
		articleKind.setUpdateTime(new Date());
		ReturnData<ArticleKind> returnData = articleService.updateArticleKind(articleKind);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testFindArticleKindById() {
		ReturnData<ArticleKind> returnData = articleService.findArticleKindById(1L);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
}
