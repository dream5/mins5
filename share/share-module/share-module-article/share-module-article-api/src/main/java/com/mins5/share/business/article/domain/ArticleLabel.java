package com.mins5.share.business.article.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;

/**
 * 
 * @author chenry
 * @since 2014-3-10
 */
public class ArticleLabel extends DomainObject {

	// Fields

	private static final long serialVersionUID = -3577098317867948427L;
	
	private Long labelId;
	private String labelName;
	private String status;
	private Date createTime;

	// Constructors

	/** default constructor */
	public ArticleLabel() {
	}

	/** minimal constructor */
	public ArticleLabel(String labelName, String status, Date createTime) {
		this.labelName = labelName;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public ArticleLabel(Long labelId,String labelName, String status, Date createTime) {
		this.labelId = labelId;
		this.labelName = labelName;
		this.status = status;
		this.createTime = createTime;
	}

	
	// Property accessors
	
	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ArticleLabel [createTime=" + createTime + ", labelId="
				+ labelId + ", labelName=" + labelName + ", status=" + status
				+ "]";
	}


}