package com.mins5.share.business.article.domain;

import com.mins5.share.common.domain.DomainObject;

/**
 * @author auto_code
 * @since 2014-4-30 15:55:52
 */
public class Attachment extends DomainObject {

	private static final long serialVersionUID = 5454155825314635342L;

	/** 属性 begin **/
	private java.lang.Integer attachmentId; // 主键
	private java.lang.Integer articleId; // 对应文章ID，与其一对一关系
	private java.lang.String attachmentName; // 文件名
	private java.lang.String attachmentOldName; // 旧的文件名
	private java.lang.String attachmentType; // 后缀
	private java.util.Date createDate; // 创建时间
	private java.lang.String attachmenSts; // 是否有效，1有效，0无效
	private java.lang.String large; // 最大尺寸图片地址，对应首页banner
	private java.lang.String midSize; // 中型图片地址，对应文章开头图片
	private java.lang.String small; // 最小尺寸图片地址，对应详情页面推荐小图

	/** 属性 end **/

	public void setAttachmentId(java.lang.Integer value) {
		this.attachmentId = value;
	}

	public java.lang.Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setArticleId(java.lang.Integer value) {
		this.articleId = value;
	}

	public java.lang.Integer getArticleId() {
		return this.articleId;
	}

	public void setAttachmentName(java.lang.String value) {
		this.attachmentName = value;
	}

	public java.lang.String getAttachmentName() {
		return this.attachmentName;
	}

	public java.lang.String getAttachmentOldName() {
		return attachmentOldName;
	}

	public void setAttachmentOldName(java.lang.String attachmentOldName) {
		this.attachmentOldName = attachmentOldName;
	}

	public void setAttachmentType(java.lang.String value) {
		this.attachmentType = value;
	}

	public java.lang.String getAttachmentType() {
		return this.attachmentType;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setAttachmenSts(java.lang.String value) {
		this.attachmenSts = value;
	}

	public java.lang.String getAttachmenSts() {
		return this.attachmenSts;
	}

	public void setLarge(java.lang.String value) {
		this.large = value;
	}

	public java.lang.String getLarge() {
		return this.large;
	}

	public void setMidSize(java.lang.String value) {
		this.midSize = value;
	}

	public java.lang.String getMidSize() {
		return this.midSize;
	}

	public void setSmall(java.lang.String value) {
		this.small = value;
	}

	public java.lang.String getSmall() {
		return this.small;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Attachment[");
		buffer.append("attachmentId=" + attachmentId);
		buffer.append(",");
		buffer.append("articleId=" + articleId);
		buffer.append(",");
		buffer.append("attachmentName=" + attachmentName);
		buffer.append(",");
		buffer.append("attachmentType=" + attachmentType);
		buffer.append(",");
		buffer.append("createDate=" + createDate);
		buffer.append(",");
		buffer.append("attachmenSts=" + attachmenSts);
		buffer.append(",");
		buffer.append("large=" + large);
		buffer.append(",");
		buffer.append("midSize=" + midSize);
		buffer.append(",");
		buffer.append("small=" + small);
		buffer.append("]");
		return buffer.toString();
	}

}
