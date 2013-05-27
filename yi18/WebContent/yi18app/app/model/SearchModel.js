 Ext.define('yi18app.model.SearchModel', {
     extend: 'Ext.data.Model',
     config: {
     	
     	

    	 fields: [ 
    	 	{name: 'id', type: 'string'},
    	   {name: 'title', type: 'string'},
    	   {name: 'url', type: 'string'},
    	    {name: 'content', type: 'string'}
    	 ]
     }
 });