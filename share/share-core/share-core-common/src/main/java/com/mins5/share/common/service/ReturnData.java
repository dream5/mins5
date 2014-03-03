package com.mins5.share.common.service;

/**
 * @author zhoutian
 * @since 2014-2-28
 * @param <T>
 */
public class ReturnData<T> {

	private int returnCode;
	
	private T resultData;

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}
	
}
