package com.mins5.share.business.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.mins5.share.business.user.domain.User;
import com.mins5.share.common.dao.CrudDao;

/**
 * @author zhoutian
 * @since 2014-3-1
 */
@Service
public interface UserDao extends CrudDao<Long, User> {

	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	int findUserCountLikeUsername(String username);
	
	List<User> findUserLikeUsername(@Param("username") String username, @Param("statRow") int statRow, @Param("onePageSize") int onePageSize);
}
