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
	
	private String nickName;
	
	private String realName;
	
	private String password;
	
	private GENDER gender;
	
	private Date createTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName
				+ ", realName=" + realName + ", password=" + password
				+ ", gender=" + gender + ", createTime=" + createTime + "]";
	}

}
