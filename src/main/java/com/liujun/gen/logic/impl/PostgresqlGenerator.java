package com.liujun.gen.logic.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liujun.gen.bean.BaseParam;
import com.liujun.gen.bean.TableField;
import com.liujun.gen.logic.Generator;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class PostgresqlGenerator implements Generator {
	private static String tag = "postgresql"; 
	private Configuration cfg;
	private PostgresqlGenerator() {
		cfg = new Configuration();  
        // 指定FreeMarker模板文件的位置  
        try {
			cfg.setDirectoryForTemplateLoading(new File(PostgresqlGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"templates"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	private static final PostgresqlGenerator instance = new PostgresqlGenerator();
	public static PostgresqlGenerator getInstance(){
		return instance;
	}
	@Override
	public void createFile(String templatepath,String filepath,BaseParam param, List<TableField> fieldlist) {
		FileOutputStream fis = null;
		OutputStreamWriter writer = null;
		try {
			Template template = cfg.getTemplate(templatepath);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("param", param);
			map.put("fieldlist", fieldlist);
			File file = new File(filepath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			fis =  new FileOutputStream(file);
			writer = new OutputStreamWriter(fis);
			template.process(map, writer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void createAllFile(BaseParam param, List<TableField> fieldlist){
		for(TableField f:fieldlist){
			if(f.getColumnKey()!=null){
				param.setPrimaryKey(f.getColumnName());
				param.setPrimaryType(getJdbcType(f.getDataType()));
				param.setPrimaryJavaType(getJavaType(f.getDataType()));
			}
			f.setJdbcType(getJdbcType(f.getDataType()));
			f.setJavaType(getJavaType(f.getDataType()));
		}
		String path = param.getCreatedir()+File.separator+param.getModelName()+File.separator;
		createFile(tag+"/Mapper.xml.ftl", path+"xml"+File.separator+param.getBeanName()+"Mapper.xml", param, fieldlist);
		createFile(tag+"/Mapper.java.ftl", path+"mapper"+File.separator+param.getBeanName()+"Mapper.java", param, fieldlist);
		createFile(tag+"/Bean.java.ftl", path+"bean"+File.separator+param.getBeanName()+".java", param, fieldlist);
		createFile(tag+"/Service.java.ftl", path+"service"+File.separator+param.getBeanName()+"Service.java", param, fieldlist);
		createFile(tag+"/ServiceImpl.java.ftl", path+"service"+File.separator+"impl"+File.separator+param.getBeanName()+"ServiceImpl.java", param, fieldlist);
		createFile(tag+"/Controller.java.ftl", path+"action"+File.separator+param.getBeanName()+"Controller.java", param, fieldlist);
	}
	
	private String getJavaType(String type){
		if(type.toLowerCase().indexOf("int")>=0){
			return "Integer";
		}else if(type.toLowerCase().indexOf("float")>=0 || type.toLowerCase().indexOf("numeric")>=0 ){
			return "Float";
		}else if(type.toLowerCase().indexOf("varchar")>=0 || type.toLowerCase().indexOf("char")>=0){
			return "String";
		}else if(type.toLowerCase().indexOf("timestamp")>=0 || type.toLowerCase().indexOf("date")>=0){
			return "java.sql.Timestamp";
		}
		return "";
	}
	public String getJdbcType(String type){
		if(type.toLowerCase().indexOf("int")>=0){
			return "INTEGER";
		}else if(type.toLowerCase().indexOf("float")>=0 || type.toLowerCase().indexOf("numeric")>=0 ){
			return "FLOAT";
		}else if(type.toLowerCase().indexOf("varchar")>=0){
			return "VARCHAR";
		}else if(type.toLowerCase().indexOf("timestamp")>=0 || type.toLowerCase().indexOf("date")>=0){
			return "TIMESTAMP";
		}
		return "";
	}
}
