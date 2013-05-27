

/**
 * 主页信息
 */
Ext.define('yi18app.view.home.Index', 
{
	extend: 'Ext.navigation.View',
	xtype: 'home',

    config: {
        	defaultBackButtonText :'返回',
    	items:[{
    		
    		title:'医药吧www.yi18.cn',
    	items:[    

          { 
          	html: '<br>'
          }, { 	
          	  ui: 'confirm',
	          text: '搜索',
	          action:'homesearch',
	          xtype:'button' ,
	          iconCls: 'search'
          } ,
          { 
          	html: '<br>'
          },
	          { 
	          ui: 'confirm', 
	         text: '综合新闻',
	          action:'homenews',
	          xtype:'button' ,
	          iconCls: 'star'
	         
	      }
          ,
          { 
          	html: '<br>'
          },
          { 
	          ui: 'confirm', 
	          text: '健康知识',
	          action:'homelore',
	          xtype:'button',
	          iconCls: 'tags' 
          },
          { 
          	html: '<br>'
          },
          { 	
          	  ui: 'confirm',
	          text: '常见药品',
	          action:'homemedicine',
	          xtype:'button' ,
	          iconCls: 'info'
          },
          { 
          	html: '<br>'
          },
          { 	
          	  ui: 'confirm',
	          text: '常见病状',
	           action:'homemedicine',
	          xtype:'button' ,
	          iconCls: 'info'
          },
          { 
          	html: '<br>'
          },
          { 	
          	  ui: 'confirm',
	          text: '常见疾病',
	          action:'homemedicine',
	          xtype:'button' ,
	          iconCls: 'info'
          }
        ]
    	
    	}]
        
        }
	
});