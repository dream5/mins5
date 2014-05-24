package com.mins5.share.business.article.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mins5.share.business.article.domain.ArticleKind;
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
	 * <p>根据拼音查询文章分类</p>
	 * @param kind 分类拼音
	 * @return
	 */
	ArticleKind findArticleKindByPinyin(String kind);

}
