 Ext.define('yi18app.model.DiseaseModel', {
     extend: 'Ext.data.Model',
     config: {
     	
     	

    	 fields: [ 
    	 	{name: 'id', type: 'string'},
    	   {name: 'name', type: 'string'}, 
    	    {name: 'message', type: 'string'},
    	   {name: 'count', type: 'string'},  
    	    {name: 'infectious', type: 'string'}, 
    	   {name: 'diseaseclass', type: 'string'}
    	 ]
     }
 });