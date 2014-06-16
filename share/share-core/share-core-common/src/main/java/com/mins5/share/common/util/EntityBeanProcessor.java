package com.mins5.share.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.mins5.share.common.cache.CacheManager;
import com.mins5.share.common.cache.CacheManagerUtils;

public class EntityBeanProcessor extends BeanProcessor {
	private static final String propClass = "class";
	private static final String JDBC_BEAN_INFO_CACHE = "jdbc.cache.beaninfo";
	protected static final int PROPERTY_NOT_FOUND = -1;
	/* 117 */private static final Map<Class<?>, Object> primitiveDefaults = new HashMap();

	protected static PropertyDescriptor[] propertyDescriptors(Class<?> c)
			throws SQLException {
		/* 46 */CacheManager cacheManager = CacheManagerUtils.getCacheManager();
		/* 47 */if ((cacheManager.containsKey("jdbc.cache.beaninfo",
				c.getName()))
				&& (cacheManager.get("jdbc.cache.beaninfo", c.getName()) != null)) {
			/* 49 */return (PropertyDescriptor[]) cacheManager.get(
					"jdbc.cache.beaninfo", c.getName());
		}

		/* 52 */BeanInfo beanInfo = null;
		try {
			/* 54 */beanInfo = Introspector.getBeanInfo(c);
		} catch (IntrospectionException e) {
			/* 57 */throw new SQLException("获取实体Bean的属性信息失败（" + c.getName()
					+ "）: " + e.getMessage());
		}

		/* 60 */PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		/* 61 */for (int i = 0; i < props.length; i++) {
			/* 62 */if (StringUtils.equals(props[i].getName(), "class")) {
				/* 63 */props = (PropertyDescriptor[]) ArrayUtils.remove(props,
						i);
				/* 64 */break;
			}
		}

		/* 68 */cacheManager.put("jdbc.cache.beaninfo", c.getName(), props);

		/* 70 */return props;
	}

	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
			PropertyDescriptor[] props) throws SQLException {
		/* 84 */int cols = rsmd.getColumnCount();
		/* 85 */int[] columnToProperty = new int[cols + 1];
		/* 86 */Arrays.fill(columnToProperty, -1);

		/* 88 */for (int col = 1; col <= cols; col++) {
			/* 89 */String columnName = rsmd.getColumnLabel(col);
			/* 90 */if ((null == columnName) || (0 == columnName.length())) {
				/* 91 */columnName = rsmd.getColumnName(col);
			}
			/* 93 */for (int i = 0; i < props.length; i++) {
				/* 94 */columnName = StringUtils.remove(columnName, '_');
				/* 95 */if (columnName.equalsIgnoreCase(props[i].getName())) {
					/* 96 */columnToProperty[col] = i;
					/* 97 */break;
				}
			}
		}

		/* 102 */return columnToProperty;
	}

	public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {
		/* 172 */PropertyDescriptor[] props = propertyDescriptors(type);

		/* 174 */ResultSetMetaData rsmd = rs.getMetaData();
		/* 175 */int[] columnToProperty = mapColumnsToProperties(rsmd, props);

		/* 177 */return createBean(rs, type, props, columnToProperty);
	}

	public <T> List<T> toBeanList(ResultSet rs, Class<T> type)
			throws SQLException {
		/* 215 */List results = new ArrayList();

		/* 217 */if (!rs.next()) {
			/* 218 */return results;
		}

		/* 221 */PropertyDescriptor[] props = propertyDescriptors(type);
		/* 222 */ResultSetMetaData rsmd = rs.getMetaData();
		/* 223 */int[] columnToProperty = mapColumnsToProperties(rsmd, props);
		do {
			/* 226 */results.add(createBean(rs, type, props, columnToProperty));
		}
		/* 228 */while (rs.next());

		/* 230 */return results;
	}

	private <T> T createBean(ResultSet rs, Class<T> type,
			PropertyDescriptor[] props, int[] columnToProperty)
			throws SQLException {
		/* 247 */Object bean = newInstance(type);

		/* 249 */for (int i = 1; i < columnToProperty.length; i++) {
			/* 250 */if (columnToProperty[i] != -1) {
				/* 254 */PropertyDescriptor prop = props[columnToProperty[i]];
				/* 255 */Class propType = prop.getPropertyType();

				/* 257 */Object value = processColumn(rs, i, propType);

				/* 259 */if ((propType != null) && (value == null)
						&& (propType.isPrimitive())) {
					/* 260 */value = primitiveDefaults.get(propType);
				}

				/* 263 */callSetter(bean, prop, value);
			}
		}
		/* 266 */return (T) bean;
	}

	private void callSetter(Object target, PropertyDescriptor prop, Object value)
			throws SQLException {
		/* 281 */Method setter = prop.getWriteMethod();

		/* 283 */if (setter == null) {
			/* 284 */return;
		}

		/* 287 */Class[] params = setter.getParameterTypes();
		try {
			/* 290 */if ((value != null) &&
			/* 291 */((value instanceof java.util.Date))) {
				/* 292 */if (params[0].getName().equals("java.sql.Date"))
					/* 293 */value = new java.sql.Date(
							((java.util.Date) value).getTime());
				/* 294 */else if (params[0].getName().equals("java.sql.Time"))
					/* 295 */value = new Time(
							((java.util.Date) value).getTime());
				/* 296 */else if (params[0].getName().equals(
						"java.sql.Timestamp")) {
					/* 297 */value = new Timestamp(
							((java.util.Date) value).getTime());
				}

			}

			/* 303 */if (isCompatibleType(value, params[0]))
				/* 304 */setter.invoke(target, new Object[] { value });
			else {
				/* 306 */throw new SQLException("Cannot set " + prop.getName()
						+ ": incompatible types.");
			}
		} catch (IllegalArgumentException e) {
			/* 311 */throw new SQLException("Cannot set " + prop.getName()
					+ ": " + e.getMessage());
		} catch (IllegalAccessException e) {
			/* 315 */throw new SQLException("Cannot set " + prop.getName()
					+ ": " + e.getMessage());
		} catch (InvocationTargetException e) {
			/* 319 */throw new SQLException("Cannot set " + prop.getName()
					+ ": " + e.getMessage());
		}
	}

	private boolean isCompatibleType(Object value, Class<?> type) {
		/* 337 */if ((value == null) || (type.isInstance(value))) {
			/* 338 */return true;
		}
		/* 340 */if ((type.equals(Integer.TYPE))
				&& (Integer.class.isInstance(value)))
			/* 341 */return true;
		/* 342 */if ((type.equals(Long.TYPE)) && (Long.class.isInstance(value)))
			/* 343 */return true;
		/* 344 */if ((type.equals(Double.TYPE))
				&& (Double.class.isInstance(value)))
			/* 345 */return true;
		/* 346 */if ((type.equals(Float.TYPE))
				&& (Float.class.isInstance(value)))
			/* 347 */return true;
		/* 348 */if ((type.equals(Short.TYPE))
				&& (Short.class.isInstance(value)))
			/* 349 */return true;
		/* 350 */if ((type.equals(Byte.TYPE)) && (Byte.class.isInstance(value)))
			/* 351 */return true;
		/* 352 */if ((type.equals(Character.TYPE))
				&& (Character.class.isInstance(value)))
			/* 353 */return true;
		/* 354 */if ((type.equals(Boolean.TYPE))
				&& (Boolean.class.isInstance(value))) {
			/* 355 */return true;
		}
		/* 357 */return false;
	}

	protected <T> T newInstance(Class<T> c) throws SQLException {
		try {
			/* 373 */return c.newInstance();
		} catch (InstantiationException e) {
			/* 376 */throw new SQLException("Cannot create " + c.getName()
					+ ": " + e.getMessage());
		} catch (IllegalAccessException e) {
			/* 380 */throw new SQLException("Cannot create " + c.getName()
					+ ": " + e.getMessage());
		}
	}

	protected Object processColumn(ResultSet rs, int index, Class<?> propType)
			throws SQLException {
		/* 410 */if ((!propType.isPrimitive()) && (rs.getObject(index) == null)) {
			/* 411 */return null;
		}

		/* 414 */if (propType.equals(String.class))
			/* 415 */return rs.getString(index);
		/* 416 */if ((propType.equals(Integer.TYPE))
				|| (propType.equals(Integer.class))) {
			/* 418 */return Integer.valueOf(rs.getInt(index));
			/* 419 */}
		if ((propType.equals(Boolean.TYPE)) || (propType.equals(Boolean.class))) {
			/* 421 */return Boolean.valueOf(rs.getBoolean(index));
			/* 422 */}
		if ((propType.equals(Long.TYPE)) || (propType.equals(Long.class)))
			/* 423 */return Long.valueOf(rs.getLong(index));
		/* 424 */if ((propType.equals(Double.TYPE))
				|| (propType.equals(Double.class))) {
			/* 426 */return Double.valueOf(rs.getDouble(index));
			/* 427 */}
		if ((propType.equals(Float.TYPE)) || (propType.equals(Float.class))) {
			/* 429 */return Float.valueOf(rs.getFloat(index));
			/* 430 */}
		if ((propType.equals(Short.TYPE)) || (propType.equals(Short.class))) {
			/* 432 */return Short.valueOf(rs.getShort(index));
			/* 433 */}
		if ((propType.equals(Byte.TYPE)) || (propType.equals(Byte.class)))
			/* 434 */return Byte.valueOf(rs.getByte(index));
		/* 435 */if (propType.equals(Timestamp.class)) {
			/* 436 */return rs.getTimestamp(index);
		}
		/* 438 */if (propType.equals(Blob.class)) {
			try {
				/* 440 */Blob blob = rs.getBlob(index);
				/* 441 */InputStream is = blob.getBinaryStream();
				/* 442 */ByteArrayOutputStream baos = new ByteArrayOutputStream();
				/* 443 */int b = is.read();
				/* 444 */while (b != -1) {
					/* 445 */baos.write(b);
					/* 446 */b = is.read();
				}
				/* 448 */byte[] content = baos.toByteArray();

				/* 450 */is.close();
				/* 451 */return content;
			} catch (IOException e) {
				/* 454 */throw new SQLException("获取Blob类型字段时发生错误："
						+ e.getMessage());
			}
		}
		/* 457 */return rs.getObject(index);
	}

	static {
		/* 120 */primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
		/* 121 */primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
		/* 122 */primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
		/* 123 */primitiveDefaults.put(Float.TYPE, Float.valueOf(0.0F));
		/* 124 */primitiveDefaults.put(Double.TYPE, Double.valueOf(0.0D));
		/* 125 */primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
		/* 126 */primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
		/* 127 */primitiveDefaults.put(Character.TYPE,
				Character.valueOf('\000'));
	}
}

/*
 * Location: C:\Users\Chenriyun\Desktop\JDBCComponent-1.1.0.jar Qualified Name:
 * com.cattsoft.jdbccomponent.EntityBeanProcessor JD-Core Version: 0.6.2
 */