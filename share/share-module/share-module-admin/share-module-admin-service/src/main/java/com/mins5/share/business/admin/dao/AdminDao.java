package com.mins5.share.business.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
@Service
public interface AdminDao extends CrudDao<Long, Admin> {

	
	Admin findByUserNameAndPassword(String username ,String password);
	
	
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
	 * @author chenry
	 * @since 2014年3月22日
	 * @param admin 查询参数
	 * @param currentPage 当前页
	 * @param onePageSize 每行行数
	 * @return
	 */
	List<Admin> findAdminListByModel(Map map);
}
