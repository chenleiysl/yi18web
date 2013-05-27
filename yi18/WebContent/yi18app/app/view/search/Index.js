
/**
 * 新闻页面
 */
Ext.define('yi18app.view.search.Index', 
{
	extend: 'Ext.List',
	xtype: 'search',
	
    config: {
        	
//    	 store: 'NewsListStore',
							
			itemTpl: '<div  style="font-size:10pt;font-weight: bold;"> {title}</div> <div style="color:#666;font-size:8pt;">{author} 发布: {time} ({count}阅读)</div>',
			emptyText: '没有找到数据,请检查条件！',
			plugins: {
				            xclass: 'Ext.plugin.ListPaging', // Reference plugin by class
				           // autoPaging: true,
				            loadMoreText:'更多……',
				            noMoreRecordsText:'没有更多记录了'
				        },
		items:[
		
		       {
				docked: 'top',
             	 xtype: 'toolbar',
				    ui:'light',
				    layout: {
                        pack: 'center',
                        align: 'center'
                    },
					items:[
					     
				    {
				       
				      xtype: 'searchfield',
				      name: 'search',
				      id:'keyword',
				      placeHolder: '请输入需要查询的信息' ,
				      autoComplete:'on'
				     
				    }   ,
				    { xtype: 'button',
				    	//text:'鎼滅储' ,
				    	iconCls:'search',
				    	ui:'confirm',
				    	action:'search'
				    	//width:40
				    }
				    ]
			}
			, {
                xtype: 'toolbar',
                docked: 'top',
				ui:'light',
               
                    scrollable: {
                        direction: 'horizontal',
                        indicators: false
                    },
                    layout: {
                        pack: 'center',
                        align: 'center'
                    },
					
                   
                items: [
                    {
                        xtype: 'segmentedbutton',
						id:'segmentedsearch',
                        items: [
                            { text: '药品' ,
                            pressed:true},
                            { text: '病状'
                            },
                            { text: '疾病'},
                            { text: '健康' 
                           
                            },
                            { text: '新闻'
                           
                            }
                        ]
                    }
                ]
            }
			
			]
			
			
        
      
        }
        
  
	
});