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
	
	
	/**
	 * 查询文章总数
	 * @return 
	 */
	int findAllArticlesCount();
	
	/**
	 * 根据条件查询文章
	 * @return
	 */
	List<Article> findArticleByCondition(@Param("startRow") int startRow, @Param("onePageSize") int onePageSize);

}
