/**
 * 
 */
package com.mins5.share.business.article.dao;

import java.util.List;

import com.mins5.share.business.article.domain.ArticleLabelRel;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014年3月15日
 */
public interface ArticleLabelRelDao extends CrudDao<Long, ArticleLabelRel> {

	/**
	 * 根据文章标签ID删除关联数据
	 * @author zhoutian
	 * @since 2014年3月31日
	 * @param articleLabelId
	 * @return
	 */
	int deleteArticleLabelRelByArticleLabelId(Long articleLabelId);
	
	/**
	 * 批量插入
	 * @author zhoutian
	 * @since 2014年4月12日
	 * @param articleLabelList
	 * @return
	 */
	int batchSaveArticleLabelRel(List<ArticleLabelRel> articleLabelRelList);
}
