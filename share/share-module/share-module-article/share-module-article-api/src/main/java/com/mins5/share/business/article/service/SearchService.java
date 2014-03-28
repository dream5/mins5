package com.mins5.share.business.article.service;

import java.util.List;

import com.mins5.share.business.article.domain.Article;
import com.mins5.share.common.service.ReturnPageData;

/**
 * <p>搜索</p>
 * @author zhanglin
 * @since 20140328
 */
public interface SearchService {
	
	/**
	 * 综合查询总数
	 * @param key
	 * @return
	 */
	int complexSearchArticleDataCount(String key);
	
	/**
	 * 综合查询
	 * @param key
	 * @return
	 */
	ReturnPageData<List<Article>> complexSearchArticleData(String key,int currentPage, int onePageSize);

}
