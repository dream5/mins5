package com.mins5.share.business.article.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.mins5.share.business.article.enums.RECOMMEND_POSITION;

public class RecommendPositionTypeHandler  extends BaseTypeHandler<RECOMMEND_POSITION> {

	@Override
	public RECOMMEND_POSITION getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return RECOMMEND_POSITION.valueOf(rs.getInt(columnName));
	}

	@Override
	public RECOMMEND_POSITION getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return RECOMMEND_POSITION.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public RECOMMEND_POSITION getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return RECOMMEND_POSITION.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			RECOMMEND_POSITION parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getCode());
	}
}
