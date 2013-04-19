<#include "adminheader.ftl">

 
 <table class="easyui-datagrid" style="width:970px;height:auto" title="验证综合资讯"  data-options="toolbar:toolbar">  
        <thead>  
            <tr>  
           	  
                <th data-options="field:'title'" width="100px">作者</th>  
                <th data-options="field:'description'" width="650px">标题</th>   
                <th data-options="field:'order'" width="150px">时间</th>
                 <th data-options="field:'attr1'" width="50px"> </th>  
            </tr>  
        </thead>  
        <tbody>  
        		<#list list as item>
            <tr>  
               <td>${item.author!!}</td>
               
               <td>${item.title}</td>
               <td>${item.time}</td>  
               
               <td><a href="${basePath}admin/checkrole/${item.getId()}" >审核</a></td>  
            </tr>  
            </#list>
        </tbody>  
    </table>  
 

<#include "../default/footer.ftl">