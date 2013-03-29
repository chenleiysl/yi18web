<%@page import="cn.ipmap.jdbc.MysqlInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mysql信息</title>
</head>
<body>

<%
	String json = System.getenv("VCAP_SERVICES");
out.println(json);
%>

<%
	MysqlInfo mysql = new MysqlInfo();
%>
<br><br>
<%="数据库名称："+mysql.getName() %>
<br>
<%="数据库地址："+mysql.getHostname() %>
<br>
<%="数据库端口："+mysql.getPort() %>
<br>
<%="数据库用户："+mysql.getUser() %>
<br>
<%="数据库密码："+mysql.getPassword() %>
<br>
</body>
</html>