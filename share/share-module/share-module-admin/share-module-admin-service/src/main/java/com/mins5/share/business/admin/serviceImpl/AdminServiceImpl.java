package com.mins5.share.business.admin.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.business.admin.dao.AdminDao;
import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Log log = LogFactory.getLog(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public ReturnData<Admin> findAdminById(Long adminId) {
		ReturnData<Admin> returnData = new ReturnData<Admin>();
		try {
			if(adminId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			Admin admin = adminDao.findById(adminId);
			returnData.setResultData(admin);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
}
