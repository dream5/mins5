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

import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.service.ArticleLabelService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext*.xml" })
@Transactional
public class ArticleLabelServiceImplTest {

	@Autowired
	private ArticleLabelService articleLabelService;

	@Test
	public void testSaveArticleLabel() {
		ArticleLabel articleLabel = new ArticleLabel();
		articleLabel.setCreateTime(new Date());
		articleLabel.setLabelName("单元测试");
		articleLabel.setStatus("1");
		ReturnData<ArticleLabel> returnData = articleLabelService
				.saveArticleLabel(articleLabel);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(),
				returnData.getReturnCode());
	}

	@Test
	public void testDeleteArticleLabelById() {
		ReturnData<VOID> returnData = articleLabelService
				.deleteArticleLabelById(0L);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(),
				returnData.getReturnCode());
	}

	@Test
	public void testUpdateArticleLabel() {
		ArticleLabel articleLabel = new ArticleLabel();
		articleLabel.setLabelId(0L);
		articleLabel.setCreateTime(new Date());
		articleLabel.setLabelName("单元测试修改");
		articleLabel.setStatus("0");
		ReturnData<ArticleLabel> returnData = articleLabelService
				.updateArticleLabel(articleLabel);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(),
				returnData.getReturnCode());
	}

	@Test
	public void testFindArticleLabelById() {
		ReturnData<ArticleLabel> returnData = articleLabelService
				.findArticleLabelById(1L);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(),
				returnData.getReturnCode());
	}

	@Test
	public void testFindArticleLabelByLabelNameAndStatusAndCreateTime() {
		String labelName = "test";
		String status = "1";
		Date beginTime = new Date();
		Date endTime = new Date();
		int currentPage = 1;
		int onePageSize = 10;
		ReturnPageData<List<ArticleLabel>> returnData = articleLabelService
				.findArticleLabelByLabelNameAndStatusAndCreateTime(labelName,
						status, beginTime, endTime, currentPage, onePageSize);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(),
				returnData.getReturnCode());
	}
}
