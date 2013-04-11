
<!-- 页码 -->
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
	<a href="?p=${i}">${i}</a>
	
</#list>
    </li>
    <#if page.page!=page.totalpage>
    <li class='page next'><a href="?p=${page.page+1}">&gt;</a></li> 
			</#if>
</#if> 
      
</ul>