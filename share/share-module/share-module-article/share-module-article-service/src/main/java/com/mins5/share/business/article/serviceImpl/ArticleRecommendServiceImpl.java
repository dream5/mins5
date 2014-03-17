package com.mins5.share.business.article.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.dao.ArticleRecommendDao;
import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleRecommendService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
/**
 * 
 * @author zhanglin
 * @since 20140317
 */
@Service
public class ArticleRecommendServiceImpl implements ArticleRecommendService{
	
	private static final Log log = LogFactory.getLog(ArticleRecommendServiceImpl.class);
	
	@Autowired
	private ArticleRecommendDao recommendDao;
	
	@Override
	public ReturnData<List<Article>> findRecommendByPositionAndCount(int position,
			int num) {
		log.info("查询推荐文章开始...");
		ReturnData<List<Article>> returnData = new ReturnData<List<Article>>();
		try {
			List<Article> Articles = recommendDao.findRecommendByPositionAndCount(position, num);
			returnData.setResultData(Articles);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

}
