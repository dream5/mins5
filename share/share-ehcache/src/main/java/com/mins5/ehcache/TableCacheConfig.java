package com.mins5.ehcache;

import net.sf.ehcache.config.CacheConfiguration;

import com.mins5.share.common.domain.DomainObject;

public class TableCacheConfig extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cacheName;
	private String sql;
	private CACHE_TYPE cacheType;
	private Class entityClass;
	private String keyProperty;
	private CacheConfiguration configuration;

	public String getCacheName() {
		return this.cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public CACHE_TYPE getCacheType() {
		return this.cacheType;
	}

	public void setCacheType(CACHE_TYPE cacheType) {
		this.cacheType = cacheType;
	}

	public Class getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public String getKeyProperty() {
		return this.keyProperty;
	}

	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	public CacheConfiguration getConfiguration() {
		this.configuration.setName(this.cacheName);
		return this.configuration;
	}

	public void setConfiguration(CacheConfiguration configuration) {
		this.configuration = configuration;
	}

	public static enum CACHE_TYPE {
		ID_VALUE, ID_ENTITY, ID_LIST;
	}
}