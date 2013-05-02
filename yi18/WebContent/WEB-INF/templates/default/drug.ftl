<#include "header.ftl">


  <div id="Message">
<samp class="date">  <a href="${basePath}drug/list">药品分类</a>&raquo;
<a href="${basePath}drug/list/${drugclass.getId()}">${drugclass.title}</a> &raquo;
${drug.name} </samp> 

</div>
    
 
 <div style="margin:10px 0;"></div>  
  <div class="ti" style="
	border-top:2px solid #CCCCCC;
	color:#737573;
	text-align:center;
	margin-top:10px;
	line-height:20px;
	font-size:12pt;
">${drug.Ingredient()}《<a href="${basePath}drug/show/${drug.getId()}">${drug.name}</a>》—<a href="${basePath}drug/list/${drugclass.getId()}">${drugclass.title}类</a><div>

  <div style="margin:10px 0;"></div>
    <div class="easyui-tabs" style="width:920px;">  
        <div title="基本信息" style="padding:10px"> 
        <div style="float: right;width:300px" >
        	<img alt="" src="${drug.image!!}" width="200"> 
        </div>
             <table width="50%">
             <tr><td width="20%" >名称</td><td>${drug.name}</td></tr>
             <tr><td>别名</td><td>${drug.alias}</td></tr>
             <tr><td>药品类型</td><td>${drug.Ingredient()}</td></tr>
              <tr><td>处方类型</td><td>${drug.Prescription()}</td></tr>
              <tr><td>产考价格</td><td>${drug.price}元</td></tr>
              <tr><td>基本描述</td><td>${drug.subTerm(1024)} </td></tr>
              <tr><td>生产厂家</td><td><a href="${factory.url!!}" target="_blank">${factory.name}</a></td></tr>
             </table>
             
        </div>  
         
         <#list list as item>
        <div title="${item.title}"  style="padding:10px">  
            ${item.message} 
        </div>  
        </#list>
    </div>  
 
 

<#include "footer.ftl">