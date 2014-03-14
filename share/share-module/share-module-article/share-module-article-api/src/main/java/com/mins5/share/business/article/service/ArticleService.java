/**
 * 
 */
package com.mins5.share.business.article.service;

import java.util.List;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
public interface ArticleService {
	
	/**
	 * 查询文章分类
	 * @author zhanglin
	 * @since 20140312 13:23
	 * @return
	 */
	ReturnData<List<ArticleKind>> findAllArticleKind();
	
	/**
	 * 新增
	 * @author zhoutian
	 * @since 2014年3月12日
	 * @param articleKind
	 * @return
	 */
	ReturnData<ArticleKind> saveArticleKind(ArticleKind articleKind);
	
	/**
	 * 根据ID删除
	 * @author zhoutian
	 * @since 2014年3月12日
	 * @param articleKindId
	 * @return
	 */
	ReturnData<VOID> deleteArticleKindById(Long articleKindId);
	
	/**
	 * 更新
	 * @author zhoutian
	 * @since 2014年3月12日
	 * @param articleKind
	 * @return
	 */
	ReturnData<ArticleKind> updateArticleKind(ArticleKind articleKind);
	
	/**
	 * 根据ID查询
	 * @author zhoutian
	 * @since 2014年3月12日
	 * @param articleKindId
	 * @return
	 */
	ReturnData<ArticleKind> findArticleKindById(Long articleKindId);
	
	/**
	 * 保存文章
	 * @author zhoutian
	 * @since 2014年3月14日
	 * @param article
	 * @return
	 */
	ReturnData<Article> saveArticle(Article article);
	
	
	/**
	 * 查询随机文章
	 * @param count 篇数
	 * @return
	 */
	ReturnData<List<Article>> findRandomArticle(int count);

}
