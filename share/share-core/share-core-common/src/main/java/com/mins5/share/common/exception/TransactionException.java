package com.mins5.share.common.exception;

import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014-3-1
 */
public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 5457041352665513217L;

	private ReturnData<VOID> returnData;
	
	public TransactionException() {
		
	}
	
	public TransactionException(int returnCode) {
		returnData = new ReturnData<VOID>();
		returnData.setReturnCode(returnCode);
	}
	
	public TransactionException(int returnCode, String message) {
		super(message);
		returnData = new ReturnData<VOID>();
		returnData.setReturnCode(returnCode);
    }

    public TransactionException(int returnCode, String message, Throwable cause) {
        super(message, cause);
        returnData = new ReturnData<VOID>();
		returnData.setReturnCode(returnCode);
    }

    public TransactionException(int returnCode, Throwable cause) {
        super(cause);
        returnData = new ReturnData<VOID>();
		returnData.setReturnCode(returnCode);
    }
	
	public ReturnData<VOID> getReturnData() {
		return returnData;
	}
	
}
