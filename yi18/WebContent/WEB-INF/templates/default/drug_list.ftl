
<#include "header.ftl">


<style type="text/css">
    
    /* 项目详细列表 */
.ProjectList {background:#fff;}
.ProjectList ul.List {margin:20px 5px;}
.ProjectList ul.List li {margin:10px 0 20px 0;border-bottom:1px dashed #ddd;list-style-type:none;}
.ProjectList ul.List li h3 {font-size:12pt;text-decoration:none;background:url('${basePath}common/image/r_arrow.gif') no-repeat left center;padding-left:16px;}
.ProjectList ul.List li h3 em {font-style:normal;color:#060;}
.ProjectList ul.List li h3 a { text-decoration:none;}
.ProjectList ul.List li a .name {color:#060;display:inline;margin:0;}
.ProjectList ul.List li a.more {font-weight:normal;color:#A00;display:block;margin:10px 0 0 0;}
.ProjectList ul.List li a:hover {color:#FF3300;text-decoration:underline;}
.ProjectList ul.List li p.detail {margin:5px 0 5px 15px;color:#666;font-size:9pt;}
.ProjectList ul.List li p.news {margin:10px 0 5px 0;}
.ProjectList ul.List li p.news a {color:#333;}
.ProjectList table td {vertical-align:top;padding:0px 0 5px 0;}

.LeftMenu {font-size:12pt;font-weight: bold;}
.LeftMenu table.Menu td{margin:10px 0 10px 0;border-bottom:1px dashed #ddd;list-style-type:none;}
.LeftMenu table.Menu td a{text-decoration:none;}
.LeftMenu table.Menu td a:hover {color:green;}
.Select{background: #eee;}
.LeftMenu table.Menu td .count{font-size:9pt;float: right;}

 </style>

  <div style="margin:10px 0;"></div>  
    <div class="easyui-layout" style="width:980px;height:1800px;">  
    
     <div data-options="region:'west',split:true" title="常见药品" style="width:240px;">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
            <#list tree as item>
                 
                <div title="${item.drugclass.title}" 
                <#if item.drugclass.getId() == open>
                data-options="selected:true" 
                </#if>
                style="padding:10px;"> 
                
                <div class="LeftMenu"> 
                   <table class="Menu" width="100%">
                   <#list item.list as it>
                   
	                  
	                   
	                   <tr <#if it.getId()==id> class="Select"  </#if> ><td><a href="${basePath}drug/list/${it.getId()}">${it.title}  </a></td>
	                	<td><span class="count">123</span></td>
	                	</tr>
	                	
	                	
	                 
                   
                   
                	</#list>
                	</table> 
                	
                </div>
                	
                </div>  
            </#list>
          
                
            </div>  
            
           
        </div>  
        
        <div data-options="region:'center',title:'<a href=\'${basePath}drug/list\'>药品分类<a> &raquo;<#if drugclass??>${drugclass.title}<#else>常见药品</#if>(${page.total}种) '" > 
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">  
                <div title="最热药品" data-options="" style="padding:10px">
                	 <div class='ProjectList'>	
               			 <ul class='List'>
                	
                	<#list page.list as item>
                	<li>
				    <h3><a href="${basePath}drug/show/${item.getId()}" target="_blank">${item.alias} <em>${item.name}</em></a></h3>    
					
					<table width='100%'><tr><td>
						<p class='detail'>
									${item.subTerm(400)}
						    	<a href="${basePath}drug/show/${item.getId()}" target="_blank" class='more'>更多${item.name}</a>
						</p>
					</td>
						<td width='100' valign='top' align='right'>
						<a href="${basePath}drug/show/${item.getId()}" target="_blank"><img src="${basePath}common/avatar/${item.image}" width="100" /></a>
						
						</td>
						</tr>
						</table>
				    
					    </li>
					 
                	</#list>
                	</ul>
                	
                	
         
                	</div>
                	
                	<div>
                	
        		<#include "page.ftl">
		
                	</div>
                
                </div> 
                
                
                
                <div title="最新添加" data-options="" style="padding:10px">
                <div class='ProjectList'>	
                <ul class='List'>
				           	<#list news as item>
                	<li>
				    <h3><a href="${basePath}drug/show/${item.getId()}" target="_blank">${item.alias} <em>${item.name}</em></a></h3>    
					
					<table width='100%'><tr><td>
						<p class='detail'>
									${item.term}
						    	<a href="${basePath}drug/show/${item.getId()}" target="_blank" class='more'>更多${item.name}</a>
						</p>
					</td>
						<td width='100' valign='top' align='right'>
						<a href="${basePath}drug/show/${item.getId()}" target="_blank"><img src="${basePath}common/avatar/${item.image}" width="100" /></a>
						
						</td>
						</tr>
						</table>
				    
					    </li>
					 
                	</#list>
						</ul>
                	</div>
                </div>
             </div>
        </div>
           
           </div>     


<#include "footer.ftl">