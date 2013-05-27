
/**
 * 新闻页面
 */
Ext.define('yi18app.view.lore.LoreShow', 
{
	extend: 'Ext.DataView',
	xtype: 'loreshow',
	
    config: {
        	
 			 
			
			    itemTpl: '<div style="font-size:12pt;font-weight: bold;" align="center">{title}</div>' +
			    		'<div style="color:#666;font-size:10pt;" align="center">{author} 发布: {time} ({count}阅读)</div>' +
			    		'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#aaa SIZE=2>' +
			    		'<div  style="font-family:宋体">{message}</div>'
        }
        
  
	
});