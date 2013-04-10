<#include "adminheader.ftl">


 <script type="text/javascript">  
        var toolbar = [{  
            text:'添加目录',  
            iconCls:'icon-add',  
            handler:function(){$('#w').window('open');}  
        }]; 
        function edit(vid,vname,vdescription,vaddress,vphone,vurl){  
            $('#w2').window('open'); 
            $('#ee').form('load',{
            id:vid,
			name:vname,
			description:vdescription,
			address:vaddress,
			phone:vphone,
			url:vurl
			
		});
        }  
    </script> 
    
 <table class="easyui-datagrid" style="width:970px;height:auto" title="生产厂家信息"  data-options="toolbar:toolbar">  
        <thead>  
            <tr>  
           	  
                <th data-options="field:'title'" width="100px">厂商名称</th>  
                <th data-options="field:'description'" width="250px">描述</th>  
                <th data-options="field:'issearch'" width="150px">公司地址</th>  
                <th data-options="field:'time'" width="250px">主页</th>  
                <th data-options="field:'order'" width="150px">联系电话</th>
                 <th data-options="field:'attr1'" width="50px"> </th>  
            </tr>  
        </thead>  
        <tbody>  
        		<#list list as item>
            <tr>  
               <td>${item.name}</td>
                <td title="${item.description}">${item.description}</td>
                <td>${item.address}</td>
               <td>${item.url}</td>
               <td>${item.phone}</td>  
               
               <td><a href="javascript:void(0)"  onclick="edit(${item.getId()},'${item.name}','${item.description}','${item.address}','${item.phone}','${item.url}')">编辑</a></td>  
            </tr>  
            </#list>
        </tbody>  
    </table>  
    
    
    <div id="w" class="easyui-window" title="添加生产厂商信息" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:300px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ff" method="post" action="${basePath}admin/factory"> 
        	<input type="hidden" name="id" value="0">
            <table>  
                <tr>  
                    <td>厂商名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                </tr>  
                
                <tr>  
                    <td>描述:</td>  
                    <td><textarea name="description" style="height:60px;" style="width: 300px"></textarea></td>  
                </tr>  
                <tr>  
                    <td>地址:</td>  
                    <td><input class="easyui-validatebox" type="text" name="address" data-options="" style="width: 300px"></input></td>  
                </tr> 
                 <tr>  
                    <td>联系方式:</td>  
                    <td><input class="easyui-validatebox" type="text" name="phone" data-options="" style="width: 300px"></input></td>  
                </tr> 
                 <tr>  
                    <td>主页:</td>  
                    <td><input class="easyui-validatebox" type="text" name="url" data-options="validType: 'email'" style="width: 300px"></input></td>  
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
     <div id="w2" class="easyui-window" title="修改生产厂商信息" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:300px;padding:10px;">  
      
        <div style="padding:10px 0 10px 60px">  
        <form id="ee" method="post" action="${basePath}admin/factory"> 
        	<input type="hidden" name="id" value="0">
            <table>  
                <tr>  
                    <td>厂商名称:</td>  
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" style="width: 300px"></input></td>  
                </tr>  
                
                <tr>  
                    <td>描述:</td>  
                    <td><textarea name="description" style="height:60px;" style="width: 300px"></textarea></td>  
                </tr>  
                <tr>  
                    <td>地址:</td>  
                    <td><input class="easyui-validatebox" type="text" name="address" data-options="" style="width: 300px"></input></td>  
                </tr> 
                 <tr>  
                    <td>联系方式:</td>  
                    <td><input class="easyui-validatebox" type="text" name="phone" data-options="" style="width: 300px"></input></td>  
                </tr> 
                 <tr>  
                    <td>主页:</td>  
                    <td><input class="easyui-validatebox" type="text" name="url" data-options="validType: 'email'" style="width: 300px"></input></td>  
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