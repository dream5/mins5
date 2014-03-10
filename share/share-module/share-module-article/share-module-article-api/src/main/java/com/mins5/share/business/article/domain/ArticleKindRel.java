package com.mins5.share.business.article.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;


public class ArticleKindRel extends DomainObject {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long articleKindRelId;
	private Long articleId;
	private Long articleKindId;
	private String status;
	private Date updateTime;
	private Date createTime;
	private Long adminId;

	// Constructors

	/** default constructor */
	public ArticleKindRel() {
	}

	/** minimal constructor */
	public ArticleKindRel(Long articleKindId, String status,
			Date updateTime, Date createTime) {
		this.articleKindId = articleKindId;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public ArticleKindRel(Long articleId, Long articleKindId,
			String status, Date updateTime, Date createTime, Long adminId) {
		this.articleId = articleId;
		this.articleKindId = articleKindId;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.adminId = adminId;
	}

	// Property accessors
	
	public Long getArticleKindRelId() {
		return articleKindRelId;
	}

	public void setArticleKindRelId(Long articleKindRelId) {
		this.articleKindRelId = articleKindRelId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getArticleKindId() {
		return articleKindId;
	}

	public void setArticleKindId(Long articleKindId) {
		this.articleKindId = articleKindId;
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

	@Override
	public String toString() {
		return "ArticleKindRel [adminId=" + adminId + ", articleId="
				+ articleId + ", articleKindId=" + articleKindId
				+ ", articleKindRelId=" + articleKindRelId + ", createTime="
				+ createTime + ", status=" + status + ", updateTime="
				+ updateTime + "]";
	}

}