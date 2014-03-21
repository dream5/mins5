/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mins5.share.business.article.dao.ArticleDao;
import com.mins5.share.business.article.dao.ArticleKindDao;
import com.mins5.share.business.article.dao.ArticleKindRelDao;
import com.mins5.share.business.article.dao.ArticleLabelRelDao;
import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.domain.ArticleKindRel;
import com.mins5.share.business.article.domain.ArticleLabelRel;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
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
	
	@Autowired
	private ArticleLabelRelDao articleLabelRelDao;
	
	@Autowired
	private ArticleKindRelDao articleKindRelDao;
	
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

	@Override
	public ReturnData<ArticleLabelRel> saveArticleLabelRel(
			ArticleLabelRel articleLabelRel) {
		ReturnData<ArticleLabelRel> returnData = new ReturnData<ArticleLabelRel>();
		try {
			if(checkSaveArticleLabelRel(articleLabelRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = articleLabelRelDao.save(articleLabelRel);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(articleLabelRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkSaveArticleLabelRel(ArticleLabelRel articleLabelRel) {
		if(articleLabelRel == null) {
			return false;
		}
		if(articleLabelRel.getArticleId() == null) {
			return false;
		}
		if(articleLabelRel.getArticleLabelId() == null) {
			return false;
		}
		if(articleLabelRel.getCareteTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleLabelRelById(Long articleLabelRelId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if(articleLabelRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleLabelRelDao.deleteById(articleLabelRelId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleLabelRel> updateArticleLabelRel(
			ArticleLabelRel articleLabelRel) {
		ReturnData<ArticleLabelRel> returnData = new ReturnData<ArticleLabelRel>();
		try {
			if(!checkUpdateArticleLabelRel(articleLabelRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleLabelRelDao.update(articleLabelRel);
			returnData.setResultData(articleLabelRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkUpdateArticleLabelRel(ArticleLabelRel articleLabelRel) {
		if(articleLabelRel == null) {
			return false;
		}
		if(articleLabelRel.getArticleLabelRelId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleLabelRel> findArticleLabelRelById(
			Long articleLabelRelId) {
		ReturnData<ArticleLabelRel> returnData = new ReturnData<ArticleLabelRel>();
		try {
			if(articleLabelRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleLabelRel articleLabelRel = articleLabelRelDao.findById(articleLabelRelId);
			returnData.setResultData(articleLabelRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleKindRel> saveArticleKindRel(
			ArticleKindRel articleKindRel) {
		ReturnData<ArticleKindRel> returnData = new ReturnData<ArticleKindRel>();
		try {
			if(checkSaveArticleKindRel(articleKindRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = articleKindRelDao.save(articleKindRel);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(articleKindRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkSaveArticleKindRel(ArticleKindRel articleKindRel) {
		if(articleKindRel == null) {
			return false;
		}
		if(articleKindRel.getAdminId() == null) {
			return false;
		}
		if(articleKindRel.getArticleId() == null) {
			return false;
		}
		if(articleKindRel.getArticleKindId() == null) {
			return false;
		}
		if(articleKindRel.getCreateTime() == null) {
			return false;
		}
		if(articleKindRel.getStatus() == null) {
			return false;
		}
		if(articleKindRel.getUpdateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleKindRelById(Long articleKindRelId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if(articleKindRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindRelDao.deleteById(articleKindRelId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleKindRel> updateArticleKindRel(
			ArticleKindRel articleKindRel) {
		ReturnData<ArticleKindRel> returnData = new ReturnData<ArticleKindRel>();
		try {
			if(!checkUpdateArticleKindRel(articleKindRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindRelDao.update(articleKindRel);
			returnData.setResultData(articleKindRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkUpdateArticleKindRel(ArticleKindRel articleKindRel) {
		if(articleKindRel == null) {
			return false;
		}
		if(articleKindRel.getArticleKindRelId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleKindRel> findArticleKindRelById(
			Long articleKindRelId) {
		ReturnData<ArticleKindRel> returnData = new ReturnData<ArticleKindRel>();
		try {
			if(articleKindRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleKindRel articleKindRel = articleKindRelDao.findById(articleKindRelId);
			returnData.setResultData(articleKindRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public int findAllArticlesCount() {
		return articleDao.findAllArticlesCount();
	}

	@Override
	public ReturnPageData<List<Article>> findArticleByCondition(
			String kindPinYin, int currentPage, int onePageSize) {
		ReturnPageData<List<Article>> returnPageData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			//没有处理查询条件
			int totalResults = articleDao.findAllArticlesCount();
			if (totalResults>0) {
				int startRow = returnPageData.getStartRow();
				List<Article> articles = articleDao.findArticleByCondition(startRow, onePageSize);
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
	public int findArticlesByKindPinyinCount(String kindPinyin) {
		return articleDao.findArticlesByKindPinyinCount(kindPinyin);
	}

	@Override
	public ReturnPageData<List<Article>> findArticlesByKindPinyin(
			String kindPinYin, int currentPage, int onePageSize) {
		ReturnPageData<List<Article>> returnPageData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			int totalResults = articleDao.findArticlesByKindPinyinCount(kindPinYin);
			if (totalResults>0) {
				int startRow = returnPageData.getStartRow();
				List<Article> articles = articleDao.findArticlesByKindPinyin(kindPinYin,startRow, onePageSize);
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
}
