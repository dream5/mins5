package com.mins5.share.business.user.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;
import com.mins5.share.common.enums.GENDER;

/**
 * @author zhoutian
 * @since 2014-2-28
 */
public class User extends DomainObject {

	private static final long serialVersionUID = -1647023021068933886L;

	private Long userId;
	
	private String username;
	
	private String password;
	
	private GENDER gender;
	
	private Date createDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", gender=" + gender
				+ ", createDate=" + createDate + "]";
	}
	
}
