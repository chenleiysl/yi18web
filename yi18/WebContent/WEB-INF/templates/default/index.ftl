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
 .List li {background:url('${basePath}common/image/a2.gif') no-repeat left center;padding-left:16px;overflow:hidden;height: 24px;list-style-type:none}
 .List h3 {
    font-size: 10pt;
    float: left;
    font-weight: normal;
    width: 280px;
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
    <div>
      <div style="float: right;">
     
     <div id="p1" class="easyui-panel" title="网站通告" style="width:280px;height:350px;padding:10px;"   >  
       2013年6月，医药吧测试版正式推出！
    </div> 
    </div>
    <div>
      <div id="nn">  
        <a href="${basePath}news/list" class="icon-redo" ></a> 
        </div>
      <div id="p" class="easyui-panel" title="最新综合信息" style="width:700px;height:350px;padding:10px;" data-options="tools:'#nn'">  
       <ul class='List'>
       <#list index.news as item>
			<li>
				<h3><a href="${basePath}news/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于 ${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
			</#list>
		
			</ul>
					 
    </div> 
    </div>
  </div>
  
  <div>
     
    <div>
    
     <div id="ll">  
        <a href="${basePath}lore/list" class="icon-redo" ></a> 
        </div>
      <div id="p" class="easyui-panel" title="健康知识" style="width:998;height:300px;padding:10px;" data-options="tools:'#ll'">  
      <div style="width: 490px;float: right;">
       <ul class='List'   >
			<#list index.month as item>
			<li>
				<h3><a href="${basePath}lore/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>
			</ul>
      </div>
        <div style="width: 490px;">
       <ul class='List'   >
			<#list index.week as item>
			<li>
				<h3><a href="${basePath}lore/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>
			</ul>
      </div>
    </div> 
    </div>
  </div>
   
     <div>
      <div style="float: right;">
      
       <div id="ss">  
        <a href="${basePath}symptom/list" class="icon-redo" ></a> 
        </div>
     <div id="p1" class="easyui-panel" title="病状" style="width:496px;height:350px;padding:10px;" data-options="tools:'#ss'">  
        <ul class='List'   >
			<#list index.snews as item>
			<li>
				<h3><a href="${basePath}symptom/show/${item.getId()}" target="_blank">${item.name}</a></h3>
				<span class='date'>发布于${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>
			</ul> 
    </div> 
    </div>
    <div>
     <div id="dd">  
        <a href="${basePath}disease/list" class="icon-redo" ></a> 
        </div>
      <div id="p3" class="easyui-panel" title="疾病" style="width:496px;height:350px;padding:10px;" data-options="tools:'#dd'">  
         <ul class='List'   >
			<#list index.dnews as item>
			<li>
				<h3><a href="${basePath}disease/show/${item.getId()}" target="_blank">${item.name}</a></h3>
				<span class='date'>发布于${item.time?string("yyyy-MM-dd")}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>
			</ul>   
    </div> 
    </div>
  </div>
    
    
    
  <#include "footer.ftl">
 