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
public interface ArticleDao extends CrudDao<Long, Article> {

	/**
	 * 查询随机文章
	 * 
	 * @param count 查询几篇
	 * @return
	 */
	List<Article> findRandomArticle(@Param("amount") Integer amount);

	/**
	 * 查询文章总数
	 * 
	 * @return
	 */
	int findAllArticlesCount();

	/**
	 * 根据条件查询文章
	 * 
	 * @return
	 */
	List<Article> findArticleByCondition(@Param("startRow") int startRow, @Param("onePageSize") int onePageSize);

	/**
	 * 根据分类拼音查询文章总数
	 * 
	 * @param kindPinyin 分类全拼
	 * @return
	 */
	int findArticlesByKindPinyinCount(@Param("kindPinyin") String kindPinyin);

	/**
	 * 根据分类拼音查询文章
	 * 
	 * @param kindPinYin
	 * @param currentPage
	 * @param onePageSize
	 * @return
	 */
	List<Article> findArticlesByKindPinyin(@Param("kindPinyin") String kindPinYin, @Param("startRow") int startRow,
			@Param("onePageSize") int onePageSize);

	/**
	 * 查询文章总数
	 * 
	 * @return
	 */
	int findArticlesCountByTitle(@Param("title") String title);

	/**
	 * 根据条件查询文章
	 * 
	 * @return
	 */
	List<Article> findArticlesByTitle(@Param("title") String title, @Param("startRow") int startRow, @Param("onePageSize") int onePageSize);

	/**
	 * 后台查询文章列表
	 * 
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @return
	 */
	int findArticlesCountByArticleTitleAndStatusAndCreateTimeAndIsOriginal(@Param("articleTitle") String articleTitle,
			@Param("status") String status, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("isOriginal") String isOriginal);

	/**
	 * 后台查询文章列表
	 * 
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
	List<Article> findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(@Param("articleTitle") String articleTitle,
			@Param("status") String status, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("isOriginal") String isOriginal, @Param("startRow") int startRow, @Param("onePageSize") int onePageSize);

	/**
	 * 后台查询抓取文章总数列表
	 * 
	 * @author zhanglin
	 * @since 20140506 13:03
	 * @param articleTitle 文章标题
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	int findAllCaptureArticlesCountByCondition(@Param("articleTitle") String articleTitle, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);

	/**
	 * 后台查询抓取文章列表
	 * 
	 * @author zhanglin
	 * @since 20140506 13:04
	 * @param articleTitle 文章标题
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param startRow 开始行
	 * @param onePageSize 每页行数
	 * @return
	 */
	List<Article> findAllCaptureArticlesByCondition(@Param("articleTitle") String articleTitle, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime, @Param("startRow") int startRow, @Param("onePageSize") int onePageSize);

	/**
	 * 任意查询2篇不是本身的文章，作为前一篇及后一篇
	 * 
	 * @param id 本身文章ID
	 * @param pinyin 文章拼音分类
	 * @return
	 */
	List<Article> findPreAndNextArticles(@Param("articleId") int id, @Param("pinyin") String pinyin);

	/**
	 * 根据ID获取抓取文章
	 * 
	 * @param id
	 * @return
	 */
	Article findCaptureById(@Param("articleId") long id);

	/**
	 * 更新抓取表文章状态
	 * 
	 * @param id
	 * @param status
	 * @author zhanglin
	 * @since 2014年5月13日
	 */
	int updateCaptureArticleSts(@Param("articleId") long id, @Param("status") long status);
	
	/**
	 * <p>从临时表中清除</p>
	 * @param id
	 * @return
	 */
	int deleteTempArticleById(@Param("articleId") long id);
	
	
	/**
	 * 
	 * <p>根据标签名称查文章总数</p>
	 * @param labelName
	 * @return
	 */
	int findArticlesCountByLabelName(@Param("labelName") String labelName);
	
	/**
	 * 
	 * <p>根据标签名称查文章</p>
	 * @param labelName
	 * @param startRow
	 * @param onePageSize
	 * @return
	 */
	List<Article> findArticlesByLabelName(@Param("labelName") String labelName, @Param("startRow") int startRow, @Param("onePageSize") int onePageSize);

}
