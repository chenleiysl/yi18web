
/**
 * 新闻页面
 */
Ext.define('yi18app.view.medicine.MedicineList', 
{
	extend: 'Ext.List',
	xtype: 'medicinelist',
	
    config: {
        	
       
		   title:'药品信息',
	        xtype:'list',
	        grouped: true,
	       		
			store: 'MedicineClassStore',				
			itemTpl: '<div  style="font-size:10pt;font-weight: bold;"> {title}    <span style="float: right;color:green">{count}</span></div> ',
			
			items:[ {
                xtype: 'toolbar',
                docked: 'bottom',
				ui:'light',
                
                    layout: {
                        pack: 'center',
                        align: 'center'
                    },

                   
                items: [
                    {
                        xtype: 'segmentedbutton',
						id:'segmentedbutton',
                        items: [
                            { text: '药品信息' ,
                            action:'drug',
                            pressed:true},

                            { text: '病状信息',
                            action:'symptom'
                            },
                            { text: '疾病信息',
                            action:'disease'}
                        ]
                    }
                ]
            }]
        
      
        }
	
});