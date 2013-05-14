<%@page import="cn.yi18.jdbc.DBManager"%>
<%@page import="cn.yi18.jdbc.QueryHelper"%>
<%@page import="cn.yi18.jdbc.Install_Mysql"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安装完成--医药吧安装向导</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dbHost = request.getParameter("dbHost")==null ? "localhost":request.getParameter("dbHost");
String dbPort = request.getParameter("dbPort")==null ? "3306":request.getParameter("dbPort");
String dbName = request.getParameter("dbName")==null ? "yi18":request.getParameter("dbName");
String dbUser = request.getParameter("dbUser")==null ? "root":request.getParameter("dbUser");
String dbPassword = request.getParameter("dbPassword")==null ? "":request.getParameter("dbPassword");
String minPoolSize = request.getParameter("minPoolSize");
String maxPoolSize = request.getParameter("maxPoolSize");
String update = request.getParameter("update");
String userName = request.getParameter("userName");
String password= request.getParameter("password");

String jdbcFileName="/WEB-INF/classes/jdbc.properties";
String sqlfileName="/WEB-INF/classes/database/yi18.sql";
String jdbcPath = application.getRealPath(jdbcFileName);
String sqlfile = application.getRealPath(sqlfileName);
Install_Mysql install=new Install_Mysql();
install.updateConfig(dbHost, dbPort, dbName, dbUser, dbPassword, minPoolSize, maxPoolSize, jdbcPath);

if(update!=null)
{
	
install.initTable( sqlfile);
String initList = "INSERT INTO `yi18_user` (`id`, `account`, `password`, `name`) VALUES ('1', '"+userName+"', '"+password+"', 'yi18')";
install.initAdmin(initList);
}
DBManager.closeConnection();//释放数据库连接到连接池中
%>

<table width="600" align="center"
	style="border: #106DBA 1px solid; margin-top: 8%;">
	<tr>
		<td bgcolor="#D1E9FA">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="27%" height="60" rowspan="2" align="center"><img
					src="img/logo.GIF" border="0" /></td>
				<td width="73%" height="30" class="f14b">3、系统安装完成</td>
			</tr>
			<tr>
				<td height="20" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;安装系统安装完成，</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="280" align="left" bgcolor="#F0F8FD"
			style="padding: 10px; line-height: 1.7em;">恭喜您，系统已经安装成功！<a href="<%=basePath%>">返回首页</a><br />
		
		管理员<%=userName %>，密码<%=password %>。</td>
	</tr>
</table>


</body>
</html>