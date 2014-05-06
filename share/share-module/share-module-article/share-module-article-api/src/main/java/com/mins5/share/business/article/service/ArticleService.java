/**
 * 
 */
package com.mins5.share.business.article.service;

import java.util.Date;
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
	 * 删除文章类别
	 * <p/>关联删除文章类别关联表数据
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param articleKindId
	 * @return
	 */
	ReturnData<VOID> deleteArticleKindAndArticleKindRelByArticleKindId(Long articleKindId);
	
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
	 * 根据父ID查询文章类别列表
	 * @author zhoutian
	 * @since 2014年4月1日
	 * @param parentId
	 * @return
	 */
	ReturnData<List<ArticleKind>> findArticleKindByParentId(Long parentId);
	
	/**
	 * 保存文章
	 * @author zhoutian
	 * @since 2014年4月12日
	 * @param article
	 * @param articleKindIdList
	 * @param articleLabelIdList
	 * @return
	 */
	ReturnData<Article> saveArticle(Article article, List<Long> articleKindIdList, List<Long> articleLabelIdList);
	
	/**
	 * 根据id删除文章（逻辑删除）
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param articleId
	 * @return
	 */
	ReturnData<VOID> deleteArticleById(Long articleId);
	
	/**
	 * 发布文章
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param articleId
	 * @return
	 */
	ReturnData<VOID> publishedArticle(Long articleId);
	
	/**
	 * 下架文章
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param articleId
	 * @return
	 */
	ReturnData<VOID> removedArticle(Long articleId);
	
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
	ReturnData<Article> findArticleById(Long id);
	
	/**
	 * 后台查询文章列表
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 * @return
	 */
	ReturnPageData<List<Article>> findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(String articleTitle, String status, Date beginTime,
			Date endTime, String isOriginal, int currentPage,
			int onePageSize);
	
	/**
	 * 后台查询抓取文章列表
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param articleTitle 文章标题
	 * @param status 文章状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 * @return
	 */
	ReturnPageData<List<Article>> findAllCaptureArticlesByCondition(String articleTitle, Date beginTime,
			Date endTime, int currentPage,
			int onePageSize);
	
	
	/**
	 * 任意查询2篇不是本身的文章，作为前一篇，及后一篇
	 * @param id 本身文章ID
	 * @param pinyin 文章拼音分类
	 * @return
	 */
	ReturnData<List<Article>> findPreAndNextArticles(int id,String pinyin);
	
	
	
	
	
}
