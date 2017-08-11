package com.liujun.gen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.liujun.gen.App;
import com.liujun.gen.bean.TableField;

public class DbUtil {
	private static String url;
	private static String username;
	private static String password;
	public static String dbtype;
	private static String queryfieldsql;
	static {
		ResourceBundle rb1 = ResourceBundle.getBundle(App.propertiesFile);
		url = rb1.getString("base.jdbc.url");
		username = rb1.getString("base.jdbc.username");
		password = rb1.getString("base.jdbc.password");
		if (url.indexOf("mysql") >= 0) {
			dbtype = "Mysql";
		} else if (url.indexOf("oracle") >= 0) {
			dbtype = "Oracle";
		} else if (url.indexOf("postgresql") >= 0) {
			dbtype = "Postgresql";
		}
	}

	public static List<TableField> loadfield(String tablename) {
		List<TableField> fieldList = new ArrayList<TableField>();
		ResourceBundle rb1 = ResourceBundle.getBundle(App.propertiesFile);
		queryfieldsql = rb1.getString("queryfieldsql");
		queryfieldsql = queryfieldsql.replaceAll("#table_name#", tablename);
		System.out.println(queryfieldsql);
		fieldList.clear();
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			if (dbtype.equals("Mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
			} else if (dbtype.equals("Oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} else if (dbtype.equals("Postgresql")) {
				Class.forName("org.postgresql.Driver");
			}
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryfieldsql);
			while (rs.next()) {
				TableField field = new TableField();
				field.setColumnName(rs.getString("COLUMN_NAME"));
				field.setDataType(rs.getString("DATA_TYPE"));
				field.setColumnComment(rs.getString("COLUMN_COMMENT"));
				String key = rs.getString("COLUMN_KEY");
				if (key != null && key.equals("PRI")) {
					field.setColumnKey("true");
				}
				fieldList.add(field);
			}
			System.out.println(fieldList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fieldList;
	}
}
