
/**
 * 新闻页面
 */
Ext.define('yi18app.view.news.Index', 
{
	extend: 'Ext.navigation.View',
	xtype: 'news',
	
    config: {
        	
   		defaultBackButtonText :'返回',
		items:[
		
		{
			title:'医药吧-综合资讯',
		   xtype:'newslist'
		   
		}]
			
			
        
      
        }
        
  
	
});