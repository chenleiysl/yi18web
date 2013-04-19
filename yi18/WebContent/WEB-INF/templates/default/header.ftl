<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
		<meta name="keywords" content="${keywords}">
		<meta name="description" content="${description}">
	<meta name="author" content="${author}"/>
	<link rel="shortcut icon" type="image/x-icon" href="${basePath}common/image/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${basePath}common/easyui/themes/metro/easyui.css">  
    <link rel="stylesheet" type="text/css" href="${basePath}common/easyui/themes/icon.css">  
     <link rel="stylesheet" type="text/css" href="${basePath}common/css/default.css">  
    <script type="text/javascript" src="${basePath}common/easyui/jquery-1.9.1.min.js"></script>  
    <script type="text/javascript" src="${basePath}common/easyui/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="${basePath}common/easyui/locale/easyui-lang-zh_CN.js"></script>  
</head>
<body>
<div id = "Screen">

	<div id="logo">
	<div style="float: right;" id="userbar"><a href="${basePath}admin">登录</a>&nbsp;|&nbsp;<a href="${basePath}drug/add">添加药品</a></div>
	<a href="${basePath}" title="医药吧"><img alt="logo" src="${basePath}common/image/logo.png"></a>
	<font color="green" size="6" style="font-weight: bold;">&nbsp;www.yi18.cn</font>
	
	</div>
   <div style="padding:10px;border:1px solid #ddd">  
        <a href="${basePath}" class="easyui-linkbutton" data-options="plain:true">主页</a>  
        <a href="${basePath}drug/list" class="easyui-linkbutton" data-options="plain:true">药品分类</a>  
        <a href="${basePath}symptom/list" class="easyui-linkbutton" data-options="plain:true">病状查找</a>
         <a href="${basePath}disease/list" class="easyui-linkbutton" data-options="plain:true">疾病中心</a> 
         <a href="${basePath}lore/list" class="easyui-linkbutton" data-options="plain:true">健康知识</a>
         <a href="${basePath}news/list" class="easyui-linkbutton" data-options="plain:true">医药新闻</a>    
        <a href="${basePath}index/partner" class="easyui-linkbutton" data-options="plain:true">合作伙伴</a>
        <div style="float: right;">
        <input class="easyui-searchbox" data-options="prompt:'请输入查询信息',menu:'#mm',searcher:doSearch" style="width:340px"></input> 
   		<script>  
        function doSearch(value,name){  
            alert('You input: ' + value+'('+name+')');  
        }  
    	</script> 
    	<div id="mm" style="width:120px">  
        	<div data-options="name:'all'">药品</div>  
        	<div data-options="name:'pi'">病状</div>  
    	</div> 
   		
   		</div>
    </div> 
   <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#5cc26f SIZE=1>
    
    