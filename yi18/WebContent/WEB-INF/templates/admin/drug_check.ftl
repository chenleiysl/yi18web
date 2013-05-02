<#include "adminheader.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 



<div id="Message">
<samp class="date">  <a href="${basePath}admin/drug">药品信息</a>&raquo;<a href="#">审核药品</a>   </samp> 
<!--
<samp style="float: right;"> 共收录药品 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 
-->
</div>
    
     <script>
     $(document).ready(function(){
	     $('#ff').form('load',{  
	     			id:${drug.getId()} ,
	                name:'${drug.name}' ,
	                alias:'${drug.alias}',
	                ingredient:${drug.ingredient},
	                prescription:'${drug.prescription}',
	                term:'${drug.term}',
	                price:${drug.price},
	                factory:${drug.factory}
	                
	            }); 
            
        });
            </script> 
    
    <form id="ff" method="post" action="${basePath}drug/update" > 
    <input type="hidden" name="id" value="0">  
   <div id="p" class="easyui-panel" title="审核药品" style="width:880px;height:600px;padding:10px;" data-options=""> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 500px">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
                <div title="药品基本信息" data-options="selected:true" style="padding:10px;">  
                      
            <table>  
                <tr>  
                    <td>药品名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                	
                </tr>  
                <tr>  
                    <td>药品别名:</td>  
                    <td><input class="easyui-validatebox" type="text" name="alias" style="width: 300px"></input></td>  
               		
                </tr> 
             
             
             
                
                  <tr>  
                    <td>药品类型:</td>  
                    <td><select id="cc" class="easyui-combobox" name="ingredient" style="width:200px;">  
					    <option value="1">中成药</option>  
					    <option value="0">西药</option>  
					    <option value="2">中药</option>  
					    
					</select></td>  
                </tr> 
                 <tr>  
                    <td>处方类型:</td>  
                    <td><select id="cc" class="easyui-combobox" name="prescription" style="width:200px;">  
					    <option value="0">非处方药</option>  
					    <option value="0">处方药</option>  
					</select></td>  
                </tr> 
                 <tr>  
                    <td>常考价格:</td>  
                    <td><input name="price" id="ss" class="easyui-numberbox" value="0。00" data-options="min:0,precision:2" >  </td>  
                </tr> 
                  <tr>  
                    <td>药品的词条:</td>  
                    <td> <textarea  id="term" name="term" style="width:300px;"></textarea>  </td>  
                   <script>
					var editor_term;
					KindEditor.ready(function(K) {
				    editor_term = K.create('#term', {
						uploadJson : '${basePath}common/kindeditor/jsp/upload_json.jsp',
						fileManagerJson : '${basePath}common/kindeditor/jsp/file_manager_json.jsp',
						items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|',   'insertorderedlist', 'insertunorderedlist','forecolor',
								 'hilitecolor', 'fontname', 'fontsize', '|','image','link', 'unlink',  'table',  '|', 'fullscreen',  'about']
				
				    });
				});
				</script>
                </tr> 
               <tr>  
                    <td>生产公司:</td>  
                    <td>
                    
                    <select id="cc" class="easyui-combobox" name="factory" style="width:200px;">  
					    
					     <#list factorys as item>
					     <option value="${item.getId()}">${item.name}</option> 
					     </#list>
					    
					</select>
                     </td>  
                </tr> 
                <tr>  
                    <td>分类:</td>  
                    <td>
                    
                    <select id="cc" class="easyui-combobox" name="drugclass" style="width:200px;">  
					     
					     <#list drugclass as item>
					        
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
								 'hilitecolor', 'fontname', 'fontsize', '|','link', 'unlink', 'emoticons', 'table', '|', 'fullscreen',  'about']
				
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