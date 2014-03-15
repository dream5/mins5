package com.mins5.share.business.article.domain;


import java.util.Date;

import com.mins5.share.business.article.enums.ARTICLE_STATUS;
import com.mins5.share.common.domain.DomainObject;

/**
 * 
 * @author chenry
 * @since 2014-3-15
 */
public class ArticleRecommend extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private Long recommendId;
	private String recommendPosition;
	private Long recommendSort;
	private Long articleId;


	// Constructors

	/** default constructor */
	public ArticleRecommend() {
	}

	/** full constructor */
	public ArticleRecommend(Long recommendId, String recommendPosition,
			Long recommendSort, Long articleId) {
		super();
		this.recommendId = recommendId;
		this.recommendPosition = recommendPosition;
		this.recommendSort = recommendSort;
		this.articleId = articleId;
	}


	public Long getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Long recommendId) {
		this.recommendId = recommendId;
	}

	public String getRecommendPosition() {
		return recommendPosition;
	}

	public void setRecommendPosition(String recommendPosition) {
		this.recommendPosition = recommendPosition;
	}

	public Long getRecommendSort() {
		return recommendSort;
	}

	public void setRecommendSort(Long recommendSort) {
		this.recommendSort = recommendSort;
	}

	public Long getArticleId() {
		return articleId;
	}



	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "ArticleRecommend [articleId=" + articleId + ", recommendId="
				+ recommendId + ", recommendPosition=" + recommendPosition
				+ ", recommendSort=" + recommendSort + "]";
	}
	
	

}