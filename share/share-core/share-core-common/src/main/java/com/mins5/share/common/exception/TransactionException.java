package com.mins5.share.common.exception;

/**
 * @author mins5
 * @since 2014-3-1
 */
public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 5457041352665513217L;

	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
