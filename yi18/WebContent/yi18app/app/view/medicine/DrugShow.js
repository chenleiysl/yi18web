
/**
 * 新闻页面
 */
Ext.define('yi18app.view.medicine.DrugShow', 
{
	extend: 'Ext.DataView',
	xtype: 'drugshow',
	
    config: {
        	
 			 
			
			    itemTpl: '<div style="font-size:12pt;font-weight: bold;" align="center">{name}</div>' +
			    		'<div style="color:#666;font-size:10pt;" "> 别名：{alias} （{drugclass}）</div>' +
			    		'<div style="color:#666;font-size:10pt;" "> 产考价格：{price}</div>' +
			    		'<div style="color:#666;font-size:10pt;""> 处方类型：{prescription}</div>' +
			    		'<div style="color:#666;font-size:10pt;""> 类型：{ingredient}</div>' +
			    		'<div style="color:#666;font-size:10pt;" > 生产厂家：{factory} </div>' +
			    		'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#aaa SIZE=2>' +
			    		'<div  style="font-family:宋体">{message}</div>'
        }
        
  
	
});