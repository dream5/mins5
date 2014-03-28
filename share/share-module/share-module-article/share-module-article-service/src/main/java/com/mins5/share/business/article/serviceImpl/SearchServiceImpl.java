package com.mins5.share.business.article.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mins5.share.business.article.dao.ArticleDao;
import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.service.SearchService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnPageData;
/**
 * 
 * @author zhanglin
 * @since 20140328
 */
@Service
public class SearchServiceImpl implements SearchService {

	private static final Log log = LogFactory.getLog(SearchServiceImpl.class);
	
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public ReturnPageData<List<Article>> complexSearchArticleData(String key,int currentPage, int onePageSize) {
		log.info("综合查询开始[关键字："+key+"]开始...");
		ReturnPageData<List<Article>> returnPageData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			//暂时只实现按标题查询
			int totalResults = articleDao.findArticlesCountByTitle(key);
			if (totalResults>0) {
				int startRow = returnPageData.getStartRow();
				List<Article> articles = articleDao.findArticlesByTitle(key,startRow, onePageSize);
				if(StringUtils.isEmpty(articles)) {
					articles = Collections.emptyList();
				}
				returnPageData.setTotalResults(totalResults);
				returnPageData.setResultData(articles);
				returnPageData.setReturnCode(ReturnCode.SUCCESS.getCode());
			}
		} catch(Exception e) {
			log.error("service exception", e);
			returnPageData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnPageData;
	}

	@Override
	public int complexSearchArticleDataCount(String key) {
		return articleDao.findArticlesCountByTitle(key);
	}

}
