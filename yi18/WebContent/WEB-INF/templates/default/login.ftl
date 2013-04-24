<#include "header.ftl">
<!--banner-->



<div class="innerPageContent">
	<table cellpadding="0" cellspacing="0" class="fixTable">
	<tr><td></td><td width="962">
	<div class="innerPageContent">
			<table width='100%' cellspacing='0' cellpadding='0' border='0'>
			<tr><td height="70px" align="center" style="color:blue"><h3>登录系统“医药吧”后台系统</h3></a></td></tr>
		<tr>
		<td width="500">
		
		 <div align="center" > <span style="font-size: 12;font-weight: bold;color: red;">${message!""}</span> <br> </div>
		 
		<form action="" method="post">
	
			<table width='100%' cellspacing='0' cellpadding='0' border='0'>
			<tr>
			<th nowrap width="180px">账号：</th>
    		<td>
				<input type='text' name='account' id='f_email' value='' class='TEXT' style='width:200px;'/>
				
			</td> 
			</tr>
		
		
    	<tr>
    		<th>登录密码：</th>
    		<td><input type='password' name='password' id='f_pwd' class='TEXT' style='width:150px;'/>	
			</td>    		
    	</tr>
    	<!--
    	<tr>
    		<th>&nbsp;</th>
    		<td><input type='checkbox' name='auto' value='1' checked='checked'/> 记住我的登录信息<span style='color:#A00;'>（请勿在公用电脑或者网吧内使用此项）</span></td>
    	</tr>
    	-->
		</tr>
    	<tr class='buttons'>
    		<th>&nbsp;</th>		
			<td style='padding:20px 0;'>
    		<input type='submit' name='sub' value=' 登录 ' class='BUTTON SUBMIT'/>&nbsp;&nbsp;&nbsp;&nbsp;
			<!--
			<a href="${basePath}user/resetpwd">忘记登录密码？</a>
			-->
			
			</td>
    	</tr>
			
			</table>
		
		</form>
		</td>
		<td valign="top">
		<div  style="float:right;width:300px;border:2px solid #40AA53;">
<h3>登录后可以？</h3>
	<ol>
		
		<li>修改系统的信息</li>
		<li>管理基本信息</li>
	</ol>

	
</div>

		</td>
		
		</tr>
		
		
		</table>
	</div>
	</td><td></td></tr></table>
</div>


<#include "footer.ftl">
