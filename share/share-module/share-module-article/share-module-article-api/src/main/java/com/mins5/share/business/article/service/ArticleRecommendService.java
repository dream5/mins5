package com.mins5.share.business.article.service;

import java.util.List;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.common.service.ReturnData;

/**
 * 文章推荐接口
 * @author zhanglin
 * @since 20140317
 */
public interface ArticleRecommendService {
	
	/**
	 * 查询推荐文章
	 * @param position 推荐位置 
	 * @param num 推荐数量
	 * @return
	 */
	ReturnData<List<Article>> findRecommendByPositionAndCount(RECOMMEND_POSITION position,int num);

}
