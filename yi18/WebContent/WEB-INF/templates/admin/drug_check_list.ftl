<#include "adminheader.ftl">

 
 <table class="easyui-datagrid" style="width:970px;height:auto" title="审核药品信息"  data-options="toolbar:toolbar">  
        <thead>  
            <tr>  
           	  
                <th data-options="field:'title'" width="100px">药品名称</th>  
                <th data-options="field:'description'" width="600px">词条</th>   
                <th data-options="field:'order'" width="150px">时间</th>
                 <th data-options="field:'attr1'" width="100px"> </th>  
            </tr>  
        </thead>  
        <tbody>  
        		<#list list as item>
            <tr>  
               <td>${item.name}</td>
               
               <td>${item.subTermHtml(50)}</td>
               <td>${item.time}</td>  
               
               <td><a href="${basePath}admin/checkdrug/${item.getId()}" >审核</a>
               <a href="${basePath}drug/delete/${item.getId()}?returnUrl=${url}" >删除</a>
               </td>  
            </tr>  
            </#list>
        </tbody>  
    </table>  
 

<#include "../default/footer.ftl">