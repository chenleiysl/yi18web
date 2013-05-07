<#include "header.ftl">


  <div id="Message">
<samp class="date">  <a href="${basePath}disease/list">药品分类</a>&raquo;<a href="${basePath}disease/list/${diseaseclass.getId()}">${diseaseclass.title}</a> &raquo ${disease.name}</samp> 

</div>
    
 
 <div style="margin:10px 0;"></div>  
  <div class="ti" style="
	border-top:2px solid #CCCCCC;
	color:#737573;
	text-align:center;
	margin-top:10px;
	line-height:20px;
	font-size:12pt;
"><a href="${basePath}disease/list/${diseaseclass.getId()}">${diseaseclass.title}</a>-《<a href="${basePath}disease/show/${disease.getId()}">${disease.name}</a>》<div>

  <div style="margin:10px 0;"></div>
    <div  class="easyui-accordion" style="width:700px;">  
          <div title="${disease.name} 信息"  style="overflow:auto;padding:10px;">  
		      <table width="100%">
		      <tr>
		      <td width="20%">名称</td><td>${disease.name}</td>
		      </tr>
		      <tr>
		      <td>描述</td><td>${disease.description}</td>
		     </tr>
		      <tr>
		      <td>科室</td><td>
		      <#list departments as item>
		      ${item.name};
		      </#list>
		      </td>
		       </tr>
		      <tr>
		       <td>传染性</td><td><#if disease.infectious==0 >
		       不传染<#else>传染
		       </#if>
		       </td>
		      </tr>
		      <tr>
		       <td>体现部位</td><td>
		       <#list places as item>
		      ${item.name};
		      </#list></td>
		      </tr>
		      </table>
		        </div> 
         
         <#list list as item>
        <div title="${item.title}"  style="padding:10px">  
            ${item.message} 
        </div>  
        </#list>
    </div>  
 
 

<#include "footer.ftl">