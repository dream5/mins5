/**
 * 
 */
package com.mins5.share.business.article.dao;

import com.mins5.share.business.article.domain.ArticleKindRel;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014年3月15日
 */
public interface ArticleKindRelDao extends CrudDao<Long, ArticleKindRel> {

	/**
	 * 根据文章类别ID删除关联数据
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param articleKindId
	 * @return
	 */
	int deleteArticleKindRelByArticleKindId(Long articleKindId);
}
