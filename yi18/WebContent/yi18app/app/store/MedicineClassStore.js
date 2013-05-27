
Ext.define('yi18app.store.MedicineClassStore',{
	extend:'Ext.data.Store',
	config: {
		 model: 'yi18app.model.MedicineClassModel',
	
        //filter the data using the firstName field
        sorters : [{
						property : 'id',
						direction : 'asc'
					}],

            //autoload the data from the server
			groupField: 'parent',
       		autoLoad: true,
        	 proxy: {
               		 type: 'jsonp',
              	 	 url: Global.Website+'drug/classlist',
               		 reader: {
				            type: "json",
				            rootProperty: "yi18"
				        }
           	 }


//         data: [
//                { id: '1',   title: '感冒 ' ,group:'常见药品',count:'23'},
//                { id: '2',    title: '发热' ,group:'常见药品',count:'11'},
//                 { id: '4',    title: '避孕套' ,group:'避孕药',count:'11'}
//                ]
			

	}
});