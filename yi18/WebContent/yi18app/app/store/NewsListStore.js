
Ext.define('yi18app.store.NewsListStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.NewsModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'id',
						direction : 'desc'
					}],

            //autoload the data from the server
			
       		autoLoad: true,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'news/list',
               		 reader: {
				            type: "json",
				            rootProperty: "yi18"
				        }
           	 }
					
//					data: [
//					{"id":"1","title":"医药吧简介","author":"yi18","time":'2013-05-21 10:10:10',"count":'10'},
//					{"id":"2","title":"医药吧简介beta发布","author":"yi18","time":'2013-05-20 10:10:10',"count":'10'}
//
//						]
	}
});