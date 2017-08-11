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

public class MysqlGenerator implements Generator {
	private static String tag = "mysql"; 
	private Configuration cfg;
	private MysqlGenerator() {
		cfg = new Configuration();  
        // 指定FreeMarker模板文件的位置  
        try {
			cfg.setDirectoryForTemplateLoading(new File(MysqlGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"templates"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	private static final MysqlGenerator instance = new MysqlGenerator();
	public static MysqlGenerator getInstance(){
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
		String javatype = "";
		switch (type.toLowerCase()) {
		case "int":
		case "bigint":
		case "tinyint":
			javatype = "Integer";
			break;
		case "decimal":
		case "double":
			javatype = "Float";
			break;
		case "varchar":
		case "char":
			javatype = "String";
			break;
		case "longtext":
		case "mediumtext":
		case "text":
			javatype = "String";
			break;
		case "datetime":
		case "timestamp":
			javatype = "java.sql.Timestamp";
			break;
		default:
			javatype = "";
			break;
		}
		return javatype;
	}
	private String getJdbcType(String type){
		String javatype = "";
		switch (type.toLowerCase()) {
		case "int":
		case "bigint":
		case "tinyint":
			javatype = "INTEGER";
			break;
		case "decimal":
		case "double":
			javatype = "NUMERIC";
			break;
		case "varchar":
			javatype = "VARCHAR";
			break;
		case "char":
			javatype = "CHAR";
			break;
		case "longtext":
		case "mediumtext":
		case "text":
			javatype = "TEXT";
			break;
		case "datetime":
		case "timestamp":
			javatype = "TIMESTAMP";
			break;
		default:
			javatype = "";
			break;
		}
		return javatype;
	}
}
