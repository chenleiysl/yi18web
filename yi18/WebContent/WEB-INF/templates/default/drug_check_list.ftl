<#include "adminheader.ftl">

 
 <table class="easyui-datagrid" style="width:970px;height:auto" title="验证药品信息"  data-options="toolbar:toolbar">  
        <thead>  
            <tr>  
           	  
                <th data-options="field:'title'" width="100px">药品名称</th>  
                <th data-options="field:'description'" width="650px">词条</th>   
                <th data-options="field:'order'" width="150px">时间</th>
                 <th data-options="field:'attr1'" width="50px"> </th>  
            </tr>  
        </thead>  
        <tbody>  
        		<#list list as item>
            <tr>  
               <td>${item.name}</td>
               
               <td>${item.term}</td>
               <td>${item.time}</td>  
               
               <td><a href="${basePath}admin/checkdrug/${item.getId()}" >审核</a></td>  
            </tr>  
            </#list>
        </tbody>  
    </table>  
 

<#include "footer.ftl">