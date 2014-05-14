package com.mins5.share.business.article.service;

import java.util.List;
import java.util.Map;

import com.mins5.share.business.article.domain.Attachment;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * <p>
 * 附件管理service
 * </p>
 * 
 * @author：auto_code
 * @date： 2014-4-30 15:55:52
 */
public interface AttachmentService {

	/**
	 * 新增
	 * 
	 * @author auto_code
	 * @since 2014-4-30 15:55:52
	 * @param Attachment
	 * @return
	 */
	ReturnData<Attachment> saveAttachment(Attachment attachment);

	/**
	 * 根据ID删除
	 * 
	 * @author auto_code
	 * @since 2014-4-30 15:55:52
	 * @param attachmentId
	 * @return
	 */
	ReturnData<VOID> deleteAttachmentById(Long attachmentId);

	/**
	 * 更新
	 * 
	 * @author auto_code
	 * @since 2014-4-30 15:55:52
	 * @param Attachment
	 * @return
	 */
	ReturnData<Attachment> updateAttachment(Attachment attachment);

	/**
	 * 根据ID查询
	 * 
	 * @author auto_code
	 * @since 2014-4-30 15:55:52
	 * @param attachmentId
	 * @return
	 */
	ReturnData<Attachment> findAttachmentById(Long attachmentId);

	/**
	 * 根据条件查询附件数据
	 * 
	 * @param param
	 * @return
	 * 
	 * @author zhanglin
	 * @since 2014年5月14日
	 */
	ReturnPageData<List<Attachment>> findAttachmentListByCondition(Map params, int currentPage, int onePageSize);

}
