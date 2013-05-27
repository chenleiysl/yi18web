
Ext.define('yi18app.store.LoreInfoStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.LoreModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'id',
						direction : 'desc'
					}],

            //autoload the data from the server
			
       		autoLoad: false,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'lore/show',
               		 reader: {
				            type: "json",
				            rootProperty: "yi18"
				        }
           	 },
					
			
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