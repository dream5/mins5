package com.mins5.share.business.admin.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
public class Admin extends DomainObject {

	private static final long serialVersionUID = -5709296665646637590L;

	private Long adminId;
	
	private String nickName;
	
	private String realName;
	
	private String password;
	
	private Date createTime;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", nickName=" + nickName
				+ ", realName=" + realName + ", password=" + password
				+ ", createTime=" + createTime + "]";
	}

}
