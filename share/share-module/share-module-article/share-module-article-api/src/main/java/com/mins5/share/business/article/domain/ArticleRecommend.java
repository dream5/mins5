package com.mins5.share.business.article.domain;


import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
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
	private RECOMMEND_POSITION recommendPosition;
	private Long recommendSort;
	private Long articleId;


	// Constructors

	/** default constructor */
	public ArticleRecommend() {
	}

	/** full constructor */
	public ArticleRecommend(Long recommendId, RECOMMEND_POSITION recommendPosition,
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

	public RECOMMEND_POSITION getRecommendPosition() {
		return recommendPosition;
	}

	public void setRecommendPosition(RECOMMEND_POSITION recommendPosition) {
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