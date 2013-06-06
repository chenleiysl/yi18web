<#include "header.ftl">


  <style type="text/css">
.News h1.Title {font-size:18pt;}
.News .PubDate {margin:15px 0 0px 0;color:#666;}
#toolbar_wrapper{padding:10px 0 5px 0;}
.toolbar{height:33px;margin:0;padding:0;vertical-align: middle;border:1px solid #CCC;-moz-box-shadow: 0 1px 3px rgba(198,198,198,0.5);-webkit-box-shadow: 0 1px 3px rgba(198,198,198,0.5);box-shadow: 0 1px 3px rgba(198,198,198,0.5);}
.toolbar a{text-decoration:none;color:black;}
.share{float:left;margin:0;padding:3px 0 0 0;line-height:25px;width:280px;}
.share_sina{float:left;background:url(${basePath}common/image/toolbar.gif) -1px -278px no-repeat;padding-left:25px;padding-right:5px;margin-left:3px;}
.share_qq{float:left;background:url(${basePath}common/image/toolbar.gif) -1px -316px no-repeat;padding-left:25px;padding-right:5px;}
.options{float:right;margin:3px 5px 0 5px;padding:0;height:30px;line-height:25px;}
.tool_title {float:left;text-align:center;vertical-align:middle;margin:0 0 0 5px;line-height:33px;}
.options .tool_title{line-height:30px;}

.Menu{
font-size:14pt;
}
</style>
    
 <div id="Message">
<samp class="date">  <a href="${basePath}lore/list ">健康知识</a>&raquo;<a href="${basePath}lore/list/${loreclass.getId()}">${loreclass.name}</a>&raquo;<a href="${basePath}lore/show/${lore.getId()}">${lore.title}</a>   </samp> 
<!-- 
<samp style="float: right;"> 共收录药品 <samp style="font-size: 12pt;color: red;font-weight: bolder;">17 </samp> 个     </samp> 
-->
</div>
 

  
   <div style="margin:10px 0;"></div>  

  <div style="margin:10px 0;"></div>
  
   <div style="float: right;width: 200;font-size:9pt;" >
  <div >本周最热健康知识</div>
<ul  >
<#list week as item >
  <li> <a href="${basePath}news/show/${item.id}" >${item.subTitle(14)}</a> </li>

</#list>
</ul>
 
   
     
 <div >最新健康知识</div>
<ul  >
<#list page.list as item >
  <li> <a href="${basePath}news/show/${item.id}" >${item.subTitle(14)}</a> </li>

</#list>
</ul>
 
 </div>
  <div id="p" class="easyui-panel" title="健康知识" style="width:750px;padding:10px;"> 
  
  <div class='NewsBody'>
	<div class='News'>
		<div class="NewsEntity">
		<!-- google_ad_section_start -->
		<h1 class='Title' >${lore.title}</h1>
		<div class='PubDate'>
        	${lore.author!!}
        	发布于： ${lore.time} (${lore.count}阅读)				
        </div>
<div class='NewsToolbar'>


<div id='toolbar_wrapper'>
	<div class='toolbar'>
	<div class="tool_title"><b>分享&nbsp;</b></div>
	<div class='share'>
		<!-- 新浪微博-->
	<div style="float: left;"><wb:share-button appkey="4283978836" count="n" type="button" size="middle" title="${title}" pic="${lore.Pic()}"></wb:share-button></div>
	<!-- 腾讯微博-->
	<div  title="分享到腾讯微博" id="qqwb_share__" data-appkey="801352224" data-icon="1"  data-content="${title}" data-pic="${lore.Pic()}"></div>
	
	
	</div>
	<div class='options'>
			
					</div>
	</div>
</div>		
</div>
<div >
<br>
<br>
 
${lore.message}
	
		</div>
		
		</div>
		</div>
		</div>
		 
		 
		 <br>
<br>
<#if last??>
<div class='Menu'>新一篇:<a href="${basePath}lore/show/${last.id}"> ${last.title}</a></div> 
</#if>
<#if next??>
<div class='Menu'>旧一篇:<a href="${basePath}lore/show/${next.id}"> ${next.title}<a></div>
</#if>
<br>
<br>
<div class='Menu'>相关健康知识</div>
<ul  class='List'>
<#list searchlist as item >
  <li> <a href="${basePath}${item.url}" >${item.title}</a> </li>

</#list>
</ul>
    </div>  
 
 
 

<#include "footer.ftl">


<!-- 腾讯微博 -->
<script src="http://mat1.gtimg.com/app/openjs/openjs.js#autoboot=no&debug=no"></script>

<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=" type="text/javascript" charset="utf-8"></script>
