
/**
 * 新闻页面
 */
Ext.define('yi18app.view.lore.LoreList', 
{
	extend: 'Ext.List',
	xtype: 'lorelist',
	
    config: {
        	
       
		    store: 'LoreListStore',
							
			itemTpl: '<div  style="font-size:10pt;font-weight: bold;"> {title}</div> <div style="color:#666;font-size:8pt;">{author} 发布: {time} ({count}阅读)</div>',
			emptyText: '没有找到数据,请检查条件！',
			plugins: {
				            xclass: 'Ext.plugin.ListPaging', // Reference plugin by class
				           // autoPaging: true,
				            loadMoreText:'更多……',
				            noMoreRecordsText:'没有更多记录了'
				        }
				        
				        ,
							
		
			items:[
				        {
				              docked: 'bottom',
				              xtype : 'toolbar',
				               layout: {
			                        pack: 'center',
			                        align: 'center'
			                    },
				           	  ui:'light',
				              items:[
				                {
				                    xtype: 'selectfield',
				                    id:'loreclass',
				                   store:'LoreClassStore',
								    valueField:'id',
								    displayField:'name'
  
				                },
				                {
				                	 xtype:'button' ,
				                	iconCls: 'refresh',
				                	action:'lorelistrefresh'
				                }
				              ]
				              
				          }
          ]
			
        
      
        }
	
});