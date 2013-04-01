<#include "header.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 



<div id="Message">
<samp class="date">  <a href="#">药品信息</a>&raquo;<a href="#">添加药品</a>   </samp> <samp style="float: right;"> 共收录药品 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 

</div>
    
     
    
    <form id="ff" method="post" action="${basePath}drug/add?sub=save" enctype="multipart/form-data"> 
    
   <div id="p" class="easyui-panel" title="添加药品" style="width:880px;height:500px;padding:10px;" data-options=""> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 400px">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
                <div title="药品基本信息" data-options="selected:true" style="padding:10px;">  
                      
            <table>  
                <tr>  
                    <td>药品名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                	<td>* </td>
                </tr>  
                <tr>  
                    <td>药品别名:</td>  
                    <td><input class="easyui-validatebox" type="text" name="alias" style="width: 300px"></input></td>  
               		<td> 该产品其它的名称，如果有多个名称可用（;）隔开。 </td>
                </tr> 
             <!--
              <tr>  
                    <td>药品图片:</td>  
                    <td><input class="easyui-validatebox" type="file" name="image" style="width: 300px"></input></td>  
                	<td> 为了更加形象的现实药品，在这里给药品上传一个“头像”。 </td>
                </tr> 
                -->
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
                    <td> <textarea  name="term" style="width:300px;"></textarea>  </td>  
                    <td> 药品的简介，简要说明 </td>
                </tr> 
               <tr>  
                    <td>生产公司:</td>  
                    <td>
                    
                    <select id="cc" class="easyui-combobox" name="factory" style="width:200px;">  
					    <option value="0">其它生产商</option>  
					     <#list factorys as item>
					     <option value="${item.getId()}">${item.name}</option> 
					     </#list>
					    
					</select>
                     </td>  
                </tr> 
               
            </table>  
        
                </div>  
                
                <#list list as item>
                <div title="${item.title}"  style="padding:10px;" data-options="  
                 
                tools:[{  
                    iconCls:'icon-help', 
                    title:'帮助', 
                    handler:function(){  
                    $.messager.show({
					title:'${item.title} 编写说明',
					msg:'${item.description}',
					timeout:10000,
					width:400,
					height:140,
					//showType:'show',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
                    //$.messager.alert('${item.title} 编写说明','${item.description}'); 
                          
                    }  
                }]">  
                   <textarea id="editor_${item.getId()}" name="editor_${item.getId()}" style="width:700px;height:300px;"></textarea>  
						
				<script>
					var editor_${item.getId()};
					KindEditor.ready(function(K) {
				    editor_${item.getId()} = K.create('#editor_${item.getId()}', {
						
						items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'formatblock', 'insertorderedlist', 'insertunorderedlist', '|','forecolor',
								 'hilitecolor', 'fontname', 'fontsize', '|','link', 'unlink', 'emoticons',   'flash', 'table', 'quote', '|', 'fullscreen', 'source', 'about'],
				
				    });
				});
				</script>
                </div>  
               </#list> 
            </div>  
            
        </div>
   
    </div>
    <input type="submit" name="sub1" value="保存">
 </from>

<#include "footer.ftl">