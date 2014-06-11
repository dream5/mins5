/**
 * 
 */
package com.mins5.share.web.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.util.DateUtils;
import com.mins5.share.util.EasyUIUtils;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.util.MD5;

/**
 * 
 * @author chenry
 * @since 2014年5月23日
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
	public String adminList() {
		return "admin/admin_list";
	}

	/**
	 * ajax查询文章标签
	 * 
	 * @author chenry
	 * @since 2014年3月29日
	 * @param response
	 * @param labelName
	 * @param status
	 * @param beginTime
	 * @param endTime
	 * @param currentPage
	 * @param articleLabelService
	 */
	@RequestMapping(value = "/searchAdmin")
	public void searchAdmin(HttpServletResponse response, String userName, String nickName, String status, String createTime, Integer currentPage,
			Integer onePageSize, Object articleLabelService) {

		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setNickName(nickName);
		//admin.set(createTime)
		admin.setCreateTime(DateUtils.parseDate(createTime, DateUtils.DATE_FORMAT));

		onePageSize = onePageSize == null ? 10 : onePageSize;
		currentPage = currentPage == null ? 1 : currentPage;

		ReturnPageData<List<Admin>> returnData = adminService.findAdminListByModel(admin, currentPage, onePageSize);
		String data = EasyUIUtils.parseDataGrid(returnData.getTotalResults(), returnData.getResultData());
		JsonUtils.write(data, response);
	}

	/**
	 * 跳转到添加管理员页面
	 * 
	 * @author chenry
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
	 * @author chenry
	 * @since 2014年3月17日
	 * @param response
	 * @param admin
	 */
	@RequestMapping(value = "/adminAdd")
	public void adminAdd(HttpServletResponse response, Admin admin) {
		Date currentDate = new Date();
		admin.setCreateTime(currentDate);
		admin.setPassword(MD5.encode(admin.getPassword()));
		ReturnData<Admin> returnData = adminService.saveAdmin(admin);
		String tip = "添加成功！";
		if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "添加失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 跳转到管理员编辑页面
	 * 
	 * @author chenry
	 * @since 2014年3月31日
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/toAdminEdit")
	public ModelAndView toAdminEdit(Long labelId) {
		ModelAndView mv = new ModelAndView();
		ReturnData<Admin> returnData = adminService.findAdminById(labelId);
		Admin admin = returnData.getResultData();
		mv.addObject("admin", admin);
		mv.setViewName("admin/admin_edit");
		return mv;
	}

	/**
	 * 修改管理员
	 * 
	 * @author chenry
	 * @since 2014年3月31日
	 * @param response
	 * @param labelId
	 * @param labelName
	 * @param status
	 */
	@RequestMapping(value = "/adminEdit")
	public void adminEdit(HttpServletResponse response, Admin admin) {
		admin.setPassword(MD5.encode(admin.getPassword()));
		ReturnData<Admin> returnData = adminService.updateAdmin(admin);
		String tip = "修改成功！";
		if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "修改失败！";
		}
		JsonUtils.write(tip, response);
	}

	/**
	 * 删除管理员
	 * 
	 * @author chenry
	 * @since 2014年3月31日
	 * @param response
	 * @param labelId
	 */
	@RequestMapping(value = "/adminDelete")
	public void adminLableDelete(HttpServletResponse response, Long adminId) {
		String tip = "删除成功！";
		try {
			adminService.deleteAdminById(adminId);
		} catch (Exception e) {
			tip = "删除失败！";
		}
		JsonUtils.write(tip, response);
	}

}
