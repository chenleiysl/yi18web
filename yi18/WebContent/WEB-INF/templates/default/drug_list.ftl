
<#include "header.ftl">


<style type="text/css">
    
    /* 项目详细列表 */
.ProjectList {background:#fff;}
.ProjectList ul.List {margin:20px 5px;}
.ProjectList ul.List li {margin:10px 0 20px 0;border-bottom:1px dashed #ddd;list-style-type:none;}
.ProjectList ul.List li h3 {font-size:12pt;text-decoration:none;background:url('${basePath}common/image/r_arrow.gif') no-repeat left center;padding-left:16px;}
.ProjectList ul.List li h3 em {font-style:normal;color:#060;}
.ProjectList ul.List li h3 a { text-decoration:none;}
.ProjectList ul.List li a .name {color:#060;display:inline;margin:0;}
.ProjectList ul.List li a.more {font-weight:normal;color:#A00;display:block;margin:10px 0 0 0;}
.ProjectList ul.List li a:hover {color:#FF3300;text-decoration:underline;}
.ProjectList ul.List li p.detail {margin:5px 0 5px 15px;color:#666;font-size:9pt;}
.ProjectList ul.List li p.news {margin:10px 0 5px 0;}
.ProjectList ul.List li p.news a {color:#333;}
.ProjectList table td {vertical-align:top;padding:0px 0 5px 0;}

.LeftMenu {font-size:12pt;font-weight: bold;}
.LeftMenu table.Menu td{margin:10px 0 10px 0;border-bottom:1px dashed #ddd;list-style-type:none;}
.LeftMenu table.Menu td a{text-decoration:none;}
.LeftMenu table.Menu td a:hover {color:green;}

.LeftMenu table.Menu td .count{font-size:9pt;float: right;}

 </style>

  <div style="margin:10px 0;"></div>  
    <div class="easyui-layout" style="width:980px;height:1800px;">  
    
     <div data-options="region:'west',split:true" title="常见药品" style="width:240px;">  
     
            <div class="easyui-accordion" data-options="fit:true,border:false">  
            
            <#list tree as item>
                 
                <div title="${item.drugclass.title}" 
                <#if item.drugclass.getId() == open>
                data-options="selected:true" 
                </#if>
                style="padding:10px;"> 
                
                <div class="LeftMenu"> 
                   <table class="Menu" width="100%">
                   <#list item.list as it>
                	<tr><td><a href="#">${it.title}  </a></td>
                	<td><span class="count">123</span></td>
                	</tr>
                	
                	</#list>
                	</table> 
                	
                </div>
                	
                </div>  
            </#list>
          
                
            </div>  
            
           
        </div>  
        
        <div data-options="region:'center',title:'<a href=\'#\'>常见药品<a>'" >  
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">  
                <div title="最热药品" data-options="" style="padding:10px">
                	 <div class='ProjectList'>	
               			 <ul class='List'>
                	
                	<li>
				    <h3><a href="/p/kissy-editor" target="_blank">所见即所得的HTML编辑器 <em>KISSY Editor</em></a></h3>    
					
					<table width='100%'><tr><td>
						<p class='detail'>
									KISSY Editor 是开源项目 KISSY UI Library 的一个所见即所得的HTML编辑器组件。KISSY 目前基于 YUI 2.x 开发，目标是打造一系列小巧灵活、简洁实用、使用起来让人感觉愉悦的 UI 组件。目前已有 CSS 基础框架、搜索提示 Suggest 和今天发布的富文本编辑器等...
						    	<a href="/p/kissy-editor" target="_blank" class='more'>更多KISSY Editor信息</a>
						</p>
					</td>
						<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						</tr>
						</table>
				    
					    </li>
							<li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。itor组件专门设计用于PHP Web应用程序中。itor组件专门设计用于PHP Web应用程序中。itor组件专门设计用于PHP Web应用程序中。itor组件专门设计用于PHP Web应用程序中。itor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="${basePath}common/image/logo.png"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="${basePath}common/image/logo1.jpg" width="100"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    <li>
				    <h3><a href="/p/areaedit" target="_blank">PHP的可视化HTML编辑器 <em>AreaEdit</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									AreaEdit是一个WYSIWYG HTML Editor组件专门设计用于PHP Web应用程序中。
						    	<a href="/p/areaedit" target="_blank" class='more'>更多AreaEdit信息</a>
						</p>
					</td>
					<td width='100' valign='top' align='right'><a href="/p/kissy-editor" target="_blank"><img src="http://www.oschina.net/img/logo/ckeditor.gif"/></a></td>
						
						</tr></table>
				    
					    </li>
					    
                	
                	</ul>
                	
                	
         
                	</div>
                	
                	<div>
                	
            <ul class="pager">
        <li class='page current'>
        <a href="">1</a></li>
        <li class='page'><a href="">2</a>
        </li><li class='page'><a href="">3</a></li>
        <li class='page'><a href="">4</a></li>
        <li class='page'><a href="">10</a></li>
        <li class='page next'><a href="#">&gt;</a></li>  
          </ul>
		
                	</div>
                
                </div> 
                
                
                
                <div title="最新添加" data-options="" style="padding:10px">
                <div class='ProjectList'>	
                <ul class='List'>
				              <li>
				    <h3><a href="/p/quarkjs" target="_blank">HTML5游戏框架 <em>QuarkJS</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									Quark JS (quark.js) 是一个全新的 javascript 的 HTML5 游戏开发框架，目前由@flashlizi负责维护，有@大城小胖 @真阿当 @06wj @Bobby_casperCBY @裕波 @米粽my @寒冬winter等一起参与完善。...
						    	<a href="/p/quarkjs" target="_blank" class='more'>更多QuarkJS信息</a>
						</p>
					</td>
						</tr></table>
				    
					    </li>
							<li>
				    <h3><a href="/p/flarevideo" target="_blank">HTML5 视频播放器 <em>Flare Video</em></a></h3>    
					<table width='100%'><tr><td>
						<p class='detail'>
									Flare video 是一个开源的 HTML5 视频播放器，会自动判断浏览器是否支持 HTML5 ，如果不支持则使用 Flash 技术播放。Flare video 可通过 CSS/HTML/JS 进行功能和界面的定制，支持全屏播放。
						    	<a href="/p/flarevideo" target="_blank" class='more'>更多Flare Video信息</a>
						</p>
					</td>
						</tr></table>
				    
					    </li>
						</ul>
                	</div>
                </div>
             </div>
        </div>
           
           </div>     


<#include "footer.ftl">