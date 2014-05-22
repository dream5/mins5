package com.mins5.share.business.admin.service;

import java.util.List;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
public interface AdminService {

	
	/**
	 * 新增
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param Admin
	 * @return
	 */
	ReturnData<Admin> saveAdmin(Admin Admin);

	/**
	 * 根据ID删除
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param AdminId
	 * @return
	 */
	ReturnData<VOID> deleteAdminById(Long labelId);

	/**
	 * 更新
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param Admin
	 * @return
	 */
	ReturnData<Admin> updateAdmin(Admin Admin);

	/**
	 * 根据ID查询
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param labelId
	 * @return
	 */
	ReturnData<Admin> findAdminById(Long labelId);

	/**
	 * 查询一定数目的标签
	 * 
	 * @param amount
	 *            数量
	 * @return
	 */
	ReturnData<List<Admin>> findAdminByNum(int amount);

}
