package com.mins5.share.business.article.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;


/**
 * 
 * @author chenry
 * @since 2014-3-9
 */
public class ArticleKind extends DomainObject {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields

	private Long articleKindId;
	private String kindName;
	private String kindPinyin;
	private String status;
	private Date updateTime;
	private Date createTime;
	private Long adminId;
	private Long parentKindId;

	// Constructors

	/** default constructor */
	public ArticleKind() {
	}

	/** minimal constructor */
	public ArticleKind(String kindName, String status, Date updateTime,
			Date createTime, Long parentKindId) {
		this.kindName = kindName;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.parentKindId = parentKindId;
	}

	

	/** full constructor */
	public ArticleKind(Long articleKindId, String kindName, String kindPinyin,
			String status, Date updateTime, Date createTime, Long adminId,
			Long parentKindId) {
		super();
		this.articleKindId = articleKindId;
		this.kindName = kindName;
		this.kindPinyin = kindPinyin;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.adminId = adminId;
		this.parentKindId = parentKindId;
	}

	
	public Long getArticleKindId() {
		return articleKindId;
	}

	public void setArticleKindId(Long articleKindId) {
		this.articleKindId = articleKindId;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getKindPinyin() {
		return kindPinyin;
	}

	public void setKindPinyin(String kindPinyin) {
		this.kindPinyin = kindPinyin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getParentKindId() {
		return parentKindId;
	}

	public void setParentKindId(Long parentKindId) {
		this.parentKindId = parentKindId;
	}
	
	@Override
	public String toString() {
		return "ArticleKind [adminId=" + adminId + ", articleKindId="
				+ articleKindId + ", createTime=" + createTime + ", kindName="
				+ kindName + ", kindPinyin=" + kindPinyin + ", parentKindId="
				+ parentKindId + ", status=" + status + ", updateTime="
				+ updateTime + "]";
	}
	

}