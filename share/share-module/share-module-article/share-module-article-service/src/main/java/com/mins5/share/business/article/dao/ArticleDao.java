/**
 * 
 */
package com.mins5.share.business.article.dao;

import java.util.Date;
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

	
	/**
	 * 根据分类拼音查询文章总数
	 * @param kindPinyin 分类全拼
	 * @return
	 */
	int findArticlesByKindPinyinCount(@Param("kindPinyin") String kindPinyin);
	
	/**
	 *  根据分类拼音查询文章
	 * @param kindPinYin
	 * @param currentPage
	 * @param onePageSize
	 * @return
	 */
	List<Article> findArticlesByKindPinyin(@Param("kindPinyin")String kindPinYin,@Param("startRow") int startRow, @Param("onePageSize") int onePageSize);
	
	
	
	/**
	 * 查询文章总数
	 * @return 
	 */
	int findArticlesCountByTitle(@Param("title") String title);
	
	/**
	 * 根据条件查询文章
	 * @return
	 */
	List<Article> findArticlesByTitle(@Param("title")String title,@Param("startRow") int startRow, @Param("onePageSize") int onePageSize);
	
	/**
	 * 后台查询文章列表
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @return
	 */
	int findArticlesCountByArticleTitleAndStatusAndCreateTimeAndIsOriginal(
			@Param("articleTitle") String articleTitle,
			@Param("status") String status,
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("isOriginal") String isOriginal);
	
	/**
	 * 后台查询文章列表
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @param startRow 开始行
	 * @param onePageSize 每页行数
	 * @return
	 */
	List<Article> findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(
			@Param("articleTitle") String articleTitle,
			@Param("status") String status,
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("isOriginal") String isOriginal, 
			@Param("startRow") int startRow,
			@Param("onePageSize") int onePageSize);
	
}
