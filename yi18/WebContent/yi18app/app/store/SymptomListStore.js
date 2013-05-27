
Ext.define('yi18app.store.SymptomListStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.SymptomModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'count',
						direction : 'DESC'
					}],

            //autoload the data from the server
			
       		//autoLoad: true,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'symptom/list',
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