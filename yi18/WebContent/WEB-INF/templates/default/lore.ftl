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

</style>
    
 
 <div style="margin:10px 0;"></div>  

  <div style="margin:10px 0;"></div>
  <div id="p" class="easyui-panel" title="健康知识" style="width:800px;padding:10px;"> 
  
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
	<div class="tool_title"><b>分享到&nbsp;</b></div>
	<div class='share'>
	<a class="share_sina" title="分享到新浪微博" href="javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=858381728',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','工信部官员：微信收费是肯定的 正在研究中: ','','utf-8'));">新浪微博</a><a class="share_qq" title="分享到腾讯微博" href="javascript:(function(){window.open('http://v.t.qq.com/share/share.php?url='+encodeURIComponent(document.location)+'&amp;appkey=96f54f97c4de46e393c4835a266207f4&amp;site=&amp;title='+encodeURIComponent(document.title)+encodeURIComponent(': '),'', 'width=450, height=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no');}())">腾讯微博</a>
	</div>
	<div class='options'>
			
					</div>
	</div>
</div>		
</div>
<div  >

${lore.message}
		
		</div>
		
		</div>
		</div>
		</div>
		 
    </div>  
 
 
 

<#include "footer.ftl">