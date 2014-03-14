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
import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

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

	@Override
	public ReturnData<ArticleKind> saveArticleKind(ArticleKind articleKind) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if(!checkSaveArticleKind(articleKind)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode()) ;
				return returnData;
			}
			int count = articleKindDao.save(articleKind);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode()) ;
				return returnData;
			}
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkSaveArticleKind(ArticleKind articleKind) {
		if(articleKind == null) {
			return false;
		}
		if(articleKind.getAdminId() == null) {
			return false;
		}
		if(articleKind.getCreateTime() == null) {
			return false;
		}
		if(articleKind.getKindName() == null) {
			return false;
		}
		if(articleKind.getStatus() == null) {
			return false;
		}
		if(articleKind.getUpdateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleKindById(Long articleKindId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if(articleKindId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindDao.deleteById(articleKindId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleKind> updateArticleKind(ArticleKind articleKind) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if(!checkUpdateArticleKind(articleKind)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindDao.update(articleKind);
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkUpdateArticleKind(ArticleKind articleKind) {
		if(articleKind == null) {
			return false;
		}
		if(articleKind.getArticleKindId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleKind> findArticleKindById(Long articleKindId) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if(articleKindId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleKind articleKind = articleKindDao.findById(articleKindId);
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<Article> saveArticle(Article article) {
		ReturnData<Article> returnData = new ReturnData<Article>();
		try {
			if(!checkSaveArticle(article)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode()) ;
				return returnData;
			}
			int count = articleDao.save(article);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode()) ;
				return returnData;
			}
			returnData.setResultData(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveArticle(Article article) {
		if(article == null) {
			return false;
		}
		if(article.getArticleAuthor() == null) {
			return false;
		}
		if(article.getArticleContent() == null) {
			return false;
		}
		if(article.getArticleTitle() == null) {
			return false;
		}
		if(article.getCreateTime() == null) {
			return false;
		}
		if(article.getIsOriginal() == null) {
			return false;
		}
		if(article.getStatus() == null) {
			return false;
		}
		if(article.getUpdateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<List<Article>> findRandomArticle(int amount) {
		log.info("查询随机文章开始...");
		ReturnData<List<Article>> returnData = new ReturnData<List<Article>>();
		try {
			List<Article> Articles = articleDao.findRandomArticle(amount);
			returnData.setResultData(Articles);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
}
