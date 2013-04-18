<#include "adminheader.ftl">


 <script type="text/javascript">  
        var toolbar = [{  
            text:'添加科室',  
            iconCls:'icon-add',  
            handler:function(){$('#w').window('open');}  
        }]; 
        function edit(vid,vtitle){  
            $('#w2').window('open'); 
            $('#ee').form('load',{
            id:vid,
			name:vtitle
			
			
		});
        }  
    </script> 
    
 <table class="easyui-datagrid" style="width:970px;height:auto" title="疾病科室信息"  data-options="toolbar:toolbar">  
        <thead>  
            <tr>  
           	 
                <th data-options="field:'title'" width="100px">科室标题</th>  
                <!--
                <th data-options="field:'description'" width="550px">描述</th>  
                <th data-options="field:'issearch',align:'right'" width="50px">是否搜索</th>  
                <th data-options="field:'time'" width="150px">创建时间</th>  
                -->
                 <th data-options="field:'attr1'" width="50px"> </th>  
            </tr>  
        </thead>  
        <tbody>  
        		<#list list as item>
            <tr>  
                <td>${item.name}</td>
               
               
               <td><a href="javascript:void(0)"  onclick="edit(${item.getId()},'${item.name}')">编辑</a></td>  
            </tr>  
            </#list>
        </tbody>  
    </table>  
    
    
    <div id="w" class="easyui-window" title="添加疾病科室信息" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:300px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ff" method="post" action="${basePath}admin/department"> 
        	<input type="hidden" name="id" value="0">
        	<input type="hidden" name="type" value="3">
            <table>  
                <tr>  
                    <td>科室名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>  
                </tr>  
                <!--
                <tr>  
                    <td>描述:</td>  
                    <td><textarea name="description" style="height:60px;"></textarea></td>  
                </tr>  
                 <tr>  
                    <td>是否允许搜索:</td>  
                    <td> <input type="radio" name="issearch" value="1" checked="checked" >允许<input type="radio" name="issearch" value="0">不允许</p></td>  
                </tr> 
                <tr>  
                    <td>显示顺序:</td>  
                    <td><input  name="sequence" class="easyui-numberspinner" style="width:80px;"  required="required" data-options="min:0,max:100,editable:false"> </td>  
                </tr>  
                 
                 -->
                
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
     <div id="w2" class="easyui-window" title="修改疾病科室信息" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:300px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ee" method="post" action="${basePath}admin/department"> 
        	<input type="hidden" name="id" value="0">
            <table>  
                <tr>  
                    <td>科室名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>  
                </tr>  
                <!--
                <tr>  
                    <td>描述:</td>  
                    <td><textarea name="description" style="height:60px;"></textarea></td>  
                </tr>  
                 <tr>  
                    <td>是否允许搜索:</td>  
                    <td> <input type="radio" name="issearch" value="1" checked="checked" >允许<input type="radio" name="issearch" value="0">不允许</p></td>  
                </tr> 
                <tr>  
                    <td>显示顺序:</td>  
                    <td><input  name="sequence" class="easyui-numberspinner" style="width:80px;"  required="required" data-options="min:0,max:100,editable:false"> </td>  
                </tr>  
                 -->
                 
                
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