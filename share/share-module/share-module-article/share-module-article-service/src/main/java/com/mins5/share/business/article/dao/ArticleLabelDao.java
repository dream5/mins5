/**
 * 
 */
package com.mins5.share.business.article.dao;

import java.util.Date;
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
	 * 
	 * @param amount
	 * @return
	 */
	List<ArticleLabel> findArticleLabelByNum(@Param("amount") int amount);

	/**
	 * 分页查询文章标签列表总行数
	 * 
	 * @author zhoutian
	 * @since 2014年3月22日
	 * @param labelName
	 *            标签名称
	 * @param status
	 *            标签状态
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	int findArticleLabelCountByLabelNameAndStatusAndCreateTime(
			@Param("labelName") String labelName,
			@Param("status") String status, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);

	/**
	 * 分页查询文章标签列表
	 * 
	 * @author zhoutian
	 * @since 2014年3月22日
	 * @param labelName
	 *            标签名称
	 * @param status
	 *            标签状态
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param startRow
	 *            开始行
	 * @param onePageSize
	 *            每行行数
	 * @return
	 */
	List<ArticleLabel> findArticleLabelByLabelNameAndStatusAndCreateTime(
			@Param("labelName") String labelName,
			@Param("status") String status, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime, @Param("startRow") int startRow,
			@Param("onePageSize") int onePageSize);
}
