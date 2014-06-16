package com.mins5.share.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.mins5.share.common.dao.DbCommonDao;
import com.mins5.share.common.domain.DomainObject;

public class MyDbUtils implements DbCommonDao {

	private static Log log = LogFactory.getLog(MyDbUtils.class);

	private static final BeanProcessor convert = new EntityBeanProcessor();

	private static final BasicRowProcessor rowProcessor = new BasicRowProcessor(
			convert);

	protected DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return DataSourceUtils.getConnection(dataSource);
	}

	public <T extends DomainObject> T queryBean(String sql, Class<T> clazz,
			Object[] params) throws SQLException {
		DomainObject bean = null;

		Connection conn = getConnection();
		QueryRunner runner = new QueryRunner();

		ResultSetHandler handler = new BeanHandler(clazz, rowProcessor);
		bean = (DomainObject) runner.query(conn, sql, handler, params);

		return (T) bean;
	}

	public <T extends DomainObject> List<T> queryList(String sql,
			Class<T> clazz, Object[] params) throws SQLException {
		Connection conn = getConnection();
		QueryRunner qRunner = new QueryRunner();
		List rsList = null;

		ResultSetHandler handler = new BeanListHandler(clazz, rowProcessor);
		rsList = (List) qRunner.query(conn, sql, handler, params);
		return rsList == null ? new ArrayList(0) : rsList;
	}

	@Override
	public int execute(String sql, Object[] params) throws SQLException {
		int result = 0;

		Connection conn = getConnection();
		QueryRunner runner = new QueryRunner();

		result = runner.update(conn, sql, params);

		return result;
	}

	@Override
	public void callProcedure(String paramString, Object[] paramArrayOfObject1,
			Object[] paramArrayOfObject2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public long queryNumValue(String sql, Object[] params) throws SQLException {
		long recordCount = 0L;

		Connection conn = getConnection();
		QueryRunner runner = new QueryRunner();

		recordCount = ((Long) runner.query(conn, sql, new ScalarHandler(1),
				params)).longValue();

		return recordCount;
	}

	@Override
	public <T extends DomainObject> int insert(T paramT) throws SQLException {
		return 0;
	}

	@Override
	public <T extends DomainObject> int update(T paramT,
			String[] paramArrayOfString) throws SQLException {
		return 0;
	}

	@Override
	public long generateSequenceValue(String paramString) throws SQLException {
		return 0;
	}

}