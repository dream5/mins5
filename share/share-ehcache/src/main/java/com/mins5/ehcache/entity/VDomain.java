package com.mins5.ehcache.entity;

import com.mins5.share.common.domain.DomainObject;

public class VDomain extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tableName;

	private String columnName;

	private String columnValue;

	private String columnDescription;

	private String displayOrder;

	private String remark;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public String getColumnDescription() {
		return columnDescription;
	}

	public void setColumnDescription(String columnDescription) {
		this.columnDescription = columnDescription;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
