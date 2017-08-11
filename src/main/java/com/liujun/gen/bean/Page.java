package com.liujun.gen.bean;

public class Page {
	private Object record;
	private int rows,page;
	public Object getRecord() {
		return record;
	}
	public void setRecord(Object record) {
		this.record = record;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
