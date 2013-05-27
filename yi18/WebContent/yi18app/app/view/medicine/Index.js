

/**
 * 关于
 */
Ext.define('yi18app.view.medicine.Index', 
{
	extend: 'Ext.navigation.View',
	xtype: 'medicine',
	
    config: {
        
    		defaultBackButtonText :'返回',	
        items:[
        {
        	
	        xtype:'medicinelist'
	       
        }
         
           

        ]
        }
	
});