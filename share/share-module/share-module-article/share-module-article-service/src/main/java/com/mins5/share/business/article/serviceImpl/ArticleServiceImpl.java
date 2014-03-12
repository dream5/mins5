/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.dao.ArticleDao;
import com.mins5.share.business.article.dao.ArticleKindDao;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
@Service
public class ArticleServiceImpl  implements ArticleService{
	
	private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ArticleKindDao articleKindDao;
	
	public ReturnData<List<ArticleKind>> findAllArticleKind() {
		log.info("查询文字所有分类开始...");
		ReturnData<List<ArticleKind>> returnData = new ReturnData<List<ArticleKind>>();
		try {
			List<ArticleKind> ArticleKinds = articleKindDao.findAllArticleKind();
			returnData.setResultData(ArticleKinds);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

}
