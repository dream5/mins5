/**
 * 
 */
package com.mins5.share.business.article.service;

import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月13日
 */
public interface ArticleLabelService {

	/**
	 * 新增
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleLabel> saveArticleLabel(ArticleLabel articleLabel);
	
	/**
	 * 根据ID删除
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param articleLabelId
	 * @return
	 */
	ReturnData<VOID> deleteArticleLabelById(Long articleLabelId);
	
	/**
	 * 更新
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleLabel> updateArticleLabel(ArticleLabel articleLabel);
	
	/**
	 * 根据ID查询
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param labelId
	 * @return
	 */
	ReturnData<ArticleLabel> findArticleLabelById(Long labelId);
}
