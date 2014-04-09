/**
 * 
 */
package com.mins5.share.business.article.enums;


/**
 * 文章状态枚举
 * @author zhoutian
 * @since 2014年3月14日
 */
public enum ARTICLE_STATUS {

	WAITING_CHECK(0, "待审核"), PASSED_CHECK(1, "通过审核"), PUBLISHED(2, "已发布"), REMOVED(3, "已下架");
	
	private int code;
	
	private String name;
	
	ARTICLE_STATUS(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static ARTICLE_STATUS valueOf(int code) {
		switch (code) {
		case 0:
			return WAITING_CHECK;
		case 1:
			return PASSED_CHECK;
		case 2:
			return PUBLISHED;
		case 3:
			return REMOVED;
		default:
			return null;
		}
	}
}
