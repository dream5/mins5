package com.mins5.share.web.controller.images;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.attachment.util.FileUtils;
import com.mins5.share.attachment.util.UploadUtil;
import com.mins5.share.business.article.domain.Attachment;
import com.mins5.share.business.article.service.AttachmentService;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.DateUtils;
import com.mins5.share.util.EasyUIUtils;
import com.mins5.share.util.JsonUtils;

/**
 * <p>
 * 图片控制器
 * </p>
 * 
 * @author zhanglin 2014年5月2日 下午7:21:59
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/upload")
public class ImagesController {

	private static final Log log = LogFactory.getLog(ImagesController.class);

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/addImagesInit")
	public String addInit() {
		return "images/uploadImages";
	}

	@RequestMapping(value = "/beginUpload")
	public void BeginUpload(HttpServletRequest request, HttpServletResponse response) {
		Map alist = UploadUtil.singleFileUpload(request);
		// 上传信息保存到数据库
		Attachment attachment = new Attachment();
		attachment.setAttachmenSts("1");// 图片没有审核，直接通过状态
		attachment.setAttachmentName(alist.get("newName").toString());
		attachment.setAttachmentOldName(alist.get("oldName").toString());
		attachment.setAttachmentType(alist.get("type").toString());
		attachment.setCreateDate(new Date());
		attachment.setLarge(alist.get("large").toString());
		attachment.setMidSize(alist.get("midSize").toString());
		attachment.setSmall(alist.get("small").toString());
		ReturnData<Attachment> attamentData = attachmentService.saveAttachment(attachment);
		Attachment attachmentWithId = attamentData.getResultData();
		alist.put("attachmentId", attachmentWithId.getAttachmentId());
		JsonUtils.write(alist, response);
	}

	/**
	 * 查询上传图片列表
	 * 
	 * @param response
	 * @param articleTitle
	 * @param beginTime
	 * @param endTime
	 * @param currentPage
	 * @param onePageSize
	 * 
	 * @author zhanglin
	 * @since 2014年5月14日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/searchUploadImages")
	public void searchUploadImages(HttpServletRequest request, HttpServletResponse response, String articleTitle, String beginTime, String endTime,
			Integer currentPage, Integer onePageSize) {
		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;
		Map params = new HashMap();
		params.put("beginTime", DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT));
		params.put("endTime", DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT));
		ReturnPageData<List<Attachment>> returnData = attachmentService.findAttachmentListByCondition(params, currentPage, onePageSize);
		List<Attachment> attachments = returnData.getResultData();
		List<Map<String, Object>> attachmentsMapList = new ArrayList<Map<String, Object>>();
		if (attachments != null) {
			for (Attachment attachment : attachments) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("attachmentId", attachment.getAttachmentId());
				map.put("attachmentOldName", attachment.getAttachmentOldName());
				map.put("attachmentName", attachment.getAttachmentName());
				map.put("attachmentType", attachment.getAttachmentType());
				map.put("attachmenSts", attachment.getAttachmenSts());
				map.put("createDate", attachment.getCreateDate());
				map.put("articleId", attachment.getArticleId());
				map.put("large", attachment.getLarge());
				map.put("midSize", attachment.getMidSize());
				map.put("small", attachment.getSmall());
				map.put("path", FileUtils.getUploadPath(request));
				attachmentsMapList.add(map);
			}
		}
		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), attachmentsMapList);
		JsonUtils.write(data, response);
	}

	/**
	 * 根据附件ID删除附件
	 * 
	 * @param response
	 * @param attachmentId
	 * @author zhanglin
	 * @since 2014年5月19日
	 */
	@RequestMapping(value = "/deleteImagesById")
	public void deleteImagesById(HttpServletRequest request, HttpServletResponse response, String attachmentId) {
		String tip = "批量删除成功！";
		try {
			String[] ids = attachmentId.split(",");
			for (int i = 0; i < ids.length; i++) {
				ReturnData<Attachment> returnData = attachmentService.findAttachmentById(Long.valueOf(ids[i]));
				String large = FileUtils.getUploadPath(request) + returnData.getResultData().getLarge();
				String midsize = FileUtils.getUploadPath(request) + returnData.getResultData().getMidSize();
				String small = FileUtils.getUploadPath(request) + returnData.getResultData().getSmall();
				FileUtils.removeFile(large);
				FileUtils.removeFile(midsize);
				FileUtils.removeFile(small);
				attachmentService.deleteAttachmentById(Long.valueOf(ids[i]));
			}

		} catch (Exception e) {
			tip = "批量删除失败！";
		}
		JsonUtils.write(tip, response);
	}

}
