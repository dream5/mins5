package com.mins5.share.business.article.domain;

import java.util.Date;

import com.mins5.share.common.domain.DomainObject;

/**
 * 
 * @author chenry
 * @since 2014-3-9
 */
public class Article extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	
	private Long articleId;
	private String articleTitle;
	private String articleContent;
	private String articleUrl;
	private String articleFrom;
	private String articleAuthor;
	private String status;
	private Date updateTime;
	private Date createTime;
	private String isOriginal;
	private String isValid;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(String articleTitle, String articleContent,
			String articleAuthor, String status, Date updateTime,
			Date createTime, String isOriginal, String isValid) {
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleAuthor = articleAuthor;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.isOriginal = isOriginal;
		this.isValid = isValid;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getArticleFrom() {
		return articleFrom;
	}

	public void setArticleFrom(String articleFrom) {
		this.articleFrom = articleFrom;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
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

	public String getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(String isOriginal) {
		this.isOriginal = isOriginal;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "Article [articleAuthor=" + articleAuthor + ", articleContent="
				+ articleContent + ", articleFrom=" + articleFrom
				+ ", articleId=" + articleId + ", articleTitle=" + articleTitle
				+ ", articleUrl=" + articleUrl + ", createTime=" + createTime
				+ ", isOriginal=" + isOriginal + ", isValid=" + isValid
				+ ", status=" + status + ", updateTime=" + updateTime + "]";
	}

}