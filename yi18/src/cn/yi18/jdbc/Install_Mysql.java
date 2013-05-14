package cn.yi18.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;





public class Install_Mysql  {

//	public boolean createDb(String dbHost, String dbPort, String dbName,
//			String dbUser, String dbPassword) {
//		// TODO Auto-generated method stub
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "?user="
//			+ dbUser + "&password=" + dbPassword + "&characterEncoding=utf8";
//			Connection conn = DriverManager.getConnection(connStr);
//			Statement stat = conn.createStatement();
//			String sql = "drop database if exists " + dbName;
//			stat.execute(sql);
//			sql = "create database " + dbName
//						+ " CHARACTER SET utf8";
//			stat.execute(sql);
//			stat.close();
//			conn.close();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//		
//		return true;
//	}

	public boolean initAdmin(String initList) {
		// TODO Auto-generated method stub
		QueryHelper.update(initList);
		return true;
	}

	public boolean initTable( String sqlfile) {
		
		try {
			String str = FileUtils.readFileToString(new File(sqlfile));
			String[] sqllist = StringUtils.split(str, ";");
			for (String string : sqllist) {
				QueryHelper.update(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	
	/**
	 * 
	 * @param dbHost
	 * @param dbPort
	 * @param dbName
	 * @param dbUser
	 * @param dbPassword
	 * @param minPool
	 * @param maxPool
	 * @param jdbcPath
	 * @return
	 */
	public boolean updateConfig(String dbHost, String dbPort, String dbName,
			String dbUser, String dbPassword, String minPool, String maxPool,
			String jdbcPath) {
		// TODO Auto-generated method stub
		try {
			String s = FileUtils.readFileToString(new File(jdbcPath));
			//s = s.replaceFirst("DB_CLASSNAME", "com.mysql.jdbc.Driver");
			s = s.replaceFirst("DB_URL", "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?characterEncoding=utf8");
			s = s.replaceFirst("DB_USER", dbUser);
			s = s.replaceFirst("DB_PASSWORD", dbPassword);
			s = s.replaceFirst("DB_MINPOOL", minPool);
			s = s.replaceFirst("DB_MAXPOOL", maxPool);
			FileUtils.writeStringToFile(new File(jdbcPath), s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	
	
	

}
