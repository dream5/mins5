/*
 * Copyright (c) 2000-- 2014 Dream five. All Rights Reserved.
 */

package com.mins5.share.business.article.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mins5.share.business.article.domain.Attachment;
import com.mins5.share.common.dao.CrudDao;

/**
 * <p>
 * 附件DAO
 * </p>
 * 
 * @author：auto_code
 * @date： 2014-5-14 09:44:52
 */
public interface AttachmentDao extends CrudDao<Long, Attachment> {

	/**
	 * <p>
	 * 根据条件查询附件数量
	 * </p>
	 * 
	 * @author：auto_code
	 * @date：2014-5-14 09:44:52
	 */
	int findAttachmentCount(Map params) throws DataAccessException;

	/**
	 * <p>
	 * 根据条件查询附件数据
	 * </p>
	 * 
	 * @author：auto_code
	 * @date： 2014-5-14 09:44:52
	 */
	List<Attachment> findAttachmentList(Map params) throws DataAccessException;

}
