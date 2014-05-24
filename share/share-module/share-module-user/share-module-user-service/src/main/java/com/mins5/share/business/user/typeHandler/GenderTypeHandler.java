package com.mins5.share.business.user.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.mins5.share.common.enums.GENDER;

/**
 * @author zhoutian
 * @since 2014-3-1
 */
public class GenderTypeHandler extends BaseTypeHandler<GENDER> {

	@Override
	public GENDER getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return GENDER.valueOf(rs.getInt(columnName));
	}

	@Override
	public GENDER getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return GENDER.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public GENDER getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return GENDER.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			GENDER parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getCode());
	}
}
