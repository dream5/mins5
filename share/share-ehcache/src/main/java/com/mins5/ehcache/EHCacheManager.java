package com.mins5.ehcache;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mins5.share.common.cache.CacheManager;
import com.mins5.share.common.dao.DbCommonDao;
import com.mins5.share.common.domain.DomainObject;

public class EHCacheManager implements CacheManager {
	private static Log log = LogFactory.getLog(EHCacheManager.class);
	private net.sf.ehcache.CacheManager ehCacheManager;
	private List<TableCacheConfig> configuration;
	private DbCommonDao dbUtils;

	public void setEhCacheManager(net.sf.ehcache.CacheManager ehCacheManager) {
		this.ehCacheManager = ehCacheManager;
	}

	public void setConfiguration(List<TableCacheConfig> configuration) {
		this.configuration = configuration;
	}

	public void setDbUtils(DbCommonDao dbUtils) {
		this.dbUtils = dbUtils;
	}

	public <T extends DomainObject> List<T> queryList(String paramString,
			Class<T> paramClass, Object[] paramArrayOfObject)
			throws SQLException {
		return dbUtils.queryList(paramString, paramClass, paramArrayOfObject);
	}

	public int execute(String paramString, Object[] paramArrayOfObject)
			throws SQLException {
		return dbUtils.execute(paramString, paramArrayOfObject);
	}

	public void callProcedure(String paramString, Object[] paramArrayOfObject1,
			Object[] paramArrayOfObject2) throws SQLException {
		dbUtils.callProcedure(paramString, paramArrayOfObject1,
				paramArrayOfObject2);
	}

	public long queryNumValue(String paramString, Object[] paramArrayOfObject)
			throws SQLException {
		return dbUtils.queryNumValue(paramString, paramArrayOfObject);
	}

	public <T extends DomainObject> int insert(T paramT) throws SQLException {
		return dbUtils.insert(paramT);
	}

	public <T extends DomainObject> int update(T paramT,
			String[] paramArrayOfString) throws SQLException {
		return dbUtils.update(paramT, paramArrayOfString);
	}

	public long generateSequenceValue(String paramString) throws SQLException {
		return dbUtils.generateSequenceValue(paramString);
	}

	public synchronized <T extends Serializable> void put(String cacheName,
			String key, T value) {
		if (!this.ehCacheManager.cacheExists(cacheName)) {
			addCache(cacheName);
		}
		Cache cache = this.ehCacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	public Serializable get(String cacheName, String key) {
		if (!this.ehCacheManager.cacheExists(cacheName)) {
			return null;
		}

		Serializable value = null;

		Cache cache = this.ehCacheManager.getCache(cacheName);
		Element element = cache.get(key);
		if (element != null) {
			value = element.getValue();
		}

		return value;
	}

	public synchronized void remove(String cacheName, String key) {
		if (!this.ehCacheManager.cacheExists(cacheName)) {
			return;
		}

		Cache cache = this.ehCacheManager.getCache(cacheName);
		cache.remove(key);
	}

	public boolean containsKey(String cacheName, String key) {
		if (!this.ehCacheManager.cacheExists(cacheName)) {
			return false;
		}

		Cache cache = this.ehCacheManager.getCache(cacheName);
		return cache.isKeyInCache(key);
	}

	public <T extends Serializable> boolean containsValue(String cacheName,
			T value) {
		if (!this.ehCacheManager.cacheExists(cacheName)) {
			return false;
		}

		Cache cache = this.ehCacheManager.getCache(cacheName);
		return cache.isValueInCache(value);
	}

	public Map<String, Serializable> getCacheMap(String cacheName) {
		Map cacheMap = new HashMap();
		Cache cache;
		if (this.ehCacheManager.cacheExists(cacheName)) {
			cache = this.ehCacheManager.getCache(cacheName);
			List<String> keys = cache.getKeys();
			for (String key : keys) {
				cacheMap.put(key, cache.get(key).getValue());
			}
		}

		return cacheMap;
	}

	public void shutdown() {
		this.ehCacheManager.shutdown();
	}

	private void addCache(String cacheName) {
		this.ehCacheManager.addCache(cacheName);
	}

	public void addCache(Object cache) {
		this.ehCacheManager.addCache((Cache) cache);
	}

	public void removeCache(String cacheName) {
		this.ehCacheManager.removeCache(cacheName);
	}

	public void initialize() {
		log.info("......开始初始化数据库表缓存......");
		if (this.configuration == null) {
			this.configuration = new ArrayList();
		}
		for (TableCacheConfig config : this.configuration) {
			doCacheInitializing(config);
		}
		log.info("......完成初始化数据库表缓存......");
	}

	public void reInitialize() {
		log.info("......开始重新初始化数据库表缓存......");
		for (TableCacheConfig config : this.configuration) {
			log.info("......重新初始化缓存：删除缓存" + config.getCacheName() + "开始执行。");
			removeCache(config.getCacheName());
			log.info("......重新初始化缓存：删除缓存" + config.getCacheName() + "完成。");
			doCacheInitializing(config);
		}
		log.info("......完成重新初始化数据库表缓存......");
	}

	private void doCacheInitializing(TableCacheConfig config) {
		if (config.getCacheType().equals(TableCacheConfig.CACHE_TYPE.ID_VALUE)) {
			doIdValueCacheInit(config);
		} else if (config.getCacheType().equals(
				TableCacheConfig.CACHE_TYPE.ID_ENTITY)) {
			doIdDomainObjectCacheInit(config);
		} else if (config.getCacheType().equals(
				TableCacheConfig.CACHE_TYPE.ID_LIST))
			doIdListCacheInit(config);
	}

	private void doIdValueCacheInit(TableCacheConfig config) {
		log.info("......初始化缓存：" + config.getCacheName() + "开始执行。");
		try {
			List<IdValueResultSet> rsList = this.dbUtils.queryList(
					config.getSql(), IdValueResultSet.class, new Object[0]);
			if (config.getConfiguration() != null) {
				CacheConfiguration.CacheEventListenerFactoryConfiguration eventListenerConfig = new CacheConfiguration.CacheEventListenerFactoryConfiguration();
				eventListenerConfig
						.setClass("net.sf.ehcache.distribution.RMICacheReplicatorFactory");
				eventListenerConfig
						.setProperties("replicateAsynchronously=true,replicatePuts=true,replicateUpdates=true,replicateUpdatesViaCopy=true,replicateRemovals=true");

				CacheConfiguration con = config.getConfiguration();
				con.addCacheEventListenerFactory(eventListenerConfig);
				Cache cache = new Cache(con);
				addCache(cache);
			}

			log.info("......初始化缓存：" + config.getCacheName() + "，按照配置信息创建缓存成功。");

			long count = 0L;
			for (IdValueResultSet record : rsList) {
				put(config.getCacheName(), record.getId(), record.getValue());
				count += 1L;
			}
			log.info("......初始化缓存：" + config.getCacheName() + "完成，共加入" + count
					+ "个缓存内容。");
		} catch (SQLException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		}
	}

	private void doIdDomainObjectCacheInit(TableCacheConfig config) {
		log.info("......初始化缓存：" + config.getCacheName() + "开始执行。");
		try {
			Class clazz = config.getEntityClass();

			String[] keyPropertys = StringUtils.split(config.getKeyProperty());
			Method[] getters = new Method[keyPropertys.length];
			for (int i = 0; i < keyPropertys.length; i++) {
				getters[i] = clazz.getDeclaredMethod(
						"get" + StringUtils.capitalize(keyPropertys[i]),
						new Class[0]);
			}

			List<DomainObject> rsList = this.dbUtils.queryList(config.getSql(),
					clazz, new Object[0]);
			if (config.getConfiguration() != null) {
				CacheConfiguration.CacheEventListenerFactoryConfiguration eventListenerConfig = new CacheConfiguration.CacheEventListenerFactoryConfiguration();
				eventListenerConfig
						.setClass("net.sf.ehcache.distribution.RMICacheReplicatorFactory");
				eventListenerConfig
						.setProperties("replicateAsynchronously=true,replicatePuts=true,replicateUpdates=true,replicateUpdatesViaCopy=true,replicateRemovals=true");

				CacheConfiguration con = config.getConfiguration();
				con.addCacheEventListenerFactory(eventListenerConfig);
				Cache cache = new Cache(con);
				addCache(cache);
			}

			log.info("......初始化缓存：" + config.getCacheName() + "，按照配置信息创建缓存成功。");

			long count = 0L;
			String keyProperty = null;
			for (DomainObject record : rsList) {
				keyProperty = "";
				for (Method getter : getters) {
					keyProperty = keyProperty
							+ getter.invoke(record, new Object[0]).toString();
					keyProperty = keyProperty + ".";
				}
				keyProperty = StringUtils.removeEnd(keyProperty, ".");

				put(config.getCacheName(), keyProperty, record);
				count += 1L;
			}
			log.info("......初始化缓存：" + config.getCacheName() + "完成，共加入" + count
					+ "个缓存内容。");
		} catch (SQLException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		} catch (NoSuchMethodException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		}
	}

	private void doIdListCacheInit(TableCacheConfig config) {
		log.info("......初始化缓存：" + config.getCacheName() + "开始执行。");
		try {
			Class clazz = config.getEntityClass();

			String[] keyPropertys = StringUtils.split(config.getKeyProperty());
			Method[] getters = new Method[keyPropertys.length];
			for (int i = 0; i < keyPropertys.length; i++) {
				getters[i] = clazz.getDeclaredMethod(
						"get" + StringUtils.capitalize(keyPropertys[i]),
						new Class[0]);
			}

			List<DomainObject> rsList = this.dbUtils.queryList(config.getSql(),
					clazz, new Object[0]);
			if (config.getConfiguration() != null) {
				CacheConfiguration.CacheEventListenerFactoryConfiguration eventListenerConfig = new CacheConfiguration.CacheEventListenerFactoryConfiguration();
				eventListenerConfig
						.setClass("net.sf.ehcache.distribution.RMICacheReplicatorFactory");
				eventListenerConfig
						.setProperties("replicateAsynchronously=true,replicatePuts=true,replicateUpdates=true,replicateUpdatesViaCopy=true,replicateRemovals=true");

				CacheConfiguration con = config.getConfiguration();
				con.addCacheEventListenerFactory(eventListenerConfig);
				Cache cache = new Cache(con);
				addCache(cache);
			}

			log.info("......初始化缓存：" + config.getCacheName() + "，按照配置信息创建缓存成功。");

			long count = 0L;
			List valueList = null;
			String keyProperty = null;
			for (DomainObject record : rsList) {
				keyProperty = "";
				for (Method getter : getters) {
					keyProperty = keyProperty
							+ getter.invoke(record, new Object[0]).toString();
					keyProperty = keyProperty + ".";
				}
				keyProperty = StringUtils.removeEnd(keyProperty, ".");

				valueList = (List) get(config.getCacheName(), keyProperty);
				if (valueList == null) {
					valueList = new ArrayList();
				}

				valueList.add(record);
				put(config.getCacheName(), keyProperty,
						(Serializable) valueList);
				count += 1L;
			}
			log.info("......初始化缓存：" + config.getCacheName() + "完成，共加入" + count
					+ "个缓存内容。");
		} catch (SQLException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		} catch (NoSuchMethodException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			log.error("......初始化缓存：" + config.getCacheName() + "失败："
					+ e.getMessage());
		}
	}
}