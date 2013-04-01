<#include "adminheader.ftl">


 <script type="text/javascript">  
        var toolbar = [{  
            text:'添加目录',  
            iconCls:'icon-add',  
            handler:function(){$('#w').window('open');}  
        }]; 
        function edit(vid,vtitle,vurl,vfocal,vsequence){  
            $('#w2').window('open'); 
            $('#ee').form('load',{
            id:vid,
			title:vtitle,
			sequence:vsequence,
			focal:vfocal,
			url:vurl
			
		});
        }  
    </script> 
    
 <table class="easyui-datagrid" style="width:970px;height:auto" title="友情链接信息"  data-options="toolbar:toolbar">  
        <thead>  
            <tr>  
           	  
                <th data-options="field:'title'" width="200px">标题</th>  
                <th data-options="field:'description'" width="300px">URL地址</th>  
                <th data-options="field:'issearch'" width="150px">焦点</th>  
                <th data-options="field:'time'" width="250px">排序</th>  
                 <th data-options="field:'attr1'" width="50px"> </th>  
            </tr>  
        </thead>  
        <tbody>  
        		<#list list as item>
            <tr>  
               <td>${item.title}</td>
                <td>${item.url}</td>
                <td><#if item.focal==1>是<#else>否</#if></td>
               <td>${item.sequence}</td>
                
               
               <td><a href="javascript:void(0)"  onclick="edit(${item.getId()},'${item.title}','${item.url}','${item.focal}','${item.sequence}')">编辑</a></td>  
            </tr>  
            </#list>
        </tbody>  
    </table>  
    
    
    <div id="w" class="easyui-window" title="添加友情链接" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:300px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ff" method="post" action="${basePath}admin/links"> 
        	<input type="hidden" name="id" value="0">
            <table>  
                <tr>  
                    <td>标题:</td>  
                    <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" style="width: 300px"></input></td>  
                </tr>  
                 <tr>  
                    <td>主页:</td>  
                    <td><input class="easyui-validatebox" type="text" name="url" data-options="required:true,validType: 'email'" style="width: 300px"></input></td>  
                </tr> 
                  <tr>  
                    <td>焦点:</td>  
                    <td> <input type="radio" name="focal" value="1" checked="checked" >允许<input type="radio" name="focal" value="0">不允许</p></td>  
                </tr> 
                <tr>  
                    <td>显示顺序:</td>  
                    <td><input  name="sequence" class="easyui-numberspinner" style="width:80px;"   required="required" data-options="min:0,max:100,editable:false"> </td>  
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
    
    
    <!-- 修改信息 -->
     <div id="w2" class="easyui-window" title="修改友情链接" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:300px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ee" method="post" action="${basePath}admin/links"> 
        	<input type="hidden" name="id" value="0">
            <table>  
                 <tr>  
                    <td>标题:</td>  
                    <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" style="width: 300px"></input></td>  
                </tr>  
                 <tr>  
                    <td>主页:</td>  
                    <td><input class="easyui-validatebox" type="text" name="url" data-options="required:true,validType: 'email'" style="width: 300px"></input></td>  
                </tr> 
                  <tr>  
                    <td>焦点:</td>  
                    <td> <input type="radio" name="focal" value="1" checked="checked" >允许<input type="radio" name="focal" value="0">不允许</p></td>  
                </tr> 
                <tr>  
                    <td>显示顺序:</td>  
                    <td><input  name="sequence" class="easyui-numberspinner" style="width:80px;"   required="required" data-options="min:0,max:100,editable:false"> </td>  
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

<#include "footer.ftl">