/*    */package com.mins5.share.common.cache;

/*    */
/* 
 /*    */
/*    */public class CacheProcessorException extends RuntimeException
/*    */{
	/*    */public CacheProcessorException()
	/*    */{
		/*    */}

	/*    */
	/*    */public CacheProcessorException(String msg)
	/*    */{
		/* 14 */super(msg);
		/*    */}

	/*    */
	/*    */public CacheProcessorException(String msg, Throwable cause) {
		/* 18 */super(msg, cause);
		/*    */}

	/*    */
	/*    */public CacheProcessorException(Throwable cause) {
		/* 22 */super(cause);
		/*    */}
	/*    */
}

/*
 * Location:
 * E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPCore-1.2.0.jar
 * Qualified Name: com.cattsoft.dapcore.cache.CacheProcessorException JD-Core
 * Version: 0.6.2
 */