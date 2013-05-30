

Ext.define('yi18app.view.medicine.DrugList', 
{
	extend: 'Ext.List',
	xtype: 'druglist',
	
    config: {
        	
   
		
			//title:'药品信息',
		  
		  // store: 'DrugListStore',
							
			itemTpl: '<div  style="font-size:10pt;font-weight: bold;"> {name}</div> ' ,
			emptyText: '没有找到数据,请检查条件！',
			plugins: {
				            xclass: 'Ext.plugin.ListPaging', // Reference plugin by class
				            autoPaging: true,
				            loadMoreText:'更多……',
				            noMoreRecordsText:'没有更多记录了'
				        }
		
			
			
        
      
        }
        
  
	
});