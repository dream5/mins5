/**
 * 
 */
package com.mins5.share.web.controller.admin;

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
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.business.admin.domain.Attachment;
import com.mins5.share.business.admin.enums.ARTICLE_STATUS;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.DateUtils;
import com.mins5.share.util.EasyUIUtils;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.util.StringUtils;

/**
 * @author chenry
 * @since 2014-5-22
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private AdminService adminService;

	
	/**
	 * 管理员列表
	 * 
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/adminList")
	public String adminList(HttpServletRequest request) {
		//ARTICLE_STATUS[] adminStatusArray = ARTICLE_STATUS.values();
		//request.setAttribute("adminStatusArray", adminStatusArray);
		return "admin/admin_list";
	}

	/**
	 * 管理员列表查询
	 * 
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param response
	 * @param adminTitle 管理员标题
	 * @param status 管理员状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isOriginal 是否原创
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 */
	@RequestMapping(value = "/searchAdmin")
	public void searchAdmin(HttpServletResponse response, String adminTitle, String status, String beginTime, String endTime, String isOriginal,
			Integer currentPage, Integer onePageSize) {

		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;

		ReturnPageData<List<Admin>> returnData = adminService.findAdminsByAdminTitleAndStatusAndCreateTimeAndIsOriginal(
				StringUtils.parseNull(adminTitle), StringUtils.parseNull(status), DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT),
				DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT), StringUtils.parseNull(isOriginal), currentPage, onePageSize);

		List<Admin> adminList = returnData.getResultData();
		List<Map<String, Object>> adminMapList = new ArrayList<Map<String, Object>>();
		if (adminList != null) {
			for (Admin admin : adminList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("adminId", admin.getAdminId());
				map.put("adminTitle", admin.getAdminTitle());
				map.put("createTime", admin.getCreateTime());
				map.put("isOriginal", admin.getIsOriginal());
				map.put("status", admin.getStatus().getName());
				adminMapList.add(map);
			}
		}

		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), adminMapList);
		JsonUtils.write(data, response);
	}

	/**
	 * 抓取管理员列表查询
	 * 
	 * @author zhanglin copy from searchAdmin方法
	 * @since 2014年4月8日
	 * @param response
	 * @param adminTitle 管理员标题
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPage 当前页
	 * @param onePageSize 每页行数
	 */
	@RequestMapping(value = "/searchCaptureAdmin")
	public void searchCaptureAdmin(HttpServletResponse response, String adminTitle, String beginTime, String endTime, Integer currentPage,
			Integer onePageSize) {

		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;

		ReturnPageData<List<Admin>> returnData = adminService.findAllCaptureAdminsByCondition(StringUtils.parseNull(adminTitle),
				DateUtils.parseDate(beginTime, DateUtils.DATE_FORMAT), DateUtils.parseDate(endTime, DateUtils.DATE_FORMAT), currentPage, onePageSize);

		List<Admin> adminList = returnData.getResultData();
		List<Map<String, Object>> adminMapList = new ArrayList<Map<String, Object>>();
		if (adminList != null) {
			for (Admin admin : adminList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("adminId", admin.getAdminId());
				map.put("adminTitle", admin.getAdminTitle());
				map.put("createTime", admin.getCreateTime());
				map.put("adminAuthor", admin.getAdminAuthor());
				map.put("adminFrom", admin.getAdminFrom());
				map.put("adminSts", admin.getStatus());
				adminMapList.add(map);
			}
		}

		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), adminMapList);
		JsonUtils.write(data, response);
	}

	/**
	 * 跳转到添加管理员页面
	 * 
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @return
	 */
	@RequestMapping(value = "/toAdminAdd")
	public String toAdminAdd() {
		return "admin/admin_add";
	}

	/**
	 * ajax请求添加管理员
	 * 
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @param response
	 * @param admin
	 */
	@RequestMapping(value = "/adminAdd")
	public void adminAdd(HttpServletResponse response, Admin admin, String adminKind, String adminLabel,String attachmentId) {

		try {

			Date currentDate = new Date();
			admin.setCreateTime(currentDate);
			admin.setUpdateTime(currentDate);
			admin.setStatus(ARTICLE_STATUS.WAITING_CHECK);

			String[] adminKindIdArray = adminKind.split(",");
			List<Long> adminKindIdList = new ArrayList<Long>();
			for (String adminKindId : adminKindIdArray) {
				adminKindIdList.add(Long.parseLong(adminKindId));
			}

			String[] adminLabelIdArray = adminLabel.split(",");
			List<Long> adminLabelIdList = new ArrayList<Long>();
			for (String adminLabelId : adminLabelIdArray) {
				adminLabelIdList.add(Long.parseLong(adminLabelId));
			}

			ReturnData<Admin> returnData = adminService.saveAdmin(admin, adminKindIdList, adminLabelIdList);
			//添加图片
			if(!org.springframework.util.StringUtils.isEmpty(attachmentId)){
				long adminId = returnData.getResultData().getAdminId();
				Attachment attachment = new Attachment();
				attachment.setAdminId((int)adminId);
				attachment.setAttachmentId(Integer.parseInt(attachmentId));
				attachmentService.updateAttachment(attachment);
			}
			String tip = "添加成功！";
			if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
				tip = "添加失败！";
			}
			JsonUtils.write(tip, response);
		} catch (Exception e) {
			log.error("添加管理员失败！", e);
			JsonUtils.write("添加失败！", response);
		}
	}

	/**
	 * 查看管理员详细信息
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param adminId
	 */
	@RequestMapping(value = "/adminDetail")
	public void adminDetail(HttpServletResponse response, Long adminId) {
		ReturnData<Admin> returnData = adminService.findAdminById(adminId);
		Admin admin = returnData.getResultData();
		JsonUtils.write(admin, response);
	}

	/**
	 * 跳转到管理员修改页面
	 * 
	 * @author zhoutian
	 * @since 2014年4月9日
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value = "/toAdminEdit")
	public ModelAndView toAdminEdit(Long adminId) {
		ModelAndView mv = new ModelAndView();
		Admin admin = adminService.findAdminById(adminId).getResultData();
		mv.addObject("admin", admin);
		mv.setViewName("admin/admin_edit");
		return mv;
	}

	public void adminEdit() {

	}

	/**
	 * 删除管理员
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param adminId
	 */
	@RequestMapping(value = "/adminDelete")
	public void adminDelete(HttpServletResponse response, Long adminId) {
		String tip = "删除成功！";
		try {
			adminService.deleteAdminById(adminId);
		} catch (Exception e) {
			tip = "删除失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 发布管理员
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param adminId
	 */
	@RequestMapping(value = "/publishedAdmin")
	public void publishedAdmin(HttpServletResponse response, Long adminId) {
		String tip = "发布成功！";
		try {
			adminService.publishedAdmin(adminId);
		} catch (Exception e) {
			tip = "发布失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * <p>
	 * 将临时表数据推送到正式表
	 * </p>
	 * 
	 * @param response
	 * @param adminId
	 * @author zhanglin
	 * @since 2014年5月13日
	 */
	@RequestMapping(value = "/publishedAdminToTable")
	public void publishedAdminToTable(HttpServletResponse response, Long adminId) {
		String tip = "发布成功！";
		try {
			adminService.publishedAdminToTable(adminId);
		} catch (Exception e) {
			tip = "发布失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * <p>
	 * 批量将临时表数据推送到正式表
	 * </p>
	 * 
	 * @param response
	 * @param adminIds
	 * @author zhanglin
	 * @since 2014年5月13日
	 */
	@RequestMapping(value = "/publishedAdminsToTable")
	public void publishedAdminsToTable(HttpServletResponse response, String adminIds) {
		String tip = "批量发布成功！";
		try {
			String[] ids = adminIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				adminService.publishedAdminToTable(Long.valueOf(ids[i]));
			}

		} catch (Exception e) {
			tip = "批量发布失败！";
		}
		JsonUtils.write(tip, response);
	}
	
	/**
	 * 
	 * <p>批量从临时表中清除数据</p>
	 * @param response
	 * @param adminIds
	 */
	@RequestMapping(value = "/removeAdminsFromTable")
	public void removeAdminsFromTable(HttpServletResponse response, String adminIds) {
		String tip = "批量删除成功！";
		try {
			String[] ids = adminIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				adminService.removedAdminFromTempTable(Long.valueOf(ids[i]));
			}

		} catch (Exception e) {
			tip = "批量删除失败！";
		}
		JsonUtils.write(tip, response);
	}
	

	/**
	 * 下架管理员
	 * 
	 * @author zhoutian
	 * @since 2014年4月13日
	 * @param response
	 * @param adminId
	 */
	@RequestMapping(value = "/removedAdmin")
	public void removedAdmin(HttpServletResponse response, Long adminId) {
		String tip = "下架成功！";
		try {
			adminService.removedAdmin(adminId);
		} catch (Exception e) {
			tip = "下架失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 查看抓取管理员详细信息
	 * 
	 * @author zhanglin
	 * @since 20140506
	 * @param response
	 * @param adminId
	 */
	@RequestMapping(value = "/captureAdminDetail")
	public void captureAdminDetail(HttpServletResponse response, Long adminId) {
		ReturnData<Admin> returnData = adminService.findCaptureAdminById(adminId);
		Admin admin = returnData.getResultData();
		JsonUtils.write(admin, response);
	}

}
