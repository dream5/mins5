package com.mins5.share.common.cache;

import java.io.Serializable;
import java.util.Map;

public interface CacheManager {
	public <T extends Serializable> void put(String paramString1,
			String paramString2, T paramT);

	public Serializable get(String paramString1, String paramString2);

	public void remove(String paramString1, String paramString2);

	public boolean containsKey(String paramString1, String paramString2);

	public <T extends Serializable> boolean containsValue(String paramString,
			T paramT);

	public Map<String, Serializable> getCacheMap(String paramString);

	public void addCache(Object paramObject);

	public void removeCache(String paramString);

	public void initialize();

	public void reInitialize();
}
