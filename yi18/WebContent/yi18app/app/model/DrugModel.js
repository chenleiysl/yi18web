 Ext.define('yi18app.model.DrugModel', {
     extend: 'Ext.data.Model',
     config: {
     	
     	

    	 fields: [ 
    	 	{name: 'id', type: 'string'},
    	   {name: 'name', type: 'string'},
    	   {name: 'alias', type: 'string'},
    	    {name: 'message', type: 'string'},
    	   {name: 'term', type: 'string'},
    	   {name: 'count', type: 'string'},
    	   {name: 'image', type: 'string'},
    	   {name: 'prescription', type: 'string'},
    	   {name: 'ingredient', type: 'string'},
    	    {name: 'price', type: 'string'},
    	   {name: 'factory', type: 'string'},
    	   {name: 'drugclass', type: 'string'}
    	 ]
     }
 });