
<#include "header.ftl">


  <div style="margin:10px 0;"></div>  
    <div class="easyui-layout" style="width:900px;height:350px;">  
    
     <div data-options="region:'west',split:true" title="常见药品" style="width:200px;">  
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
            <#list tree as item>
                 
                <div title="${item.drugclass.title}" data-options="selected:true" style="padding:10px;">  
                   <ul>
                   <#list item.list as it>
                	<li><a href="#">${it.title}  </a></li>
                	
                	</#list>
                	</ul> 
                </div>  
            </#list>
                
            </div>  
        </div>  
        
        <div data-options="region:'center',title:'<a href=\'#\'>Main Title<a>'">  
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">  
                <div title="About" data-options="" style="padding:10px">
                dddffff
                
                </div> 
             </div>
        </div>
           
           </div>     


<#include "footer.ftl">