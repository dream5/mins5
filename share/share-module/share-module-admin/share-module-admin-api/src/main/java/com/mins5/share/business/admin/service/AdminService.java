package com.mins5.share.business.admin.service;

import java.util.List;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * 
 * @author chenry
 * @since 2014年5月23日
 */
public interface AdminService {

	/**
	 * 新增
	 * 
	 * @author chenry
	 * @since 2014年3月13日
	 * @param Admin
	 * @return
	 */
	ReturnData<Admin> saveAdmin(Admin Admin);

	/**
	 * 根据ID删除
	 * 
	 * @author chenry
	 * @since 2014年3月13日
	 * @param adminId
	 * @return
	 */
	ReturnData<VOID> deleteAdminById(Long adminId);

	/**
	 * 更新
	 * 
	 * @author chenry
	 * @since 2014年3月13日
	 * @param Admin
	 * @return
	 */
	ReturnData<Admin> updateAdmin(Admin Admin);

	/**
	 * 根据ID查询
	 * 
	 * @author chenry
	 * @since 2014年3月13日
	 * @param adminId
	 * @return
	 */
	ReturnData<Admin> findAdminById(Long adminId);

	/**
	 * 根据Admin对象查询
	 * 
	 * @author chenry
	 * @since 2014年3月13日
	 * @param adminId
	 * @return
	 */
	Long findAdminCountByModel(Admin admin);

	/**
	 * 分页查询管理员列表
	 * 
	 * @author chenry
	 * @since 2014年3月22日
	 * @param admin 查询参数
	 * @param currentPage 当前页
	 * @param onePageSize 每行行数
	 * @return
	 */
	ReturnPageData<List<Admin>> findAdminListByModel(Admin admin, int currentPage, int onePageSize);

	ReturnData<Admin> findByUserName(String username);

	ReturnData<Boolean> checkUserName(String userMame);

}
