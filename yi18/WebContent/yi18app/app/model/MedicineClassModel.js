 Ext.define('yi18app.model.MedicineClassModel', {
     extend: 'Ext.data.Model',
     config: {
     	
     	

    	 fields: [ 
    	 	{name: 'id', type: 'string'},
    	  	{name: 'title', type: 'string'},
    	  	{name: 'parent', type: 'string'},
    	  	{name: 'count', type: 'string'}
    	 ]
     }
 });