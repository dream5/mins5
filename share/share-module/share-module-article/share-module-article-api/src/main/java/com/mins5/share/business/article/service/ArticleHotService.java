/**
 * 
 */
package com.mins5.share.business.article.service;

import com.mins5.share.business.article.domain.ArticleHot;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月14日
 */
public interface ArticleHotService {

	/**
	 * 新增
	 * @author zhoutian
	 * @since 2014年3月14日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleHot> saveArticleHot(ArticleHot articleHot);
	
	/**
	 * 根据ID删除
	 * @author zhoutian
	 * @since 2014年3月14日
	 * @param articleLabelId
	 * @return
	 */
	ReturnData<VOID> deleteArticleHotById(Long hotId);
	
	/**
	 * 更新
	 * @author zhoutian
	 * @since 2014年3月14日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleHot> updateArticleHot(ArticleHot articleHot);
	
	/**
	 * 根据ID查询
	 * @author zhoutian
	 * @since 2014年3月14日
	 * @param labelId
	 * @return
	 */
	ReturnData<ArticleHot> findArticleHotById(Long hotId);
}
