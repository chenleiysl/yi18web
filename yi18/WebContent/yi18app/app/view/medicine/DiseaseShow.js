
/**
 * 新闻页面
 */
Ext.define('yi18app.view.medicine.DiseaseShow', 
{
	extend: 'Ext.DataView',
	xtype: 'diseaseshow',
	
    config: {
        	
 			 
			
			    itemTpl: '<div style="font-size:12pt;font-weight: bold;" align="center">{name}</div>' +
			    		'<div style="color:#666;font-size:10pt;" >分类：{diseaseclass}</div>' +
			    		'<div style="color:#666;font-size:10pt;" >传染性：{infectious}</div>' +
			    		'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#aaa SIZE=2>' +
			    		'<div  style="font-family:宋体">{message}</div>'
        }
        
  
	
});