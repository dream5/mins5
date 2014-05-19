package com.mins5.share.business.article.service;

import java.util.List;
import java.util.Map;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleRecommend;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.common.service.ReturnData;

/**
 * 文章推荐接口
 * 
 * @author zhanglin
 * @since 20140317
 */
public interface ArticleRecommendService {

	/**
	 * 查询推荐文章
	 * 
	 * @param position 推荐位置
	 * @param num 推荐数量
	 * @return
	 */
	ReturnData<List<Article>> findRecommendByPositionAndCount(RECOMMEND_POSITION position, int num);

	/**
	 * 检验推荐位
	 * 
	 * @param param
	 * @return
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 */
	ReturnData<ArticleRecommend> checkRecommend(Map param);

	/**
	 * 新增
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 * @param articleKind
	 * @return
	 */
	ReturnData<ArticleRecommend> saveArticleRecommend(ArticleRecommend recomend);

	/**
	 * 更新
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 * @param articleKind
	 * @return
	 */
	ReturnData<ArticleRecommend> updateArticleRecommend(ArticleRecommend recomend);

}
