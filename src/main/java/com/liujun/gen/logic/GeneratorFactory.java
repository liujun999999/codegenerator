package com.liujun.gen.logic;

import com.liujun.gen.logic.impl.MysqlGenerator;
import com.liujun.gen.logic.impl.OracleGenerator;
import com.liujun.gen.logic.impl.PostgresqlGenerator;
import com.liujun.gen.util.DbUtil;

public class GeneratorFactory {
	public static Generator createCenerator(){
		if(DbUtil.dbtype.equalsIgnoreCase("mysql")){
			return MysqlGenerator.getInstance();
		}else if(DbUtil.dbtype.equalsIgnoreCase("oracle")){
			return OracleGenerator.getInstance();
		}else if(DbUtil.dbtype.equalsIgnoreCase("postgresql")){
			return PostgresqlGenerator.getInstance();
		}
		return null;
	}
}
