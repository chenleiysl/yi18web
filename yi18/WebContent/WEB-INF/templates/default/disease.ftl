<#include "header.ftl">


  <div id="Message">
<samp class="date">  <a href="${basePath}drug/list">药品分类</a>&raquo;<a href="#">#</a> &raquo </samp> 

</div>
    
 
 <div style="margin:10px 0;"></div>  
  <div class="ti" style="
	border-top:2px solid #CCCCCC;
	color:#737573;
	text-align:center;
	margin-top:10px;
	line-height:20px;
	font-size:10pt;
">《${disease.name}》<div>

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
		      <td>科室</td><td></td>
		       </tr>
		      <tr>
		       <td>传染</td><td>${disease.infectious}</td>
		      </tr>
		      <tr>
		       <td>体现部位</td><td>血液</td>
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