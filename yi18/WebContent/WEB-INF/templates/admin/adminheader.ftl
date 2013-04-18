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
	<div style="float: right;" id="userbar">欢迎  yi18 ！&nbsp;|&nbsp;<a href="${basePath}drug/add">添加药品</a></div>
	
	<a href="${basePath}" title="医药吧"><img alt="logo"  src="${basePath}common/image/logo.png"></a>
	<font color="green" size="6" style="font-weight: bold;">&nbsp;www.yi18.cn</font>
	</div>
   <div style="padding:10px;border:1px solid #ddd">  
        <a href="${basePath}admin" class="easyui-linkbutton" data-options="plain:true">个人信息</a>  
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm1'">药品管理</a>    
         <a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">病状管理</a> 
         <a href="#" class="easyui-menubutton" data-options="menu:'#mm4'">疾病管理</a>  
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm2'">服务管理</a>   
        <a href="#" class="easyui-linkbutton" data-options="plain:true">关于</a>
       
       
        <div id="mm4" style="width:150px;">  
		      <div ><a href="${basePath}admin/symptom">疾病审核</a></div>  
		        <div ><a href="${basePath}admin/directory/3"> 疾病目录信息 </a></div> 
		       
		        <div>  <a href="${basePath}admin/diseaseclass">疾病分类</a></div>   
		        <div class="menu-sep"></div>  
		        <div>  <a href="${basePath}admin/symptomclass">科室分类</a></div>  
		        <div>  <a href="${basePath}admin/symptomclass">部位分类</a></div> 
		        
        </div>
        
          <div id="mm3" style="width:150px;">  
		      <div ><a href="${basePath}admin/symptom">病状审核</a></div>  
		        <div ><a href="${basePath}admin/directory/2"> 病状目录信息 </a></div> 
		        <div class="menu-sep"></div>  
		        <div>  <a href="${basePath}admin/symptomclass">病状分类</a></div>   
        </div>
       
       <div id="mm2" style="width:150px;">  
       <div ><a href="${basePath}admin/news">综合资讯</a></div>  
         <div ><a href="${basePath}admin/partner">合作伙伴</a></div>  
        <div ><a href="${basePath}admin/factory"> 生产厂家 </a></div> 
       
        <div>  <a href="${basePath}admin/links"> 友情链接</a></div>  
	        
        </div>
       
        <div id="mm1" style="width:150px;">  
        <div ><a href="${basePath}admin/drug">药品审核</a></div>  
        <div ><a href="${basePath}admin/directory/1"> 药品目录信息 </a></div> 
        <div class="menu-sep"></div>  
        <div>  <a href="${basePath}admin/drugclass">药品分类</a></div>  
        
        </div>
       
        
        
        
       
       
       
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
    
    