package com.mins5.share.business.admin.dao;

import org.springframework.stereotype.Service;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
@Service
public interface AdminDao extends CrudDao<Long, Admin> {

}
