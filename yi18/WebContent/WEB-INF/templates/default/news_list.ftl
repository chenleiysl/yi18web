
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
 .List li {background:url('${basePath}common/image/a2.gif') no-repeat left center;padding-left:16px;overflow:hidden;height: 28px;}
 .List h3 {
    font-size: 10pt;
    float: left;
    font-weight: normal;
    width: 420px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin:0 1px;
}

 .List .date {float:right;margin-left:20px;color:#666;}
 .List .stat {float:right;margin-left:10px;color:#666;}
.List li em {font-style:normal;color:#A00;margin:0 2px;} 


 .ListAll {margin:20px 10px;}
 .ListAll li {overflow:hidden;border-bottom:1px solid #eee; margin-bottom:10px; padding-bottom:10px;}
 .ListAll li h2 {margin:0 0 5px 0;}
 .ListAll li .date {color:#666;}
 .ListAll li .detail {color:#666;margin:10px 0;line-height:20px;}
 .ListAll li .img {float:right;}
    
 

 </style>

  
  
     <div style="margin:10px 0;"></div>  
     
     <div style="float: right;width: 220" >
     推荐区
     </div>
     
     
      <div id="tab-tools">  
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add()" title="添加"></a>  
       <script type="text/javascript">

       function add(){  
    	   window.location.href='${basePath}news/add'; 
       } 

       </script>
    </div> 
    <div class="easyui-tabs" style="width:700px;height:360px;"  data-options="tools:'#tab-tools'">  
    
    <div title="本周热点" style="padding:10px" >
    
    
    	<ul class='List'   >
    	<#list week as item>
			<li>
				<h3><a href="${basePath}news/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于 ${item.time}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>									
			</ul>
							
							
    
    </div>
      <div title="本月热点" style="padding:10px">
      
      <ul class='List'   >
    	<#list week as item>
			<li>
				<h3><a href="${basePath}news/show/${item.getId()}" target="_blank">${item.title}</a></h3>
				<span class='date'>发布于 ${item.time}</span>	
				<span class='stat'><em> ${item.count}</em>浏览</span>			
			</li>
		</#list>									
			</ul>
							
      
    </div>
        
      
           
     </div>   
     
     <div style="margin:10px 0;"></div>  
    <div class="easyui-tabs" style="width:700px;">  
    <div title="全部资讯">
      <ul class='ListAll'>
								
				<#list page.list as item>
				<li>				
					
				<h2><a href="${basePath}news/show/${item.getId()}" target="_blank">${item.title}</a></h2>
				<p class='date'><a href="">${item.author}</a> 发布于 8小时前 (${item.time}) - 1评</p>				
				<p class='detail'>${item.subMessage(100)}</p>
				<p class='more'><a href="${basePath}news/show/${item.getId()}" target="_blank" class='more'>显示全文</a></p>
			</li>
				</#list>						
				</ul>
				<#include "page.ftl">
    
    </div>
    </div>
     
    
  
  
  

<#include "footer.ftl">