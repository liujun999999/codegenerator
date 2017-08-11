package com.liujun.gen.bean;

import javax.swing.JOptionPane;

public class BaseParam {
	/**
	 * 生成目录
	 */
	private String createdir;
	/**
	 * 模块名
	 */
	private String modelName;
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 作者名
	 */
	private String autherName;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * bean名称
	 */
	private String beanName;
	
	private String primaryKey;
	private String primaryType;
	private String primaryJavaType;
	
	private String pageclass;
	
	private String urlprefix;
	private String urlsuffix;
	public String getUrlprefix() {
		return urlprefix;
	}
	public void setUrlprefix(String urlprefix) {
		this.urlprefix = urlprefix;
	}
	public String getUrlsuffix() {
		return urlsuffix;
	}
	public void setUrlsuffix(String urlsuffix) {
		this.urlsuffix = urlsuffix;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getPrimaryType() {
		return primaryType;
	}
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}


	
	
	public String getCreatedir() {
		return createdir;
	}
	public void setCreatedir(String createdir) {
		this.createdir = createdir;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getAutherName() {
		return autherName;
	}
	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	
	/**
	 * 检查数据
	 * @return
	 */
	public boolean checkdata(){
		if(createdir==null || createdir.equals("")){
			JOptionPane.showMessageDialog(null, "请选中目录", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(modelName==null || modelName.equals("")){
			JOptionPane.showMessageDialog(null, "请输入模块名", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(packageName==null || packageName.equals("")){
			JOptionPane.showMessageDialog(null, "请输入包名", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(autherName==null || autherName.equals("")){
			JOptionPane.showMessageDialog(null, "请输入作者", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(tableName==null || tableName.equals("")){
			JOptionPane.showMessageDialog(null, "请输入表名", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(beanName==null || beanName.equals("")){
			JOptionPane.showMessageDialog(null, "请输入bean名", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public String getPrimaryJavaType() {
		return primaryJavaType;
	}
	public void setPrimaryJavaType(String primaryJavaType) {
		this.primaryJavaType = primaryJavaType;
	}
	public String getPageclass() {
		return pageclass;
	}
	public void setPageclass(String pageclass) {
		this.pageclass = pageclass;
	}
}
