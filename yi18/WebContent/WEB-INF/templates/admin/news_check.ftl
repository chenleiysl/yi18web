

<#include "adminheader.ftl">

<script charset="utf-8" src="${basePath}common/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}common/kindeditor/lang/zh_CN.js"></script>

 




    
     
     <div id="p" class="easyui-panel" title="审核综合信息" style="width:880px;height:700px;padding:10px;" data-options="tools:'#tt'"> 
  <div data-options="region:'west',split:true" title="展开/收缩" style="width:780px;height: 400px">  
     
           
                 
                 <form id="ff" method="post" >    
                 <input type="hidden" name="id" value="${news.getId()}">   
            <table>  
                <tr>  
                    <td>标题:</td>  
                    <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" style="width: 400px" value="${news.title!!}"></input></td>  
                	
                </tr>  
                  <tr>  
                    <td>作者:</td>  
                    <td><input class="easyui-validatebox" type="text" name="author"  style="width: 200px" value="${news.author!!}"></input></td>  
                	
                </tr>  
 
                 <tr>  
                    <td>内容:</td>  
                    <td> <textarea id="editor_id" name="message" style="width:750px;height:450px;">
						${news.message!!}
						</textarea>  
						
						<script>
							var editor_id;
							KindEditor.ready(function(K) {
								editor_id = K.create('#editor_id', {
								items : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'formatblock', 'insertorderedlist', 'insertunorderedlist', '|','forecolor',
										 'hilitecolor', 'fontname', 'fontsize', '|','link', 'unlink', 'emoticons',   'table', 'quote', '|', 'fullscreen', 'source', 'about']
						
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
     
    
   
<#include "../default/footer.ftl">