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
	
	private String username;
	
	private String password;
	
	private Date createDate;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", username=" + username
				+ ", password=" + password + ", createDate=" + createDate + "]";
	}
	
}
