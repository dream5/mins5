package com.mins5.share.business.article.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;

/**
 * 
 * @author chenry
 * @since 2014-3-10
 */
public class ArticleLabelRel extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	// Fields

	private Long articleLabelRelId;
	private Long labelId;
	private Long articleId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public ArticleLabelRel() {
	}

	public ArticleLabelRel(Long articleLabelRelId, Long labelId,
			Long articleId, Date createTime) {
		super();
		this.articleLabelRelId = articleLabelRelId;
		this.labelId = labelId;
		this.articleId = articleId;
		this.createTime = createTime;
	}

	public Long getArticleLabelRelId() {
		return articleLabelRelId;
	}

	public void setArticleLabelRelId(Long articleLabelRelId) {
		this.articleLabelRelId = articleLabelRelId;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ArticleLabelRel [articleLabelRelId=" + articleLabelRelId
				+ ", labelId=" + labelId + ", articleId=" + articleId
				+ ", createTime=" + createTime + "]";
	}

}