package com.mins5.share.business.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleRecommend;
import com.mins5.share.common.dao.CrudDao;

/**
 * 
 * @author zhanglin
 * @since 20140317
 */
public interface ArticleRecommendDao extends CrudDao<Long, ArticleRecommend> {

	List<Article> findRecommendByPositionAndCount(
			@Param("position") int position, @Param("num") int num);
}
