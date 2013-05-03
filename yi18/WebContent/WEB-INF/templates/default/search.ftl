
<#include "header.ftl">

<style>
#Screen {
	width:998px;
	margin:0px auto;
	text-align:left;
	padding:0 4px;
	margin-bottom:50px;
}


#head {
	text-align:right;
	color: green;
	font-size: 9pt;
}
#logo {
	text-align:left;
	font-size: 12pt;
	color: green;
	
	
}

#search {
	text-align:left;
	font-size: 16pt;
}

.buttonCss{
 background:#F0F0F0 repeat-x;
 border-top:1px solid #708090;
 border-right:1px solid #708090;
 border-bottom:1px solid #708090;
 border-left:1px solid #708090; 
 width:80px;
 height:auto;
 font-size:14pt;
 cursor:hand;
 text-align:right;
} 

.s_ipt_wr{width:418px;height:30px;display:inline-block;margin-right:5px;background:url(${basePath}common/image/button.png) no-repeat -304px 0;border:1px solid #b6b6b6;border-color:#9a9a9a #cdcdcd #cdcdcd #9a9a9a;vertical-align:top}
.s_ipt{width:405px;height:22px;font:16px/22px arial;margin:5px 0 0 7px;background:#fff;outline:none;-webkit-appearance:none}
.s_btn{width:95px;height:32px;padding-top:2px\9;font-size:14px;background:#ddd url(${basePath}common/image/button.png);cursor:pointer}
.s_btn_h{background-position:-100px 0}
.s_btn_wr{width:97px;height:34px;display:inline-block;background:url(${basePath}common/image/button.png) no-repeat -202px 0;*position:relative;z-index:0;vertical-align:top}


#result {
padding:0 10px;
margin:0 230px 0 0;
word-wrap:break-word;
border:1px solid #5cc26f;
}

.title{
 font-size: 12pt;
 font-weight: bold;
}

.title a{
		text-decoration: none;
}
.title a:hover{
		color: #5cc26f;
}

.content{
	margin:6px 0 0 5px;
	font-size:10.5pt;
	color:#777;
	line-height:18px;
}

.url{
	margin:6px 0 0 5px;
	font-size:10pt;
	color:#5cc26f;
	line-height:18px;
}
.date{
	margin:6px 0 0 5px;
	font-size:10pt;
	color:#5cc26f;
	line-height:18px;
}

.table {

margin:20px 0 0 15px;


}

.total{
	margin:0 0 0 70px;
	color: green;
	font-size: 9pt;

}

.pager {height:28px;line-height:28px;margin:50px 0 0 4px;}
.pager li {
	font-size: 14px;
	text-align: center;
	margin: 0 2px 0 0;
	display:inline;
}
.pager li a {
	font-family:Courier New,Arial;
    color: #58595b;
    background-color:#f0f0f0;
    padding: 3px 5px;
    text-decoration:none;
}
.pager li a:hover {
	color:#000;
}
.pager li.prev a {
    background-color:#333;
    color:#333;	
}
.pager li.next a {
    background-color:#7798CB;
    color:#333;	
}
.pager li.current a {
	color: #fff;
    background: #7798CB;
}

#Links {margin:20px 0 0 0;}
#Links h3 {font-size:10pt;color:#000;}
#Links p {padding:5px;line-height:20px;}
#Links p a {font-size:9pt;margin-right:6px;color:#666;}
#Links p a:hover {color:#A00;}

#SpaceRight {
position:absolute;
 right:0; top:200px; 
 width:320px;
  word-wrap:break-word;
 }

</style>



<!-- 搜索结果 -->
<div id="result">

<#list page.list as item>
<table width='80%' cellspacing='0' cellpadding='0' class="table">
<tr>
<td class="title"> <a href="${basePath}${item.url}" target="_blank">${item.title}</a></td>
</tr>




<tr>
<td class="content">&nbsp;&nbsp;&nbsp;&nbsp;
<#if item.content?length &gt; 200>
${item.content?substring(0, 200)}<samp class="url">……</samp>
<#else>
${item.content}
</#if>
</td>
</tr>

<tr>
<td ><samp class="url">

${basePath}${item.url} 
</samp></td>
</tr>
</table>

</#list>




<!-- 页面 -->
<ul class="pager">
	
	<#if page.totalpage &gt; 1> 
	<#assign x=1>
	<#assign mx=page.totalpage>
	
	<#if page.page &gt; 10>
		<#assign x=page.page>
		
		<#assign mx=page.page+1>
		<#if mx &gt;page.totalpage >
					<#assign mx=page.page>
		</#if>
	</#if>
	
	<#list x..mx as i>
	
	<li <#if i==page.page>class='page current'<#else>class='page'</#if>>
	<a href="${basePath}search?keyword=${keyword}&page=${i}">${i}</a>
	
</#list>
    </li>
    <#if page.page!=page.totalpage>
    <li class='page next'><a href="${basePath}search?keyword=${keyword}&page=${page.page+1}">&gt;</a></li> 
			</#if>
</#if> 
      <span class="total">  为你找到 ${page.total?string(",##0")} 个结果   | 用时 ${page.time} ms</span>
</ul>




</div>

<div id="SpaceRight">
<!-- -->
<div id="Links">

<h3 class='caption'>热门搜索</h3>

<u>


</u>


</div>



</div>

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#5cc26f SIZE=1>




<#include 'footer.ftl'>
</div>

</body>
</html>