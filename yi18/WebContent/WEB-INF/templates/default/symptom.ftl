<#include "header.ftl">


  <div id="Message">
<samp class="date">  <a href="${basePath}symptom/list">病状分类</a>&raquo;<a href="${basePath}symptom/list/${symptomclass.getId()}">${symptomclass.title}</a> &raquo${symptoms.name} </samp> 

</div>
    
 
 <div style="margin:10px 0;"></div>  
  <div class="ti" style="
	border-top:2px solid #CCCCCC;
	color:#737573;
	text-align:center;
	margin-top:10px;
	line-height:20px;
	font-size:12pt;
"><a href="${basePath}symptom/list/${symptomclass.getId()}">${symptomclass.title}类</a>—《<a href="${basePath}symptom/show/${symptoms.getId()}">${symptoms.name}</a>》<div>

  <div style="margin:10px 0;"></div>
    <div class="easyui-tabs" style="width:920px;" data-options="tabPosition:'left'">  
        <div title="病状基本信息" style="padding:10px"> 
        
             <table width="50%">
             <tr><td width="20%" >名称</td><td>${symptoms.name}</td></tr>
             
              <tr><td>基本描述</td><td>${symptoms.description} </td></tr>
              
             </table>
             
        </div>  
         
         <#list list as item>
        <div title="${item.title}"  style="padding:10px">  
            ${item.message} 
        </div>  
        </#list>
    </div>  
 
 

<#include "footer.ftl">