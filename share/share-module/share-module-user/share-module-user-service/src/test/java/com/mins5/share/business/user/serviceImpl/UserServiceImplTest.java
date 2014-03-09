package com.mins5.share.business.user.serviceImpl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mins5.share.business.user.domain.User;
import com.mins5.share.business.user.service.UserService;
import com.mins5.share.common.enums.GENDER;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014-3-2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUserById() {
		Long userId = 1L;
		ReturnData<User> returnData = userService.findUserById(userId);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setCreateTime(new Date());
		user.setGender(GENDER.SECRET);
		user.setPassword("123456");
		user.setNickName("单元测试昵称");
		user.setUserName("单元测试用户名");
		ReturnData<User> returnData = userService.saveUser(user);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}

	@Test
	public void testDeleteUserById() {
		Long userId = 99999999L;
		ReturnData<VOID> returnData = userService.deleteUserById(userId);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setUserId(1L);
		user.setCreateTime(new Date());
		user.setGender(GENDER.FEMALE);
		user.setPassword("111111");
		user.setNickName("单元测试修改昵称");
		user.setUserName("单元测试修改用户名");
		ReturnData<User> returnData = userService.updateUser(user);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}

	@Test
	public void testFindUserByUsernameAndPassword() {
		String userName = "超级管理员";
		String password = "123456";
		ReturnData<User> returnData = userService.findUserByUserNameAndPassword(userName, password);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testFindUserLikeUsername() {
		String userName = "超级管理员";
		int currentPage = 1;
		int onePageSize = 10;
		ReturnPageData<List<User>> returnPageData = userService.findUserLikeUserName(userName, currentPage, onePageSize);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnPageData.getReturnCode());
	}

}
