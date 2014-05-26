package com.mins5.share.business.article.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mins5.share.business.article.dao.ArticleDao;
import com.mins5.share.business.article.dao.ArticleKindDao;
import com.mins5.share.business.article.dao.ArticleKindRelDao;
import com.mins5.share.business.article.dao.ArticleLabelRelDao;
import com.mins5.share.business.article.service.BaseService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

public class BaseServiceImpl<T, I> implements BaseService<T> {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private ArticleKindDao articleKindDao;

	@Autowired
	private ArticleLabelRelDao articleLabelRelDao;

	@Autowired
	private ArticleKindRelDao articleKindRelDao;

	/**
	 * 返回DAO接口
	 * 
	 * @param i
	 * @author zhanglin
	 * @since 2014年5月26日
	 */
	/*
	 * private I getDao(I i) { if (i instanceof ArticleDao) { dao = (I)
	 * articleDao; } return dao; }
	 */

	/**
	 * 通用实体检测类，可子类重写实现具体逻辑
	 * 
	 * @param t
	 * @author zhanglin
	 * @since 2014年5月26日
	 */
	protected boolean checkDomain(T t) {
		boolean result = true;
		if (t == null) {
			result = false;
		}
		return result;

	}

	@Override
	public ReturnData<T> save(T t) {
		ReturnData<T> returnData = new ReturnData<T>();
		try {
			if (!checkDomain(t)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			/*
			 * int count = dao.save(t); if (count != 1) {
			 * returnData.setReturnCode(ReturnCode.EXCEPTION.getCode()); return
			 * returnData; } returnData.setResultData(articleHot);
			 */
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<VOID> deleteById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnData<T> update(T e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnData<T> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
