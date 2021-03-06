package com.mins5.share.business.user.service;

import java.util.List;

import com.mins5.share.business.user.domain.User;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014-3-1
 */
public interface UserService {

	ReturnData<User> findUserById(Long userId);
	
	ReturnData<User> saveUser(User user);
	
	ReturnData<VOID> deleteUserById(Long userId);
	
	ReturnData<User> updateUser(User user);
	
	ReturnData<User> findUserByUserNameAndPassword(String userName, String password);
	
	ReturnPageData<List<User>> findUserLikeUserName(String userName, int currentPage, int onePageSize);
}
