 Ext.define('yi18app.model.NewsModel', {
     extend: 'Ext.data.Model',
     config: {
     	
     	

    	 fields: [ 
    	 	{name: 'id', type: 'string'},
    	   {name: 'title', type: 'string'},
    	   {name: 'author', type: 'string'},
    	    {name: 'message', type: 'string'},
    	   {name: 'time', type: 'string'},
    	   {name: 'count', type: 'string'}
    	 ]
     }
 });