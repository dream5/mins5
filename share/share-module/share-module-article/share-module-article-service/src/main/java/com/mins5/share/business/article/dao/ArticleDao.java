/**
 * 
 */
package com.mins5.share.business.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
public interface ArticleDao extends CrudDao<Long, Article>{
	
	/**
	 * 查询随机文章
	 * @param count 查询几篇
	 * @return
	 */
	List<Article> findRandomArticle(@Param("amount") Integer amount);

}
