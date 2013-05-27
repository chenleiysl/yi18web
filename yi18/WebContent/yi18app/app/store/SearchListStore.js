
Ext.define('yi18app.store.SearchListStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.SearchModel',
	
        //filter the data using the firstName field
       
            //autoload the data from the server
			
       		autoLoad: false,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'search',
               		 reader: {
				            type: "json",
				            rootProperty: "yi18"
				        }
           	 }
					

	}
});