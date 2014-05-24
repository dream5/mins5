package com.mins5.share.business.admin.serviceImpl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mins5.share.business.admin.domain.Admin;
import com.mins5.share.business.admin.service.AdminService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class AdminServiceImplTest {

	@Autowired
	private AdminService adminService;
	
	@Test
	public void testFindAdminById() {
		Long adminId = 1L;
		ReturnData<Admin> returnData = adminService.findAdminById(adminId);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
	
	@Test
	public void testFindAdminCountByModel() {
		Admin admin = new Admin();
		admin.setUserName("天");
		long returnData = adminService.findAdminCountByModel(admin);
		Assert.assertNull("", returnData);
	}
	
	@Test
	public void testFindAdminListByModel() {
		Admin admin = new Admin();
		admin.setUserName("c");
		ReturnPageData<List<Admin>> returnData = adminService.findAdminListByModel(admin,0,10);
		Assert.assertEquals(ReturnCode.SUCCESS.getCode(), returnData.getReturnCode());
	}
}
