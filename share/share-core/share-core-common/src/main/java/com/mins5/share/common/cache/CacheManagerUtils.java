/*    */package com.mins5.share.common.cache;

/*    */
/*    */public class CacheManagerUtils
/*    */{
	/* 10 */private static CacheManager cacheManager = null;

	/*    */
	/*    */public static CacheManager getCacheManager()
	/*    */{
		/* 17 */if (cacheManager == null) {
			/* 18 */cacheManager = new BasicCacheManager();
			/*    */}
		/* 20 */return cacheManager;
		/*    */}

	/*    */
	/*    */public static void setCacheManager(CacheManager cacheManager)
	/*    */{
		/* 28 */cacheManager = cacheManager;
		/*    */}
	/*    */
}

/*
 * Location:
 * E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPCore-1.2.0.jar
 * Qualified Name: com.cattsoft.dapcore.cache.CacheManagerUtils JD-Core Version:
 * 0.6.2
 */