
<#include "adminheader.ftl">

<script type="text/javascript">  


		
       
        
        var toolbar = [{  
            text:'添加疾病分类',  
            iconCls:'icon-add',  
            handler:function(){$('#w').window('open');}  
        },'-',{  
            text:'编辑',  
            iconCls:'icon-edit',  
            handler:function(){
            
            
            var row = $('#tg').treegrid('getSelected'); 
           
            if(row)
            {
            	 var vid = row.___key_id;
	             $('#w2').window('open'); 
		            $('#ee').form('load',{
		            id:vid,
					title:row.title,
					_parentId:row._parentId
					
					
				});
			
            }else
            {
            	alert('请选择需要修改的分类！');
            }
	            

            }  
        }];  
    </script> 
    
 <table id="tg"></table>  
    <script type="text/javascript">  
        $(function(){  
            $('#tg').treegrid({  
                title:'疾病分类',  
                toolbar:toolbar,  
                width:920,  
                height:550,  
               // rownumbers: true,  
                animate:true,  
                collapsible:true,  
                fitColumns:true,  
               	 url:'${basePath}admin/jsondiseaseclass', 
               	//url:'${basePath}treegrid_data2.json', 
                idField:'___key_id',  
                treeField:'title',  
                showFooter:true,  
                columns:[[  
                    {title:'分类名称',field:'title',width:280}
                 
                ]]  
            });  
        })  
    </script>  
    
    
    <div id="w" class="easyui-window" title="添加疾病分类" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:200px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ff" method="post" action="${basePath}admin/diseaseclass">  
        <input type="hidden" name="id" value="0">
            <table>  
                <tr>  
                    <td>名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>上级分类:</td>  
                    <td><select id="cc" class="easyui-combobox" name="_parentId" style="width:200px;">  
					    <option value="0">根目录</option>  
					     <#list roots as item>
					     <option value="${item.getId()}">${item.title}</option> 
					     </#list>
					    
					</select></td>  
                </tr>  
                 
                 
                
            </table>  
        </form>  
         
        <div style="text-align:center;padding:5px">  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a>  
        </div>  
    </div>  
    <script>  
        function submitForm(){  
            $('#ff').form('submit', {
            onSubmit: function(param){  
		        param.sub = 'save';      
		    } ,
		    success: function(data){  
		       var data = eval('(' + data + ')');  // change the JSON string to javascript object  
		        if (data.success){  
		           alert(data.message)  ;
		            $('#w').window('close');
		            window.location.reload(); //刷新该页面
		        } 
		    }  
		});     
        }  
        function clearForm(){  
            $('#ff').form('clear');  
        }  
    </script>  
          
    </div> 
    
    
    
    <div id="w2" class="easyui-window" title="编辑疾病分类" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:200px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ee" method="post" action="${basePath}admin/diseaseclass">
        <input type="hidden" name="id" value="0">  
            <table>  
                <tr>  
                    <td>名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>上级分类:</td>  
                    <td><select id="cc" class="easyui-combobox" name="_parentId" style="width:200px;">  
					    <option value="0">根目录</option>  
					     <#list roots as item>
					     <option value="${item.getId()}">${item.title}</option> 
					     </#list>
					    
					</select></td>  
                </tr>  
                 
                 
                
            </table>  
        </form>  
         
        <div style="text-align:center;padding:5px">  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="esubmitForm()">保存</a>  
             
        </div>  
    </div>  
    <script>  
        function esubmitForm(){  
            $('#ee').form('submit', {
            onSubmit: function(param){  
		        param.sub = 'edit';      
		    } ,
		    success: function(data){  
		       var data = eval('(' + data + ')');  // change the JSON string to javascript object  
		        if (data.success){  
		           alert(data.message)  ;
		            $('#w2').window('close');
		            window.location.reload(); //刷新该页面
		        } 
		    }  
		});     
        }  
        
    </script>  
          
    </div> 
    

<#include "../default/footer.ftl">