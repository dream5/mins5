package com.mins5.share.user.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;
import com.mins5.share.user.dao.UserDao;
import com.mins5.share.user.domain.User;
import com.mins5.share.user.service.UserService;

/**
 * @author mins5
 * @since 2014-3-1
 */
@Service
public class UserServiceImpl implements UserService {
	
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	public ReturnData<User> findUserById(Long userId) {
		ReturnData<User> returnData = new ReturnData<User>();
		try {
			if(userId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			User user = userDao.findById(userId);
			returnData.setResultData(user);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	public ReturnData<User> saveUser(User user) {
		ReturnData<User> returnData = new ReturnData<User>();
		try {
			if(!checkSaveUser(user)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = userDao.save(user);
			if(count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(user);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	public ReturnData<VOID> deleteUserById(Long userId) {
		ReturnData<VOID> returnData = new ReturnData<VOID>();
		try {
			if(userId == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			userDao.deleteById(userId);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	public ReturnData<User> updateUser(User user) {
		ReturnData<User> returnData = new ReturnData<User>();
		try {
			if(!checkUpdateUser(user)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			userDao.update(user);
			returnData.setResultData(user);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	public ReturnData<User> findUserByUsernameAndPassword(String username,
			String password) {
		ReturnData<User> returnData = new ReturnData<User>();
		try {
			if(username == null || password == null) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			User user = userDao.findByUsernameAndPassword(username, password);
			returnData.setResultData(user);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}
	
	public ReturnPageData<List<User>> findUserLikeUsername(String username, int currentPage, int onePageSize) {
		ReturnPageData<List<User>> returnPageData = new ReturnPageData<List<User>>(currentPage, onePageSize);
		try {
			if(username == null) {
				returnPageData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnPageData;
			}
			
			int totalResults = userDao.findUserCountLikeUsername(username);
			int startRow = returnPageData.getStartRow();
			List<User> userList = userDao.findUserLikeUsername(username, startRow, onePageSize);
			if(userList == null) {
				userList = Collections.emptyList();
			}
			
			returnPageData.setTotalResults(totalResults);
			returnPageData.setResultData(userList);
			returnPageData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch(Exception e) {
			log.error("service exception", e);
			returnPageData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnPageData;
	}

	private boolean checkSaveUser(User user) {
		if(user == null) {
			return false;
		}
		if(user.getUsername() == null) {
			return false;
		}
		if(user.getPassword() == null) {
			return false;
		}
		if(user.getGender() == null) {
			return false;
		}
		if(user.getCreateDate() == null) {
			return false;
		}
		return true;
	}
	
	private boolean checkUpdateUser(User user) {
		if(user == null) {
			return false;
		}
		if(user.getUserId() == null) {
			return false;
		}
		return true;
	}

}
