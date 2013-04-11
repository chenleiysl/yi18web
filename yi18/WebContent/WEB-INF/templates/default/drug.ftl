<#include "header.ftl">


  <div id="Message">
<samp class="date">  <a href="#">常用药</a>&raquo;<a href="#">感冒药</a> &raquo;<a href="#">${drug.name}</a>  </samp> 

</div>
    
 
 <div style="margin:10px 0;"></div>  
  <div class="ti" style="
	border-top:2px solid #CCCCCC;
	color:#737573;
	text-align:center;
	margin-top:10px;
	line-height:20px;
	font-size:10pt;
">中成药《${drug.name}》<div>

  <div style="margin:10px 0;"></div>
    <div class="easyui-tabs" style="width:920px;height:350px">  
        <div title="药品词条" style="padding:10px"> 
        <div style="float: right;width:300px" >
        	<img alt="" src="${basePath}common/avatar/${drug.image!!}" width="200"> 
        </div>
             <table width="50%">
             <tr><td width="20%" >名称</td><td>${drug.name}</td></tr>
             <tr><td>别名</td><td>${drug.alias}</td></tr>
             <tr><td>药品类型</td><td>${drug.ingredient}</td></tr>
              <tr><td>处方类型</td><td>${drug.prescription}</td></tr>
              <tr><td>产考价格</td><td>${drug.price}</td></tr>
              <tr><td>基本描述</td><td>${drug.term} </td></tr>
              <tr><td>生产厂家</td><td> </td></tr>
             </table>
             
        </div>  
         
         <#list list as item>
        <div title="${item.title}"  style="padding:10px">  
            ${item.message} 
        </div>  
        </#list>
    </div>  
 
 

<#include "footer.ftl">