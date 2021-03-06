/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mins5.share.business.article.dao.ArticleDao;
import com.mins5.share.business.article.dao.ArticleKindDao;
import com.mins5.share.business.article.dao.ArticleKindRelDao;
import com.mins5.share.business.article.dao.ArticleLabelRelDao;
import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.domain.ArticleKindRel;
import com.mins5.share.business.article.domain.ArticleLabelRel;
import com.mins5.share.business.article.enums.ARTICLE_STATUS;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.exception.TransactionException;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private ArticleKindDao articleKindDao;

	@Autowired
	private ArticleLabelRelDao articleLabelRelDao;

	@Autowired
	private ArticleKindRelDao articleKindRelDao;

	@Override
	public ReturnData<List<ArticleKind>> findAllArticleKind() {
		ReturnData<List<ArticleKind>> returnData = new ReturnData<List<ArticleKind>>();
		try {
			List<ArticleKind> ArticleKinds = articleKindDao.findAllArticleKind();
			returnData.setResultData(ArticleKinds);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	@Transactional
	public ReturnData<VOID> deleteArticleKindAndArticleKindRelByArticleKindId(Long articleKindId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			this.deleteAticleKindTree(articleKindId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (TransactionException e) {
			log.error("service exception", e);
			throw new TransactionException(ReturnCode.EXCEPTION.getCode(), e);
		}
		return returnData;
	}

	@Transactional
	private void deleteAticleKindTree(Long articleKindId) {
		try {
			articleKindDao.deleteById(articleKindId);
			articleKindRelDao.deleteArticleKindRelByArticleKindId(articleKindId);
			List<ArticleKind> articleKindList = articleKindDao.findArticleKindByParentId(articleKindId);
			if (!CollectionUtils.isEmpty(articleKindList)) {
				for (ArticleKind articleKind : articleKindList) {
					this.deleteAticleKindTree(articleKind.getArticleKindId());
				}
				articleKindDao.deleteArticleKindByParentId(articleKindId);
			}
		} catch (TransactionException e) {
			log.error("service exception", e);
			throw new TransactionException(ReturnCode.EXCEPTION.getCode(), e);
		}
	}

	@Override
	public ReturnData<ArticleKind> saveArticleKind(ArticleKind articleKind) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if (!checkSaveArticleKind(articleKind)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = articleKindDao.save(articleKind);
			if (count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveArticleKind(ArticleKind articleKind) {
		if (articleKind == null) {
			return false;
		}
		if (articleKind.getAdminId() == null) {
			return false;
		}
		if (articleKind.getKindPinyin() == null) {
			return false;
		}
		if (articleKind.getCreateTime() == null) {
			return false;
		}
		if (articleKind.getKindName() == null) {
			return false;
		}
		if (articleKind.getStatus() == null) {
			return false;
		}
		if (articleKind.getUpdateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleById(Long articleId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			Article article = new Article();
			article.setArticleId(articleId);
			article.setIsValid("1");
			articleDao.update(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<VOID> publishedArticle(Long articleId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			Article article = new Article();
			article.setArticleId(articleId);
			article.setStatus(ARTICLE_STATUS.PUBLISHED);
			articleDao.update(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<VOID> removedArticle(Long articleId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			Article article = new Article();
			article.setArticleId(articleId);
			article.setStatus(ARTICLE_STATUS.REMOVED);
			articleDao.update(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<VOID> deleteArticleKindById(Long articleKindId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleKindId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindDao.deleteById(articleKindId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleKind> updateArticleKind(ArticleKind articleKind) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if (!checkUpdateArticleKind(articleKind)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindDao.update(articleKind);
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkUpdateArticleKind(ArticleKind articleKind) {
		if (articleKind == null) {
			return false;
		}
		if (articleKind.getArticleKindId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleKind> findArticleKindById(Long articleKindId) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if (articleKindId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleKind articleKind = articleKindDao.findById(articleKindId);
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<List<ArticleKind>> findArticleKindByParentId(Long parentId) {
		ReturnData<List<ArticleKind>> returnData = new ReturnData<List<ArticleKind>>();
		try {
			if (parentId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			List<ArticleKind> articleKindList = articleKindDao.findArticleKindByParentId(parentId);
			returnData.setResultData(articleKindList);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Transactional
	@Override
	public ReturnData<Article> saveArticle(Article article, List<Long> articleKindIdList, List<Long> articleLabelIdList) {
		ReturnData<Article> returnData = new ReturnData<Article>();
		try {
			if (!checkSaveArticle(article)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}

			articleDao.save(article);
			Long articleId = article.getArticleId();

			Date currentTime = new Date();

			List<ArticleKindRel> articleKindRelList = new ArrayList<ArticleKindRel>();
			for (Long articleKindId : articleKindIdList) {
				ArticleKindRel articleKindRel = new ArticleKindRel();
				articleKindRel.setAdminId(0L);
				articleKindRel.setArticleId(articleId);
				articleKindRel.setCreateTime(currentTime);
				articleKindRel.setStatus("1");
				articleKindRel.setUpdateTime(currentTime);
				articleKindRel.setArticleKindId(articleKindId);
				articleKindRelList.add(articleKindRel);
			}
			if (!CollectionUtils.isEmpty(articleKindRelList)) {
				articleKindRelDao.batchSaveArticleKindRel(articleKindRelList);
			}

			List<ArticleLabelRel> articleLabelRelList = new ArrayList<ArticleLabelRel>();
			for (Long articleLabelId : articleLabelIdList) {
				ArticleLabelRel articleLabelRel = new ArticleLabelRel();
				articleLabelRel.setArticleId(articleId);
				articleLabelRel.setLabelId(articleLabelId);
				articleLabelRel.setCreateTime(currentTime);
				articleLabelRelList.add(articleLabelRel);
			}
			if (!CollectionUtils.isEmpty(articleLabelRelList)) {
				articleLabelRelDao.batchSaveArticleLabelRel(articleLabelRelList);
			}

			returnData.setResultData(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			throw new TransactionException(ReturnCode.EXCEPTION.getCode(), e);
		}
		return returnData;
	}

	private boolean checkSaveArticle(Article article) {
		if (article == null) {
			return false;
		}
		if (article.getArticleAuthor() == null) {
			return false;
		}
		if (article.getArticleContent() == null) {
			return false;
		}
		if (article.getArticleTitle() == null) {
			return false;
		}
		if (article.getCreateTime() == null) {
			return false;
		}
		if (article.getIsOriginal() == null) {
			return false;
		}
		if (article.getStatus() == null) {
			return false;
		}
		if (article.getUpdateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<Article> updateArticle(Article article) {
		ReturnData<Article> returnData = new ReturnData<Article>();

		if (article == null || article.getArticleId() == null) {
			returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
			return returnData;
		}

		try {
			articleDao.update(article);
			returnData.setResultData(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
			return returnData;
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<List<Article>> findRandomArticle(int amount) {
		ReturnData<List<Article>> returnData = new ReturnData<List<Article>>();
		try {
			List<Article> Articles = articleDao.findRandomArticle(amount);
			returnData.setResultData(Articles);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleLabelRel> saveArticleLabelRel(ArticleLabelRel articleLabelRel) {
		ReturnData<ArticleLabelRel> returnData = new ReturnData<ArticleLabelRel>();
		try {
			if (checkSaveArticleLabelRel(articleLabelRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = articleLabelRelDao.save(articleLabelRel);
			if (count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(articleLabelRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveArticleLabelRel(ArticleLabelRel articleLabelRel) {
		if (articleLabelRel == null) {
			return false;
		}
		if (articleLabelRel.getArticleId() == null) {
			return false;
		}
		if (articleLabelRel.getLabelId() == null) {
			return false;
		}
		if (articleLabelRel.getCreateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleLabelRelById(Long articleLabelRelId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleLabelRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleLabelRelDao.deleteById(articleLabelRelId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleLabelRel> updateArticleLabelRel(ArticleLabelRel articleLabelRel) {
		ReturnData<ArticleLabelRel> returnData = new ReturnData<ArticleLabelRel>();
		try {
			if (!checkUpdateArticleLabelRel(articleLabelRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleLabelRelDao.update(articleLabelRel);
			returnData.setResultData(articleLabelRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkUpdateArticleLabelRel(ArticleLabelRel articleLabelRel) {
		if (articleLabelRel == null) {
			return false;
		}
		if (articleLabelRel.getArticleLabelRelId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleLabelRel> findArticleLabelRelById(Long articleLabelRelId) {
		ReturnData<ArticleLabelRel> returnData = new ReturnData<ArticleLabelRel>();
		try {
			if (articleLabelRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleLabelRel articleLabelRel = articleLabelRelDao.findById(articleLabelRelId);
			returnData.setResultData(articleLabelRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleKindRel> saveArticleKindRel(ArticleKindRel articleKindRel) {
		ReturnData<ArticleKindRel> returnData = new ReturnData<ArticleKindRel>();
		try {
			if (checkSaveArticleKindRel(articleKindRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = articleKindRelDao.save(articleKindRel);
			if (count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(articleKindRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveArticleKindRel(ArticleKindRel articleKindRel) {
		if (articleKindRel == null) {
			return false;
		}
		if (articleKindRel.getAdminId() == null) {
			return false;
		}
		if (articleKindRel.getArticleId() == null) {
			return false;
		}
		if (articleKindRel.getArticleKindId() == null) {
			return false;
		}
		if (articleKindRel.getCreateTime() == null) {
			return false;
		}
		if (articleKindRel.getStatus() == null) {
			return false;
		}
		if (articleKindRel.getUpdateTime() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleKindRelById(Long articleKindRelId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleKindRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindRelDao.deleteById(articleKindRelId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleKindRel> updateArticleKindRel(ArticleKindRel articleKindRel) {
		ReturnData<ArticleKindRel> returnData = new ReturnData<ArticleKindRel>();
		try {
			if (!checkUpdateArticleKindRel(articleKindRel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleKindRelDao.update(articleKindRel);
			returnData.setResultData(articleKindRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkUpdateArticleKindRel(ArticleKindRel articleKindRel) {
		if (articleKindRel == null) {
			return false;
		}
		if (articleKindRel.getArticleKindRelId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleKindRel> findArticleKindRelById(Long articleKindRelId) {
		ReturnData<ArticleKindRel> returnData = new ReturnData<ArticleKindRel>();
		try {
			if (articleKindRelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleKindRel articleKindRel = articleKindRelDao.findById(articleKindRelId);
			returnData.setResultData(articleKindRel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
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
	public ReturnPageData<List<Article>> findArticleByCondition(String kindPinYin, int currentPage, int onePageSize) {
		ReturnPageData<List<Article>> returnPageData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			// 没有处理查询条件
			int totalResults = articleDao.findAllArticlesCount();
			if (totalResults > 0) {
				int startRow = returnPageData.getStartRow();
				List<Article> articles = articleDao.findArticleByCondition(startRow, onePageSize);
				if (StringUtils.isEmpty(articles)) {
					articles = Collections.emptyList();
				}
				returnPageData.setTotalResults(totalResults);
				returnPageData.setResultData(articles);
				returnPageData.setReturnCode(ReturnCode.SUCCESS.getCode());
			}

		} catch (Exception e) {
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
	public ReturnPageData<List<Article>> findArticlesByKindPinyin(String kindPinYin, int currentPage, int onePageSize) {
		ReturnPageData<List<Article>> returnPageData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			int totalResults = articleDao.findArticlesByKindPinyinCount(kindPinYin);
			if (totalResults > 0) {
				int startRow = returnPageData.getStartRow();
				List<Article> articles = articleDao.findArticlesByKindPinyin(kindPinYin, startRow, onePageSize);
				if (StringUtils.isEmpty(articles)) {
					articles = Collections.emptyList();
				}
				returnPageData.setTotalResults(totalResults);
				returnPageData.setResultData(articles);
				returnPageData.setReturnCode(ReturnCode.SUCCESS.getCode());
			}
		} catch (Exception e) {
			log.error("service exception", e);
			returnPageData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnPageData;
	}

	@Override
	public ReturnData<Article> findArticleById(Long id) {
		ReturnData<Article> returnData = new ReturnData<Article>();
		try {
			if (StringUtils.isEmpty(id)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			Article article = articleDao.findById(id);
			returnData.setResultData(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnPageData<List<Article>> findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(String articleTitle, String status,
			Date beginTime, Date endTime, String isOriginal, int currentPage, int onePageSize) {
		ReturnPageData<List<Article>> returnData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			int count = articleDao.findArticlesCountByArticleTitleAndStatusAndCreateTimeAndIsOriginal(articleTitle, status, beginTime, endTime,
					isOriginal);
			if (count > 0) {
				int startRow = returnData.getStartRow();
				List<Article> articleList = articleDao.findArticlesByArticleTitleAndStatusAndCreateTimeAndIsOriginal(articleTitle, status, beginTime,
						endTime, isOriginal, startRow, onePageSize);

				returnData.setTotalResults(count);
				returnData.setResultData(articleList);
			}
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * 后台查询抓取文章列表
	 * 
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
	@Override
	public ReturnPageData<List<Article>> findAllCaptureArticlesByCondition(String articleTitle, Date beginTime, Date endTime, int currentPage,
			int onePageSize) {
		ReturnPageData<List<Article>> returnData = new ReturnPageData<List<Article>>(currentPage, onePageSize);
		try {
			int count = articleDao.findAllCaptureArticlesCountByCondition(articleTitle, beginTime, endTime);
			if (count > 0) {
				int startRow = returnData.getStartRow();
				List<Article> articleList = articleDao.findAllCaptureArticlesByCondition(articleTitle, beginTime, endTime, startRow, onePageSize);
				returnData.setTotalResults(count);
				returnData.setResultData(articleList);
			}
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<List<Article>> findPreAndNextArticles(int id, String pinyin) {
		ReturnData<List<Article>> returnData = new ReturnData<List<Article>>();
		try {
			if (StringUtils.isEmpty(id)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			List<Article> article = articleDao.findPreAndNextArticles(id, pinyin);
			returnData.setResultData(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * 根据文章ID查询抓取文章
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ReturnData<Article> findCaptureArticleById(Long id) {
		ReturnData<Article> returnData = new ReturnData<Article>();
		try {
			if (StringUtils.isEmpty(id)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			Article article = articleDao.findCaptureById(id);
			returnData.setResultData(article);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * 将临时表数据推送到正式表
	 * 
	 * @param id
	 * @author zhanglin
	 * @since 2014年5月13日
	 * @update 增加种类及标签处理 20140605
	 */
	@Override
	@Transactional
	public ReturnData<VOID> publishedArticleToTable(Long id, String articleKind, String articleLabel) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			Article article = articleDao.findCaptureById(id);
			if (!checkSaveArticle(article)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleDao.save(article);
			articleDao.updateCaptureArticleSts(id, 1);

			if (article.getArticleId() > 0) {
				Date currentTime = new Date();
				// 保存文章种类及标签信息
				List<ArticleKindRel> articleKindRelList = new ArrayList<ArticleKindRel>();
				String[] articleKindIdArray = articleKind.split(",");
				for (int i = 0; i < articleKindIdArray.length; i++) {
					ArticleKindRel articleKindRel = new ArticleKindRel();
					articleKindRel.setAdminId(0L);
					articleKindRel.setArticleId(article.getArticleId());
					articleKindRel.setCreateTime(currentTime);
					articleKindRel.setStatus("1");
					articleKindRel.setUpdateTime(currentTime);
					articleKindRel.setArticleKindId(Long.valueOf(articleKindIdArray[i]));
					articleKindRelList.add(articleKindRel);
				}
				if (!CollectionUtils.isEmpty(articleKindRelList)) {
					articleKindRelDao.batchSaveArticleKindRel(articleKindRelList);
				}

				String[] articleLabelIdArray = articleLabel.split(",");
				List<ArticleLabelRel> articleLabelRelList = new ArrayList<ArticleLabelRel>();

				for (int j = 0; j < articleLabelIdArray.length; j++) {
					ArticleLabelRel articleLabelRel = new ArticleLabelRel();
					articleLabelRel.setArticleId(article.getArticleId());
					articleLabelRel.setLabelId(Long.valueOf(articleLabelIdArray[j]));
					articleLabelRel.setCreateTime(currentTime);
					articleLabelRelList.add(articleLabelRel);
				}
				if (!CollectionUtils.isEmpty(articleLabelRelList)) {
					articleLabelRelDao.batchSaveArticleLabelRel(articleLabelRelList);
				}

			}

			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (TransactionException e) {
			log.error("service exception", e);
			throw new TransactionException(ReturnCode.EXCEPTION.getCode(), e);
		}
		return returnData;
	}

	/**
	 * 将临时表数据删除
	 * 
	 * @param id
	 * @author zhanglin
	 * @since 2014年5月13日
	 */
	@Override
	@Transactional
	public ReturnData<VOID> removedArticleFromTempTable(Long articleId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (articleId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleDao.deleteTempArticleById(articleId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * 根据拼音查询
	 * 
	 * @author zhanglin
	 * @since 2014年5月24日
	 * @param articleKindId
	 * @return
	 */
	@Override
	public ReturnData<ArticleKind> findArticleKindByPinyin(String kind) {
		ReturnData<ArticleKind> returnData = new ReturnData<ArticleKind>();
		try {
			if (kind == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleKind articleKind = articleKindDao.findArticleKindByPinyin(kind);
			returnData.setResultData(articleKind);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * <p>
	 * 根据标签搜索文章
	 * </p>
	 * 
	 * @param labelName 标签名称
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@Override
	public ReturnPageData<List<Article>> findArticlesByLabelName(String labelId, String labelName, int currentPage, int pageSize) {
		ReturnPageData<List<Article>> returnPageData = new ReturnPageData<List<Article>>(currentPage, pageSize);
		try {
			// 没有处理查询条件
			int totalResults = articleDao.findArticlesCountByLabelName(labelId, labelName);
			if (totalResults > 0) {
				int startRow = returnPageData.getStartRow();
				List<Article> articles = articleDao.findArticlesByLabelName(labelId, labelName, startRow, pageSize);
				if (StringUtils.isEmpty(articles)) {
					articles = Collections.emptyList();
				}
				returnPageData.setTotalResults(totalResults);
				returnPageData.setResultData(articles);
				returnPageData.setReturnCode(ReturnCode.SUCCESS.getCode());
			} else {
				returnPageData.setReturnCode(ReturnCode.EXCEPTION.getCode());
			}

		} catch (Exception e) {
			log.error("service exception", e);
			returnPageData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnPageData;
	}

}
