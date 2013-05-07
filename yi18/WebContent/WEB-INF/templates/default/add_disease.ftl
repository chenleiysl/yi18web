<#include "header.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 



<div id="Message">
<samp class="date">  <a href="${basePath}disease/list">疾病信息</a>&raquo;<a href="${basePath}disease/add">疾病发布</a>   </samp> 
<!-- <samp style="float: right;"> 共收录疾病 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> -->

</div>
    
     
    
    <form id="ff" method="post" action="${basePath}disease/add"> 
    
   <div id="p" class="easyui-panel" title="添加疾病" style="width:880px;height:500px;padding:10px;" data-options=""> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 400px">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
                <div title="疾病基本信息" data-options="selected:true" style="padding:10px;">  
                      
            <table>  
                <tr>  
                    <td>疾病名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                	
                </tr>  
              
             
                  <tr>  
                    <td>疾病的简介:</td>  
                    <td> <textarea  name="description" style="width:400px;height:200px"></textarea>  </td>  
                   
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
                   <textarea id="editor_${item.getId()}" name="editor_${item.getId()}" style="width:700px;height:250px;"></textarea>  
						
				<script>
					var editor_${item.getId()};
					KindEditor.ready(function(K) {
				    editor_${item.getId()} = K.create('#editor_${item.getId()}', {
						uploadJson : '${basePath}common/kindeditor/jsp/upload_json.jsp',
						urlType:'domain',
						items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'formatblock', 'insertorderedlist', 'insertunorderedlist', '|','forecolor',
								 'hilitecolor', 'fontname', 'fontsize', '|','link','image' ,'unlink', 'emoticons',   'table',  '|', 'fullscreen', 'source', 'about']
				
				    });
				});
				</script>
                </div>  
               </#list> 
            </div>  
            
        </div>
   
    </div>
    <input type="submit" name="sub" value="保存">
 </from>

<#include "footer.ftl">