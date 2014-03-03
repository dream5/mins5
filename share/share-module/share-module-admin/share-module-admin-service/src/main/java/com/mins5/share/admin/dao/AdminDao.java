package com.mins5.share.admin.dao;

import org.springframework.stereotype.Service;

import com.mins5.share.admin.domain.Admin;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author mins5
 * @since 2014-2-27
 */
@Service
public interface AdminDao extends CrudDao<Long, Admin> {

}
