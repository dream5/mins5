package com.mins5.share.business.admin.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mins5.share.business.admin.dao.AdminDao;
import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * 
 * @author chenry
 * @since 2014年5月23日
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Log log = LogFactory.getLog(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao;
	
	@Override
	@Transactional
	public ReturnData<Admin> saveAdmin(Admin Admin) {
		ReturnData<Admin> returnData = new ReturnData<Admin>();
		try {
			if (!checkSaveAdmin(Admin)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = adminDao.save(Admin);
			if (count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(Admin);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveAdmin(Admin Admin) {
		if (Admin == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<VOID> deleteAdminById(Long AdminId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if (AdminId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			adminDao.deleteById(AdminId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<Admin> updateAdmin(Admin Admin) {
		ReturnData<Admin> returnData = new ReturnData<Admin>();
		try {
			if (!checkUpdateAdmin(Admin)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			adminDao.update(Admin);
			returnData.setResultData(Admin);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkUpdateAdmin(Admin Admin) {
		if (Admin == null) {
			return false;
		}
		if (Admin.getAdminId() == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReturnData<Admin> findAdminById(Long AdminId) {
		ReturnData<Admin> returnData = new ReturnData<Admin>();
		try {
			if (AdminId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			System.out.println(AdminId  + "---------------");
			Admin Admin = adminDao.findById(AdminId);
			System.out.println(Admin  + "---------------");
			returnData.setResultData(Admin);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public Long findAdminCountByModel(Admin admin) {
		return adminDao.findAdminCountByModel(admin);
	}

	@Override
	public ReturnPageData<List<Admin>> findAdminListByModel(Admin admin, int currentPage, int onePageSize) {
		
		ReturnPageData<List<Admin>> returnData = new ReturnPageData<List<Admin>>(currentPage, onePageSize);
		try {
			long count = adminDao.findAdminCountByModel(admin);
			if (count > 0) {
				int startRow = returnData.getStartRow();
				System.out.println(admin+ "......");
				System.out.println(admin.getUserName()+ "......");

				List<Admin> adminList = adminDao.findAdminListByModel(admin, startRow, onePageSize);
				System.out.println(adminList+ "......");
				returnData.setTotalResults(count);
				if (StringUtils.isEmpty(adminList)) {
					adminList = Collections.emptyList();
				}
				returnData.setResultData(adminList);
			}
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
		
	}

	
}
