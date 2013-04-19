<#include "header.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 



<div id="Message">
<samp class="date">  <a href="#">药品信息</a>&raquo;<a href="#">添加药品</a>   </samp> <samp style="float: right;"> 共收录药品 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 

</div>
    
     
     <div id="p" class="easyui-panel" title="添加健康知识" style="width:880px;height:700px;padding:10px;" data-options="tools:'#tt'"> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 400px">  
     
           
                 
                 <form id="ff" method="post" >      
            <table>  
                <tr>  
                    <td>标题:</td>  
                    <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" style="width: 400px"></input></td>  
                	
                </tr>  
                  <tr>  
                    <td>作者:</td>  
                    <td><input class="easyui-validatebox" type="text" name="author"  style="width: 200px"></input></td>  
                	
                </tr>  
 
                 <tr>  
                    <td>内容:</td>  
                    <td> <textarea id="editor_id" name="message" style="width:750px;height:450px;">
						
						</textarea>  
						
						<script>
							var editor_id;
							KindEditor.ready(function(K) {
								
								editor_id = K.create('#editor_id', {
								uploadJson : '${basePath}common/kindeditor/jsp/upload_json.jsp',
								items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'formatblock', 'insertorderedlist', 'insertunorderedlist', '|','forecolor',
										 'hilitecolor', 'fontname', 'fontsize', '|','link','image', 'unlink', 'emoticons',   'table', 'quote', '|', 'fullscreen', 'source', 'about']
						
						    });
						});
				</script>
						</td>  
                </tr> 
               
               
            </table>  
            
             <input type="submit" name="sub" value="保存">
         </form>
                
            
        </div>
    
    </div>
     
    
   
<#include "footer.ftl">