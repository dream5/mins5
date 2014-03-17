package com.mins5.share.business.article.enums;

/**
 * 推荐位
 * @author zhanglin
 * @since 20140317
 */
public enum RECOMMEND_POSITION {

	INDEX_POSITION(0, "首页"), DETAIL_POSITION(1, "详情页"), LIST_POSITION(2, "列表页");
	
	private int code;
	
	private String name;
	
	RECOMMEND_POSITION(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static RECOMMEND_POSITION valueOf(int code) {
		switch (code) {
		case 0:
			return INDEX_POSITION;
		case 1:
			return DETAIL_POSITION;
		case 2:
			return LIST_POSITION;
		default:
			return null;
		}
	}
}
