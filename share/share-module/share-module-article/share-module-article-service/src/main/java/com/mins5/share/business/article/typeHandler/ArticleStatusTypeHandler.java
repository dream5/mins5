/**
 * 
 */
package com.mins5.share.business.article.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.mins5.share.business.article.enums.ARTICLE_STATUS;

/**
 * 文章状态枚举typeHanlder
 * @author zhoutian
 * @since 2014年3月14日
 */
public class ArticleStatusTypeHandler extends BaseTypeHandler<ARTICLE_STATUS> {

	@Override
	public ARTICLE_STATUS getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return ARTICLE_STATUS.valueOf(rs.getInt(columnName));
	}

	@Override
	public ARTICLE_STATUS getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return ARTICLE_STATUS.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public ARTICLE_STATUS getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return ARTICLE_STATUS.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			ARTICLE_STATUS parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getCode());
	}
}
