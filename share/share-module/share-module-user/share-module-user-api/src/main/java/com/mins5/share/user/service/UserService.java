package com.mins5.share.user.service;

import java.util.List;

import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;
import com.mins5.share.user.domain.User;

/**
 * @author zhoutian
 * @since 2014-3-1
 */
public interface UserService {

	ReturnData<User> findUserById(Long userId);
	
	ReturnData<User> saveUser(User user);
	
	ReturnData<VOID> deleteUserById(Long userId);
	
	ReturnData<User> updateUser(User user);
	
	ReturnData<User> findUserByUsernameAndPassword(String username, String password);
	
	ReturnPageData<List<User>> findUserLikeUsername(String username, int currentPage, int onePageSize);
}
