package com.mins5.share.common.service;

/**
 * @author zhoutian
 * @since 2014-2-28
 */
public enum ReturnCode {

	SUCCESS(200, "成功"), PARAM_ERROR(250, "参数错误"), EXCEPTION(500, "异常");

	private int code;

	private String name;

	ReturnCode(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}

	public static ReturnCode valueOf(int code) {
		switch (code) {
		case 200:
			return SUCCESS;
		case 250:
			return PARAM_ERROR;
		case 500:
			return EXCEPTION;
		default:
			return null;
		}
	}

}
