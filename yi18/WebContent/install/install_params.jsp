<%@page import="cn.yi18.secdr.jdbc.MysqlInfo"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
	MysqlInfo mysql = new MysqlInfo();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统参数设置--医药吧安装向导</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
<script src="../common/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function formSubmit() {
	if(document.getElementById('dbPassword').value==''&&document.getElementById('db').value!='Hsqldb'){
		
		if(!confirm("您没有填写数据库密码，您确定数据库密码为空吗？")) {
		$(document).ready(function(){
		$("#dbPassword").select();
		});
			return false;
		}
	}
	if(document.getElementById('userName').value==''){
		
		alert("管理员账号不能为空，请输入管理员账号！");
		$(document).ready(function(){
		$("#userName").select();
		});
		return false;	
	}
	if(document.getElementById('password').value==''){
		
		alert("管理员密码不能为空，请输入密码！");
		$(document).ready(function(){
		$("#password").select();
		});
		return false;	
	}
	if(document.getElementById('password').value!=document.getElementById('rpassword').value){
		
		alert("确认密码错误！请重新输入！");
		$(document).ready(function(){
		$("#rpassword").val("");
		$("#rpassword").select();
		});
		return false;	
	}
	if(document.getElementById('minPoolSize').value<0){
		
		alert("数据库最小连接池不能小于0！请重新输入！");
		$(document).ready(function(){
		$("#minPoolSize").select();
		});
		return false;	
	}
	if(document.getElementById('maxPoolSize').value<1||document.getElementById('maxPoolSize').value>500){
		
		alert("数据库最大连接池不能小于1或大于500！请重新输入！");
		$(document).ready(function(){
		$("#maxPoolSize").select();
		});
		return false;	
	}
	if(eval(document.getElementById('minPoolSize').value)>eval(document.getElementById('maxPoolSize').value)){
		
		alert("数据库最大连接池不能小于最小连接池！请重新输入！");
		$(document).ready(function(){
		$("#minPoolSize").select();
		});
		return false;	
	}
	document.getElementById('beforeSubmit').style.display = "none";
	document.getElementById('afterSubmit').style.display = "";
}
</script>
<script type="text/javascript">
$(document).ready(function(){


});


</script>


</head>

<body>
<form action="install_setup.jsp" method="post" onsubmit="return formSubmit();" >
<table width="900" align="center" style="border:#106DBA 1px solid; margin-top:30px;">
  <tr>
    <td bgcolor="#D1E9FA"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="27%" height="60" rowspan="2" align="center"><img src="img/logo.GIF" border="0"/></td>
        <td width="73%" height="30" class="f14b">2、系统参数设置<span style="color:#FF0000">（环境要求：jdk1.5或以上、tomcat5.5或以上）</span></td>
      </tr>
      <tr>
        <td height="20" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;请设置系统相关参数</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="400" align="center" bgcolor="#F0F8FD">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="3" align="center"><strong>数据库设置</strong></td>
       
      </tr>
       <tr>
        <td width="30%" height="30" align="right">数据库：</td>
        <td width="22%" align="left">
        <select name="db" id="db">
        <option selected="selected" value="Mysql">MYSQL</option>
        <!--  
        	<option selected="selected" value="Hsqldb">HSQLDB</option>
			
			<option value="Oracle">ORACLE</option>
			<option value="Sqlserver">SQLSERVER</option>
			-->
		</select>
</td>
        <td align="left">选择你的数据库，如果没有数据库就默认HSQLDB数据库</td>
      </tr>  
      <tr>
        <td width="30%" height="30" align="right">数据库主机：</td>
        <td width="22%" align="left"><input name="dbHost" type="text" class="input" id="dbHost"  value="<%=mysql.getHostname()%>"/></td>
        <td align="left">数据库的ip地址，如果是本机：127.0.0.1</td>
      </tr>     
       <tr>
        <td width="30%" height="30" align="right">数据库端口号：</td>
        <td width="22%" align="left"><input name="dbPort" type="text" class="input" id="dbPort"  value="<%= mysql.getPort() %>"/></td>
        <td align="left">数据库的端口号，一般无需改动</td>
      </tr>
      <tr>
        <td height="30" align="right">数据库名称：</td>
        <td align="left"><input name="dbName" type="text" class="input" id="dbName"  value="<%= mysql.getName() %>"/></td>
				<td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right">数据库用户：</td>
        <td align="left"><input name="dbUser" type="text" class="input" id="dbUser"  value="<%= mysql.getUser() %>"/></td>
				<td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right">数据库密码：</td>
        <td align="left"><input name="dbPassword" type="text" class="input" id="dbPassword"  value="<%=mysql.getPassword() %>"/></td>
		<td align="left">安装数据库时输入的密码</td>
      </tr>
      <tr>
        <td height="30" align="right">最小连接池：</td>
        <td align="left"><input name="minPoolSize" type="text" class="input" id="minPoolSize" value="2" /></td>
		<td align="left">设置数据库连池，最小连接池如：20</td>
      </tr>
      <tr>
        <td height="30" align="right">最大连接池：</td>
        <td align="left"><input name="maxPoolSize" type="text" class="input" id="maxPoolSize" value="15" /></td>
		<td align="left">设置数据库连池，最大连接池如：50</td>
      </tr>
      
      <tr>
        <td colspan="3" align="center"><strong>管理员设置</strong></td>
       
      </tr>
      <tr>
        <td height="30" align="right">是否更新数据库：</td>
        <td align="left"><input name="update" type="checkbox"  id="update" value="yes" checked="checked"/></td>
		<td align="left">选择更新数据库！不选择使用原来的数据库</td>
      </tr>
      <tr>
        <td height="30" align="right">管理员账号：</td>
        <td align="left"><input name="userName" type="text" class="input" id="userName" /></td>
		<td align="left">设置系统管理员账号</td>
      </tr>
      <tr>
        <td height="30" align="right">管理员密码：</td>
        <td align="left"><input name="password" type="password" class="input" id="password" /></td>
		<td align="left">设置系统管理员密码</td>
      </tr>
      <tr>
        <td height="30" align="right">确认密码：</td>
        <td align="left"><input name="rpassword" type="password" class="input" id="rpassword" /></td>
		<td align="left"></td>
      </tr>
     
     
      
    </table></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#D1E9FA">
		<span id="beforeSubmit"><input type="submit" class="btn" value=" 提 交 " />
		</span>
		<span id="afterSubmit" style="display:none;color:red;">安装需要十几秒的时间，请您耐心等待...</span>
	</td>
  </tr>
</table>
</form>
</body>
</html>
