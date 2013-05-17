<#include "adminheader.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 


<!--
<div id="Message">
<samp class="date">  <a href="#">病状信息</a>&raquo;<a href="#">审核病状</a>   </samp> <samp style="float: right;"> 共收录病状 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 

</div>
-->
    
     <script>
     $(document).ready(function(){
	     $('#ff').form('load',{  
	     			id:${syptoms.getId()} ,
	                name:'${syptoms.name}' ,
	                description:'${syptoms.description}'
	             
	                
	            }); 
            
        });
            </script> 
    
    <form id="ff" method="post" action="${basePath}symptom/update" > 
    <input type="hidden" name="id" value="0">  
     <input type="hidden" name="allow" value="1">
   <div id="p" class="easyui-panel" title="审核病状" style="width:880px;height:600px;padding:10px;" data-options=""> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 500px">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
                <div title="病状基本信息" data-options="selected:true" style="padding:10px;">  
                      
           <table>  
                <tr>  
                    <td>病状名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                	<td>* </td>
                </tr>  
              
             
                  <tr>  
                    <td>病状的简介:</td>  
                    <td> <textarea  name="description" style="width:400px;height:200px"></textarea>  </td>  
                    <td> 病状的简介，简要说明 </td>
                </tr> 
               
                <tr>  
                    <td>病状分类:</td>  
                    <td>
					  <select id="cc" class="easyui-combobox" name="symptomsclass" style="width:200px;">   
					     <#list symptomclass as item>
					     <option value="${item.getId()}">${item.title}</option> 
					     </#list>
					    
					</select>
					    
					</select></td>  
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
						uploadJson : '${basePath}common/kindeditor/jsp/upload_json.jsp',
						urlType:'domain',
						items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'formatblock', 'insertorderedlist', 'insertunorderedlist', '|','forecolor',
								 'hilitecolor', 'fontname', 'fontsize', '|','link','image', 'unlink', 'emoticons', 'table',  '|', 'fullscreen', 'source', 'about']
				
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