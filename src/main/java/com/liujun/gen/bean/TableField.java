package com.liujun.gen.bean;

public class TableField {
//	COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT,COLUMN_KEY
	private String columnName;
	private String dataType;
	private String columnComment;
	private String columnKey;
	private String jdbcType;
	private String javaType;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	@Override
	public String toString() {
		return String.format("columnName:%s\tdataType:%s\tcolumnComment:%s\tcolumnKey:%s\n", columnName,dataType,columnComment,columnKey);
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
}
