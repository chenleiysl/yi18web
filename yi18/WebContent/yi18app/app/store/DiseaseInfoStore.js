
Ext.define('yi18app.store.DiseaseInfoStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.DiseaseModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'id',
						direction : 'desc'
					}],

            //autoload the data from the server
			
       		autoLoad: false,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'Disease/show',
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