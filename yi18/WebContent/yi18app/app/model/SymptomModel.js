 Ext.define('yi18app.model.SymptomModel', {
     extend: 'Ext.data.Model',
     config: {
     	
     	

    	 fields: [ 
    	 	{name: 'id', type: 'string'},
    	   {name: 'name', type: 'string'}, 
    	    {name: 'message', type: 'string'},
    	   {name: 'count', type: 'string'},  
    	   {name: 'symptomsclass', type: 'string'}
    	 ]
     }
 });