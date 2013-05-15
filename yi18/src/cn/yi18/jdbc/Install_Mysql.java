package cn.yi18.jdbc;


import java.io.File;
import java.io.IOException;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;





public class Install_Mysql  {

	private boolean install = false;
	PropertiesConfiguration  dbProperties = null;
	public Install_Mysql() {
		
		try {
			this.dbProperties = new PropertiesConfiguration("jdbc.properties");
			 this.install = dbProperties.getBoolean("install");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//那么这个文件会自动在下面几个地方被搜寻：当前目录 、用户主目录 、classpath
		
		
		
	}
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
		if (install) return true;
		
		QueryHelper.update(initList);
		return true;
	}

	public boolean initTable( String sqlfile) {
		if (install) return true;
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
		if (install) return true;
		dbProperties.setProperty("install", true);
		dbProperties.setProperty("jdbc.url", "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?characterEncoding=utf8");
		dbProperties.setProperty("jdbc.username", dbUser);
		dbProperties.setProperty("jdbc.password", dbPassword);
		dbProperties.setProperty("jdbc.maxActive", maxPool);
		dbProperties.setProperty("jdbc.minIdle", minPool);
		try {
			dbProperties.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dbProperties.load();
		
		//		try {
//			
//			String s = FileUtils.readFileToString(new File(jdbcPath));
//			s = s.replaceFirst("install=false", "install=true");
//			s = s.replaceFirst("DB_URL", "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?characterEncoding=utf8");
//			s = s.replaceFirst("DB_USER", dbUser);
//			s = s.replaceFirst("DB_PASSWORD", dbPassword);
//			s = s.replaceFirst("DB_MINPOOL", minPool);
//			s = s.replaceFirst("DB_MAXPOOL", maxPool);
//			FileUtils.writeStringToFile(new File(jdbcPath), s);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return true;
	}

	
	
	

}
