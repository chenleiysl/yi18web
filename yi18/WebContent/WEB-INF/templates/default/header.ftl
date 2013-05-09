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
	
	
	<div style="float: right;" id="userbar">
	<#if user??>
	欢迎  <a href="${basePath}admin"> ${user.account} </a>！&nbsp;<a href="${basePath}/admin/exit/${session_id}?returnUrl=${url}">退出</a>
	<#else>
	<a href="${basePath}admin/login?returnUrl=${url}">登录</a>
	</#if>
	
	&nbsp;|&nbsp;<a href="${basePath}drug/add">添加药品</a>
	&nbsp;|&nbsp;<a href="${basePath}symptom/add">新增病状</a>
	&nbsp;|&nbsp;<a href="${basePath}disease/add">疾病发布</a>
	&nbsp;|&nbsp;<a href="${basePath}news/add">投递新闻</a>
	&nbsp;|&nbsp;<a href="${basePath}lore/add">分享健康</a>
	</div>
	<a href="${basePath}" title="医药吧"><img alt="logo" src="${basePath}common/image/logo.png"></a>
	<font color="green" size="6" style="font-weight: bold;">&nbsp;www.yi18.cn</font>
	
	</div>
   <div style="padding:10px;border:1px solid #ddd"> 
    <div style="float: right;">
        <input class="easyui-searchbox" data-options="prompt:'请输入查询信息',menu:'#mm',searcher:doSearch" style="width:340px" value="${keyword!!}"></input> 
   		<script>  
        function doSearch(value,name){  
        
            window.location.href='${basePath}search?type='+name+'&q='+encodeURL(value); 
        } 
        
        function encodeURL(value)
        {
          var encodeURL= encodeURIComponent(value+"");//修改特殊编码
         // alert(encodeURL);
          return encodeURL.replace("%20","+");//修改常见搜索空格变成+编码
        } 
    	</script> 
    	<div id="mm" style="width:120px">  
        	<div data-options="name:'drug'">药品</div>  
        	<div data-options="name:'symptom'">病状</div>  
        	<div data-options="name:'disease'">疾病</div>  
        	<div data-options="name:'news'">综合新闻</div> 
        	<div data-options="name:'lore'">健康知识</div>
    	</div> 
   		
   		</div>
    
        <a href="${basePath}" class="easyui-linkbutton" data-options="plain:true">主页</a>  
        <a href="${basePath}drug/list" class="easyui-linkbutton" data-options="plain:true">药品分类</a>  
        <a href="${basePath}symptom/list" class="easyui-linkbutton" data-options="plain:true">病状查找</a>
         <a href="${basePath}disease/list" class="easyui-linkbutton" data-options="plain:true">疾病中心</a> 
         <a href="${basePath}lore/list" class="easyui-linkbutton" data-options="plain:true">健康知识</a>
         <a href="${basePath}news/list" class="easyui-linkbutton" data-options="plain:true">医药新闻</a>    
        <a href="${basePath}index/partner" class="easyui-linkbutton" data-options="plain:true">合作伙伴</a>
       
    </div> 
   <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#5cc26f SIZE=1>
    
    