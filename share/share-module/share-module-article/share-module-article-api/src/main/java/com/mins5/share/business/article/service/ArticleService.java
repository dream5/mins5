/**
 * 
 */
package com.mins5.share.business.article.service;

import java.util.List;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.domain.ArticleKindRel;
import com.mins5.share.business.article.domain.ArticleLabelRel;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
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
	
	/**
	 * 新增
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleLabelRel
	 * @return
	 */
	ReturnData<ArticleLabelRel> saveArticleLabelRel(ArticleLabelRel articleLabelRel);
	
	/**
	 * 根据ID删除
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleLabelId
	 * @return
	 */
	ReturnData<VOID> deleteArticleLabelRelById(Long articleLabelRelId);
	
	/**
	 * 更新
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleLabelRel> updateArticleLabelRel(ArticleLabelRel articleLabelRel);
	
	/**
	 * 根据ID查询
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param labelId
	 * @return
	 */
	ReturnData<ArticleLabelRel> findArticleLabelRelById(Long articleLabelRelId);
	
	/**
	 * 新增
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleKindRel
	 * @return
	 */
	ReturnData<ArticleKindRel> saveArticleKindRel(ArticleKindRel articleKindRel);
	
	/**
	 * 根据ID删除
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleKindRelId
	 * @return
	 */
	ReturnData<VOID> deleteArticleKindRelById(Long articleKindRelId);
	
	/**
	 * 更新
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleKindRel
	 * @return
	 */
	ReturnData<ArticleKindRel> updateArticleKindRel(ArticleKindRel articleKindRel);
	
	/**
	 * 根据ID查询
	 * @author zhoutian
	 * @since 2014年3月15日
	 * @param articleKindRelId
	 * @return
	 */
	ReturnData<ArticleKindRel> findArticleKindRelById(Long articleKindRelId);
	
	/**
	 * 查询文章总数
	 * @return
	 */
	int findAllArticlesCount();
	
	/**
	 * 根据条件查询
	 * @param kindPinYin
	 * @param currentPage
	 * @param onePageSize
	 * @return
	 */
	ReturnPageData<List<Article>> findArticleByCondition(String kindPinYin, int currentPage, int onePageSize);

	
	/**
	 * 根据分类拼音查询文章总数
	 * @param kindPinyin 分类全拼
	 * @return
	 */
	int findArticlesByKindPinyinCount(String kindPinyin);
	
	/**
	 *  根据分类拼音查询文章
	 * @param kindPinYin
	 * @param currentPage
	 * @param onePageSize
	 * @return
	 */
	ReturnPageData<List<Article>> findArticlesByKindPinyin(String kindPinYin,int currentPage, int onePageSize);

	/**
	 * 根据文章ID查询文章
	 * @param id
	 * @return
	 */
	ReturnData<Article> findArticleById(long id);
	
}
