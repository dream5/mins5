/**
 * 
 */
package com.mins5.share.business.article.service;

import java.util.List;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.common.service.ReturnData;

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

}
