
Ext.define('yi18app.store.DrugListStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.DrugModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'count',
						direction : 'DESC'
					}],

            //autoload the data from the server
			
       		//autoLoad: true,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'drug/list',
               		 reader: {
				            type: "json",
				            rootProperty: "yi18"
				        }
           	 }

		,
					
			
	    listeners : {
	    	
	    	beforeload : {
					fn : function(store, options) {
							var news_id = store.getStoreId()  ;
							
							store.getProxy().setExtraParam('id', news_id);
					}
	    	}
		}	

	}
});