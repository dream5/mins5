package com.mins5.share.admin.service;

import com.mins5.share.admin.domain.Admin;
import com.mins5.share.common.service.ReturnData;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
public interface AdminService {

	ReturnData<Admin> findAdminById(Long adminId);
}
