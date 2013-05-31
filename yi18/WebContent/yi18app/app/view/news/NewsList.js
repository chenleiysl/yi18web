
/**
 * 新闻页面
 */
Ext.define('yi18app.view.news.NewsList', 
{
	extend: 'Ext.List',
	xtype: 'newslist',
	
    config: {
        	
       
		    store: 'NewsListStore',
							
			itemTpl: '<div  style="font-size:10pt;font-weight: bold;"> {title}</div> <div style="color:#666;font-size:8pt;">{author} 发布: {time} ({count}阅读)</div>',
			emptyText: '没有找到数据,请检查条件！',
			plugins: {
				            xclass: 'Ext.plugin.ListPaging', // Reference plugin by class
				           autoPaging: true,
				            loadMoreText:'更多……',
				            noMoreRecordsText:'没有更多记录了'
				        }
				        
//				        ,
//							
//		
//			items:[
//				        	{
//				              docked: 'top',
//				              xtype: 'titlebar',
//				              title: '医药吧-资讯',
//				              items:[
//				               {
//						            iconCls: 'star',
//						            align: 'left'
//						        }
//				              ]
//				              
//				          }
//          ]
			
        
      
        }
	
});