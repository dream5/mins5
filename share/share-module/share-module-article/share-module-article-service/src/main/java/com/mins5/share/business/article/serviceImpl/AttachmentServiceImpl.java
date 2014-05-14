/*
 * Copyright (c) 2000-- 2014 Dream five. All Rights Reserved.
 */

package com.mins5.share.business.article.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.dao.AttachmentDao;
import com.mins5.share.business.article.domain.Attachment;
import com.mins5.share.business.article.service.AttachmentService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhanglin
 * @since 2014年5月14日
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

	private static final Log log = LogFactory.getLog(AttachmentServiceImpl.class);

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public ReturnData<Attachment> saveAttachment(Attachment attachment) {
		ReturnData<Attachment> returnData = new ReturnData<Attachment>();
		try {
			if (!checkSaveAttachment(attachment)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = attachmentDao.save(attachment);
			if (count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(attachment);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveAttachment(Attachment attachment) {
		if (attachment == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteAttachmentById(Long attachmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnData<Attachment> updateAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnData<Attachment> findAttachmentById(Long attachmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
