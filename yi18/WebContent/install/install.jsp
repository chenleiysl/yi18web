
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="cn.ipmap.jdbc.QueryHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
QueryHelper queryHelper = new QueryHelper();

String filepath = application.getRealPath("/install/user.sql");
File file = new File(filepath);
String sql = FileUtils.readFileToString(file, "UTF-8");
//out.print(sql);
//queryHelper.update(sql);
queryHelper.update("INSERT INTO yi18_user(`id`,`account`,`password`,`name`,`home`,`image`,`email`,`phone`,`qq`,`url`,`sex`,`birth`,`area`,`signature`,`integral`,`isemail`,`isphone`,`isqq`,`isuse`,`role`,`hkey`,`time`)VALUES(1, '397713572@qq.com', 'd89267ba6e888426c8f798a04f2fb874', 'mysns', 'mysns', 'avatar.gif', NULL, NULL, NULL, NULL, 1, NULL, '四川', NULL, 0, 0, 0, 0, 1, 0, NULL, '2013-03-25 12:21:27')");
%>
</body>
</html>