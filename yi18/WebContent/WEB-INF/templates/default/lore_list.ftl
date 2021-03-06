
<#include "header.ftl">


<style type="text/css">
    
 .List h3 a{
 color:#666;
 text-decoration: none;
 }   
   
.ListAll h2 a{
 color:#a0a;
 text-decoration: none;
 }   
    
.List h3  a:hover{
  	color:#A00;
}
 .ListAll   a:hover{
  	color:#A00;
}
.List{font-size: 10pt}
 .List li {background:url('${basePath}common/image/a2.gif') no-repeat left center;padding-left:16px;overflow:hidden;height: 28px;list-style-type:none}
 .List h3 {
    font-size: 10pt;
    float: left;
    font-weight: normal;
    width: 420px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin:8px 0px;
}

 .List .date {float:right;margin-left:20px;color:#666; margin:8px ;}
 .List .stat {float:right;margin-left:10px;color:#666; margin:8px ;}
.List li em {font-style:normal;color:#A00;margin:8px 2px;} 


 .ListAll {margin:20px 10px;}
 .ListAll li {overflow:hidden;border-bottom:1px solid #eee; margin-bottom:10px; padding-bottom:10px;}
 .ListAll li h2 {margin:0 0 5px 0;}
 .ListAll li .date {color:#666;}
 .ListAll li .detail {color:#666;margin:10px 0;line-height:20px;}
 .ListAll li .img {float:right;}
    
 

 </style>

  
   <div id="Message">
<samp class="date">  <a href="${basePath}lore/list ">健康知识</a>&raquo;<a href="${basePath}lore/list/${loreclassid!!}">${loreclass!!}</a>  </samp> 
<!-- 
<samp style="float: right;"> 共收录药品 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 
-->
</div>
  
     <div style="margin:10px 0;"></div>  
     
     <div style="float: right;width: 220" >
    <div id="p" class="easyui-panel" title="健康分类" style="width:200px;padding:10px;">  
    <table width="100%">
    
    <#list list as item>
    <tr><td><a href="${basePath}lore/list/${item.getId()}"> ${item.name}</td></tr>
    </#list>
    </table>
    </div>
     </div>
     
     
      <div id="tab-tools">  
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add()" title="添加"></a>  
       <script type="text/javascript">

       function add(){  
    	   window.location.href='${basePath}lore/add'; 
       } 

       </script>
    </div> 
    <div class="easyui-tabs" style="width:700px;height:360px;"  data-options="tools:'#tab-tools'">  
    
    <div title="本周热点" style="padding:10px" >
    
    
    	<ul class='List'   >
    	<#list week as item>
			<li>
				<h3><a href="${basePath}lore/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于 ${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>									
			</ul>
							
							
    
    </div>
      <div title="本月热点" style="padding:10px">
      
      <ul class='List'   >
    	<#list month as item>
			<li>
				<h3><a href="${basePath}lore/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>									
			</ul>
							
      
    </div>
        
      
           
     </div>   
     
     <div style="margin:10px 0;"></div>  
    <div class="easyui-tabs" style="width:700px;">  
    <div title="健康知识">
      <ul class='ListAll'>
								
				<#list page.list as item>
				<li>				
					
				<h2><a href="${basePath}lore/show/${item.getId()}" target="_blank">${item.title}</a></h2>
				<p class='date'><a href="">${item.author}</a> 发布于 ${item.prettyTime()} (${item.time?string("yyyy-MM-dd")}) - ${item.count}阅读</p>				
				<p class='detail'>${item.subMessage(100)}</p>
				<p class='more'><a href="${basePath}lore/show/${item.getId()}" target="_blank" class='more'>显示全文</a></p>
			</li>
				</#list>						
				</ul>
				<#include "page.ftl">
    
    </div>
    </div>
     
    
  
  
  

<#include "footer.ftl">