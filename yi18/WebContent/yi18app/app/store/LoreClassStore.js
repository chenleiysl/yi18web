
Ext.define('yi18app.store.LoreClassStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.LoreClassModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'id',
						direction : 'asc'
					}],

            //autoload the data from the server
			
       		autoLoad: true,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'lore/classlist',
               		 reader: {
				            type: "json",
				            rootProperty: "yi18"
				        }
           	 },
		listeners : {
			refresh:{
					fn:function( store, data)
					{
						
						store.add({id:0,name:'全部'});
					}
				
				}
			}
			

	}
});