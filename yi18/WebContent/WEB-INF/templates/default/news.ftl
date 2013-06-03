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


.List{font-size: 10pt}
 .List li {background:url(${basePath}common/image/a2.gif') no-repeat left center;padding-left:16px;overflow:hidden;height: 28px;list-style-type:none}

</style>
    
    
     <div id="Message">
<samp class="date">  <a href="${basePath}news/list ">综合新闻</a>&raquo;<a href="${basePath}news/show/${news.getId()}">${news.title}</a>   </samp> 

</div>
 
 <div style="margin:10px 0;"></div>  

  <div style="margin:10px 0;"></div>
  
   <div style="float: right;width: 190" >
     推荐区

</ul>
     
     </div>
  
  <div id="p" class="easyui-panel" title="综合信息" style="width:800px;padding:10px;"> 
  
  <div class='NewsBody'>
	<div class='News'>
		<div class="NewsEntity">
		<!-- google_ad_section_start -->
		<h1 class='Title' >${news.title}</h1>
		<div class='PubDate'>
        	${news.author!!}
        	发布于： ${news.time} (${news.count}阅读)				
        </div>
<div class='NewsToolbar'>


<div id='toolbar_wrapper'>


	<div class='toolbar'>
	
	<div class="tool_title"><b>分享&nbsp;</b></div>
	<div class='share'>
	
	<!-- 新浪微博-->
	<div style="float: left;"><wb:share-button appkey="4283978836" count="n" type="button" size="middle" title="${title}" pic="${news.Pic()}"></wb:share-button></div>
	<!-- 腾讯微博-->
	<div  title="分享到腾讯微博" id="qqwb_share__" data-appkey="801352224" data-icon="1"  data-content="${title}" data-pic="${news.Pic()}"></div>
	
	
	


	</div>
	
	</div>
</div>		
</div>

<div >
<br>
<br>
 
${news.message}
		
		</div>
		</div>
		</div>
		</div>
<br>
<br>
<#if last??>
<div class='Menu'>新一篇: <a href="${basePath}news/show/${last.id}">${last.title}</a></div> 
</#if>
<#if next??>
<div class='Menu'>旧一篇: <a href="${basePath}news/show/${next.id}">${next.title}<a></div>
</#if>
<br>
<br>
<div class='Menu'>相关新闻</div>
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