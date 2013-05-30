
/**
 * 新闻页面
 */
Ext.define('yi18app.view.medicine.SymptomShow', 
{
	extend: 'Ext.DataView',
	xtype: 'symptomshow',
	
    config: {
        	
 			 
			
			    itemTpl: '<div style="font-size:12pt;font-weight: bold;" align="center">{name}</div>' +
			    		'<div style="color:#666;font-size:10pt;">分类： {symptomsclass}</div>' +
			    		'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="98%" color=#aaa SIZE=2>' +
			    		'<div  style="font-family:宋体">{message}</div>'
        }
        
  
	
});