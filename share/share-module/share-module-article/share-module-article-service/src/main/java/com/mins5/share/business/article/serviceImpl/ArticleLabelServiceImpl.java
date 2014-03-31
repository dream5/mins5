/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mins5.share.business.article.dao.ArticleLabelDao;
import com.mins5.share.business.article.dao.ArticleLabelRelDao;
import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.business.article.service.ArticleLabelService;
import com.mins5.share.common.exception.TransactionException;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月13日
 */
@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {

	private static final Log log = LogFactory.getLog(ArticleLabelServiceImpl.class);
	
	@Autowired
	private ArticleLabelDao articleLabelDao;
	
	@Autowired
	private ArticleLabelRelDao articleLabelRelDao;
	
	@Override
	public ReturnData<ArticleLabel> saveArticleLabel(ArticleLabel articleLabel) {
		ReturnData<ArticleLabel> returnData = new ReturnData<ArticleLabel>();
		try {
			if(!checkSaveArticleLabel(articleLabel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode()) ;
				return returnData;
			}
			int count = articleLabelDao.save(articleLabel);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode()) ;
				return returnData;
			}
			returnData.setResultData(articleLabel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkSaveArticleLabel(ArticleLabel articleLabel) {
		if(articleLabel == null) {
			return false;
		}
		if(articleLabel.getCreateTime() == null) {
			return false;
		}
		if(articleLabel.getLabelName() == null) {
			return false;
		}
		if(articleLabel.getStatus() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleLabelById(Long labelId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if(labelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleLabelDao.deleteById(labelId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleLabel> updateArticleLabel(ArticleLabel articleLabel) {
		ReturnData<ArticleLabel> returnData = new ReturnData<ArticleLabel>();
		try {
			if(!checkUpdateArticleLabel(articleLabel)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleLabelDao.update(articleLabel);
			returnData.setResultData(articleLabel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkUpdateArticleLabel(ArticleLabel articleLabel) {
		if(articleLabel == null) {
			return false;
		}
		if(articleLabel.getLabelId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleLabel> findArticleLabelById(Long labelId) {
		ReturnData<ArticleLabel> returnData = new ReturnData<ArticleLabel>();
		try {
			if(labelId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleLabel articleLabel = articleLabelDao.findById(labelId);
			returnData.setResultData(articleLabel);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<List<ArticleLabel>> findArticleLabelByNum(int amount) {
		ReturnData<List<ArticleLabel>> returnData = new ReturnData<List<ArticleLabel>>();
		try {
			if(StringUtils.isEmpty(amount)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			List<ArticleLabel> articleLabels = articleLabelDao.findArticleLabelByNum(amount);
			returnData.setResultData(articleLabels);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnPageData<List<ArticleLabel>> findArticleLabelByLabelNameAndStatusAndCreateTime(
			String labelName, String status, Date beginTime, Date endTime,
			int currentPage, int onePageSize) {
		ReturnPageData<List<ArticleLabel>> returnData = new ReturnPageData<List<ArticleLabel>>(currentPage, onePageSize);
		try {
			int count = articleLabelDao.findArticleLabelCountByLabelNameAndStatusAndCreateTime(labelName, status, beginTime, endTime);
			if(count > 0) {
				int startRow = returnData.getStartRow();
				List<ArticleLabel> articleLabelList = articleLabelDao.findArticleLabelByLabelNameAndStatusAndCreateTime(labelName, status, beginTime, endTime, startRow, onePageSize);
				returnData.setTotalResults(count);
				returnData.setResultData(articleLabelList);;
			}
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	@Transactional
	public ReturnData<VOID> deleteArticleLabelAndArticleLabelRelByLabelId(Long articleLabelId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			articleLabelDao.deleteById(articleLabelId);
			articleLabelRelDao.deleteArticleLabelRelByArticleLabelId(articleLabelId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(TransactionException e) {
			log.error("service exception", e);
			throw new TransactionException(ReturnCode.EXCEPTION.getCode(), e);
		}
		return returnData;
	}

}
