package com.mins5.share.business.article.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.common.dao.CrudDao;

/**
 * <p>文字种类</p>
 * @author zhanglin
 * @since 20140312
 */
@Service
public interface ArticleKindDao extends CrudDao<Long, ArticleKind>{
	
	List<ArticleKind> findAllArticleKind();

	/**
	 * 根据父ID查询文章类别列表
	 * @author zhoutian
	 * @since 2014年4月1日
	 * @param parentId
	 * @return
	 */
	List<ArticleKind> findArticleKindByParentId(Long parentId);
	
	/**
	 * 根据父ID删除文章类别
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param parentId
	 * @return
	 */
	int deleteArticleKindByParentId(Long parentId);
	
	/**
	 * 分页查询文章类别列表总行数
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param labelName 类别名称
	 * @param status 类别状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	int findArticleKindCountByKindNameAndStatusAndCreateTime(
			@Param("kindName") String kindName,
			@Param("status") String status, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);

	/**
	 * 分页查询文章类别列表
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param labelName 类别名称
	 * @param status 类别状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param startRow 开始行
	 * @param onePageSize 每行行数
	 * @return
	 */
	List<ArticleKind> findArticleKindByKindNameAndStatusAndCreateTime(
			@Param("kindName") String kindName,
			@Param("status") String status, @Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime, @Param("startRow") int startRow,
			@Param("onePageSize") int onePageSize);
}
