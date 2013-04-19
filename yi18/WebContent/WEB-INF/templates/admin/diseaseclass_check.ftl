<#include "adminheader.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 



<div id="Message">
<samp class="date">  <a href="#">疾病信息</a>&raquo;<a href="#">审核疾病</a>   </samp> <samp style="float: right;"> 共收录疾病 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 

</div>
    
     <script>
     $(document).ready(function(){
	     $('#ff').form('load',{  
	     			id:${disease.getId()} ,
	                name:'${disease.name}' ,
	                description:'${disease.description}'
	             
	                
	            }); 
            
        });
            </script> 
    
    <form id="ff" method="post" action="${basePath}disease/update" > 
    <input type="hidden" name="id" value="0">  
     <input type="hidden" name="allow" value="1">
   <div id="p" class="easyui-panel" title="审核疾病" style="width:880px;height:600px;padding:10px;" data-options=""> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 500px">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
                <div title="疾病基本信息" data-options="selected:true" style="padding:10px;">  
                      
           <table>  
                <tr>  
                    <td>疾病名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                	<td>* </td>
                </tr>  
              
             
                  <tr>  
                    <td>疾病的简介:</td>  
                    <td> <textarea  name="description" style="width:400px;height:200px"></textarea>  </td>  
                    <td> 疾病的简介，简要说明 </td>
                </tr> 
               
                <tr>  
                    <td>分类:</td>  
                    <td>
					  <select id="cc" class="easyui-combobox" name="diseasesclass" style="width:200px;">  
					    <option value="0">其它</option>  
					     <#list diseaseclass as item>
					     <option value="${item.getId()}">${item.title}</option> 
					     </#list>
					    
					</select>
					    
					</td>  
                </tr> 
               
            </table> 
        
                </div>  
                
                   <#list list as item>
                <div title="${item.title}"  style="padding:10px;" data-options="" >  
                   <textarea id="editor_${item.getId()}" name="editor_${item.getId()}" style="width:700px;height:300px;">${item.message}</textarea>  
						
				<script>
					var editor_${item.getId()};
					KindEditor.ready(function(K) {
				    editor_${item.getId()} = K.create('#editor_${item.getId()}', {
						
						items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'formatblock', 'insertorderedlist', 'insertunorderedlist', '|','forecolor',
								 'hilitecolor', 'fontname', 'fontsize', '|','link', 'unlink', 'emoticons', 'table', 'quote', '|', 'fullscreen', 'source', 'about']
				
				    });
				});
				</script>
                </div>  
               </#list> 
             
            </div>  
            
        </div>
   
    </div>
    <input type="submit" name="sub1" value="更新">
 </from>

<#include "../default/footer.ftl">