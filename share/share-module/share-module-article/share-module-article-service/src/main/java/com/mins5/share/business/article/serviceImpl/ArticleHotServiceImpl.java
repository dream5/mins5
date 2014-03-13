/**
 * 
 */
package com.mins5.share.business.article.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.dao.ArticleHotDao;
import com.mins5.share.business.article.domain.ArticleHot;
import com.mins5.share.business.article.service.ArticleHotService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月14日
 */
@Service
public class ArticleHotServiceImpl implements ArticleHotService {

	private static final Log log = LogFactory.getLog(ArticleHotServiceImpl.class);
	
	@Autowired
	private ArticleHotDao articleHotDao;
	
	@Override
	public ReturnData<ArticleHot> saveArticleHot(ArticleHot articleHot) {
		ReturnData<ArticleHot> returnData = new ReturnData<ArticleHot>();
		try {
			if(!checkSaveArticleHot(articleHot)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode()) ;
				return returnData;
			}
			int count = articleHotDao.save(articleHot);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode()) ;
				return returnData;
			}
			returnData.setResultData(articleHot);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			e.printStackTrace();
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkSaveArticleHot(ArticleHot articleHot) {
		if(articleHot == null) {
			return false;
		}
		if(articleHot.getArticleId() == null) {
			return false;
		}
		if(articleHot.getReprintCount() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteArticleHotById(Long hotId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if(hotId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleHotDao.deleteById(hotId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleHot> updateArticleHot(ArticleHot articleHot) {
		ReturnData<ArticleHot> returnData = new ReturnData<ArticleHot>();
		try {
			if(!checkUpdateArticleHot(articleHot)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			articleHotDao.update(articleHot);
			returnData.setResultData(articleHot);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	private boolean checkUpdateArticleHot(ArticleHot articleHot) {
		if(articleHot == null) {
			return false;
		}
		if(articleHot.getHotId()== null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<ArticleHot> findArticleHotById(Long hotId) {
		ReturnData<ArticleHot> returnData = new ReturnData<ArticleHot>();
		try {
			if(hotId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			ArticleHot articleHot = articleHotDao.findById(hotId);
			returnData.setResultData(articleHot);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

}
