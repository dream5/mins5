package com.mins5.share.business.article.domain;

import com.mins5.share.common.domain.DomainObject;


/**
 * 
 * @author chenry
 * @since 2014-3-9
 */
public class ArticleHot extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	
	private Long hotId;
	private Long articleId;
	private Long reprintCount;

	// Constructors

	/** default constructor */
	public ArticleHot() {
	}

	

	/** full constructor */
	public ArticleHot(Long hotId, Long articleId, Long reprintCount) {
		super();
		this.hotId = hotId;
		this.articleId = articleId;
		this.reprintCount = reprintCount;
	}
	

	public Long getHotId() {
		return hotId;
	}

	public void setHotId(Long hotId) {
		this.hotId = hotId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getReprintCount() {
		return reprintCount;
	}

	public void setReprintCount(Long reprintCount) {
		this.reprintCount = reprintCount;
	}



	@Override
	public String toString() {
		return "ArticleHot [articleId=" + articleId + ", hotId=" + hotId
				+ ", reprintCount=" + reprintCount + "]";
	}
	

}