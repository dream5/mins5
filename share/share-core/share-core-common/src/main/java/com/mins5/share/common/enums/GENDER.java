package com.mins5.share.common.enums;

/**
 * @author zhoutian
 * @since 2014-2-28
 */
public enum GENDER {

	MALE(0, "男"), FEMALE(1, "女"), SECRET(2, "保密");
	
	private int code;
	
	private String name;
	
	GENDER(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static GENDER valueOf(int code) {
		switch (code) {
		case 0:
			return MALE;
		case 1:
			return FEMALE;
		case 2:
			return SECRET;
		default:
			return null;
		}
	}
}
