package com.mins5.share.business.article.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;

/**
 * 
 * @author chenry
 * @since 2014-3-10
 */
public class ArticleLabelRel extends DomainObject {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields

	private Long articleLabelRelId;
	private Long articleLabelId;
	private Long articleId;
	private Date careteTime;

	// Constructors

	/** default constructor */
	public ArticleLabelRel() {
	}

	/** full constructor */
	public ArticleLabelRel(Long articleLabelRelId,
			Long articleLabelId, Long articleId, Date careteTime) {
		this.articleLabelRelId = articleLabelRelId;
		this.articleLabelId = articleLabelId;
		this.articleId = articleId;
		this.careteTime = careteTime;
	}
	
	
	// Property accessors

	public Long getArticleLabelRelId() {
		return articleLabelRelId;
	}

	public void setArticleLabelRelId(Long articleLabelRelId) {
		this.articleLabelRelId = articleLabelRelId;
	}

	public Long getArticleLabelId() {
		return articleLabelId;
	}

	public void setArticleLabelId(Long articleLabelId) {
		this.articleLabelId = articleLabelId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Date getCareteTime() {
		return careteTime;
	}

	public void setCareteTime(Date careteTime) {
		this.careteTime = careteTime;
	}

	@Override
	public String toString() {
		return "ArticleLabelRel [articleId=" + articleId + ", articleLabelId="
				+ articleLabelId + ", articleLabelRelId=" + articleLabelRelId
				+ ", careteTime=" + careteTime + "]";
	}
	

}