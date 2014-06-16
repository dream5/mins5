/*    */package com.mins5.share.common.cache;

/*    */
/*    */import java.io.Serializable;
/*    */
import java.util.HashMap;
/*    */
import java.util.Map;

/*    */
/*    */public final class BasicCacheManager
/*    */implements CacheManager
/*    */{
	/* 8 */private static Map<String, Map<String, Serializable>> cacheManager = new HashMap();

	/*    */
	/*    */public synchronized <T extends Serializable> void put(
			String cacheName, String key, T value)
	/*    */{
		/* 18 */Map cacheMap = getCacheMap(cacheName);
		/* 19 */cacheMap.put(key, value);
		/* 20 */cacheManager.put(cacheName, cacheMap);
		/*    */}

	/*    */
	/*    */public Serializable get(String cacheName, String key)
	/*    */{
		/* 30 */Map cacheMap = getCacheMap(cacheName);
		/* 31 */return (Serializable) cacheMap.get(key);
		/*    */}

	/*    */
	/*    */public synchronized void remove(String cacheName, String key)
	/*    */{
		/* 40 */Map cacheMap = getCacheMap(cacheName);
		/* 41 */cacheMap.remove(key);
		/* 42 */cacheManager.put(cacheName, cacheMap);
		/*    */}

	/*    */
	/*    */public boolean containsKey(String cacheName, String key)
	/*    */{
		/* 52 */Map cacheMap = getCacheMap(cacheName);
		/* 53 */return cacheMap.containsKey(key);
		/*    */}

	/*    */
	/*    */public <T extends Serializable> boolean containsValue(
			String cacheName, T value)
	/*    */{
		/* 64 */Map cacheMap = getCacheMap(cacheName);
		/* 65 */return cacheMap.containsValue(value);
		/*    */}

	/*    */
	/*    */public Map<String, Serializable> getCacheMap(String cacheName)
	/*    */{
		/* 74 */Map cacheMap = (Map) cacheManager.get(cacheName);
		/* 75 */if (cacheMap == null) {
			/* 76 */cacheMap = new HashMap();
			/*    */}
		/* 78 */return cacheMap;
		/*    */}

	/*    */
	/*    */public void addCache(Object cache)
	/*    */{
		/*    */}

	/*    */
	/*    */public void removeCache(String cacheName)
	/*    */{
		/* 93 */cacheManager.remove(cacheName);
		/*    */}

	/*    */
	/*    */public void initialize()
	/*    */{
		/*    */}

	/*    */
	/*    */public void reInitialize()
	/*    */{
		/*    */}
	/*    */
}

/*
 * Location:
 * E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPCore-1.2.0.jar
 * Qualified Name: com.cattsoft.dapcore.cache.BasicCacheManager JD-Core Version:
 * 0.6.2
 */