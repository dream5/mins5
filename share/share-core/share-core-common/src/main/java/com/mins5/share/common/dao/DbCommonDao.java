package com.mins5.share.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.mins5.share.common.domain.DomainObject;

public interface DbCommonDao {
	public <T extends DomainObject> List<T> queryList(String paramString,
			Class<T> paramClass, Object[] paramArrayOfObject)
			throws SQLException;

	// public <T extends DomainObject> T queryBean(String paramString,
	// Class<T> paramClass, Object[] paramArrayOfObject)
	// throws SQLException;
	//
	// public <T extends DomainObject> PagingResult<T> queryList(
	// String paramString, Class<T> paramClass,
	// PagingInfo paramPagingInfo, Object[] paramArrayOfObject)
	// throws SQLException;

	public int execute(String paramString, Object[] paramArrayOfObject)
			throws SQLException;

	public void callProcedure(String paramString, Object[] paramArrayOfObject1,
			Object[] paramArrayOfObject2) throws SQLException;

	public long queryNumValue(String paramString, Object[] paramArrayOfObject)
			throws SQLException;

	public <T extends DomainObject> int insert(T paramT) throws SQLException;

	public <T extends DomainObject> int update(T paramT,
			String[] paramArrayOfString) throws SQLException;

	public long generateSequenceValue(String paramString) throws SQLException;
}
