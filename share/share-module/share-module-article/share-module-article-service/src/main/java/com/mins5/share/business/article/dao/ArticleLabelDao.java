/**
 * 
 */
package com.mins5.share.business.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014年3月13日
 */
public interface ArticleLabelDao extends CrudDao<Long, ArticleLabel> {

	/**
	 * 查询热门标签
	 * @param amount
	 * @return
	 */
	List<ArticleLabel> findArticleLabelByNum(@Param("amount") int amount);
	
}
