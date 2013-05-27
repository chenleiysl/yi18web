Ext.define('yi18app.controller.Main', {
    extend: 'Ext.app.Controller',

    config: {
        refs: {
            main: 'main',
            home: 'home',
            news: 'news',
            newslist:'newslist',
            lore: 'lore',
            lorelist:'lorelist',
            loreclass:'#loreclass',
            medicine:'medicine',
            medicinelist:'medicinelist',
           segmentedbutton:'#segmentedbutton',
           druglist:'druglist',
           symptomlist:'symptomlist',
           diseaselist:'diseaselist',
           search:'search',
           keyword:'#keyword',
           segmentedsearch:'#segmentedsearch'
        },
   		control:{
   			main:{
   			activeitemchange:function()
   				{
   			
   			
   				}
   			},
    		newslist: {
                itemtap: 'onNewsListSelect'
            },
           lorelist: {
                itemtap: 'onLoreListSelect'
            },
            medicinelist:{
            	itemtap:'onMedicineListSelect'
            },
            druglist:{
            	itemtap:'onDrugListSelect'
            },
            symptomlist:{
            	itemtap:'onSymptomListSelect'
            },
            diseaselist:{
            	itemtap:'onDiseaseListSelect'
            },
            search:{
            	itemtap:'onSearchListSelect'
            },
            'button[action=homesearch]': {
                tap: function(){ 
//                		var store = Ext.create('yi18app.store.SearchListStore');
//                		store.getProxy().setExtraParam('type', "drug");
//                		store.getProxy().setExtraParam('keyword', "999");
//                		store.setAutoLoad(true);
//                		var searchshow = Ext.create('yi18app.view.search.Index',{store:store});
    					var searchshow = Ext.create('yi18app.view.search.Index');
        				this.getHome().push(searchshow);
                	
                }
            },
            
            'button[action=search]': {
                tap: function(){ 
                	var vaule = this.getKeyword().getValue( );
                	var select =this.getSegmentedsearch().getPressedButtons()[0].getText();
                	var type ="";
                	if(select=="药品")type ="drug";
                	else if(select=="病状")type ="symptom";
 					else if(select=="疾病")type ="disease";	
                	else if(select=="健康")type ="lore";	
                	else if(select=="新闻")type ="news";
                	
                	var store = Ext.create('yi18app.store.SearchListStore');
              		store.getProxy().setExtraParam('type',type);
               		store.getProxy().setExtraParam('keyword', vaule);
               		store.setAutoLoad(true);
               		this.getSearch().setStore(store);
                	//this.getSearch().getStore().load();
                }
            },
   			'button[action=homenews]': {
                tap: function(){ 
                	this.getMain().setActiveItem(3);//修改tab的焦点到第三个上
                	
                }
            },
            'button[action=homelore]': {
                tap: function(){ 
                	this.getMain().setActiveItem(2);//修改tab的焦点到第二个上
                	
                }
            },
            'button[action=homemedicine]': {
                tap: function(){ 
                	this.getMain().setActiveItem(1);//修改tab的焦点到第二个上
                	
                }
            }
            ,
            'button[action=lorelistrefresh]': {
                tap: function(){ 
                	
                	var id= this.getLoreclass().getValue();
                	
                	this.getLorelist().getStore().getProxy().setExtraParam('id', id);
                	this.getLorelist().getStore().load();
                	
                }
            }
            
            ,
            'button[action=drug]': {
                tap: function(){ 
                	
	               	this.getMedicinelist().getStore().getProxy().setUrl(Global.Website+'drug/classlist');
	               	this.getMedicinelist().getStore().load();
                	
                }
            },
             'button[action=symptom]': {
                tap: function(){ 

                	
                	this.getMedicinelist().getStore().getProxy().setUrl(Global.Website+'symptom/classlist');
              
               		this.getMedicinelist().getStore().load();
                }
            },
             'button[action=disease]': {
                tap: function(){ 
                	this.getMedicinelist().getStore().getProxy().setUrl(Global.Website+'disease/classlist');
               		
               		this.getMedicinelist().getStore().load();
                	
                }
            }
    	
   	 	}
    },

  
    
    
    /**
     * 自动运行
     */
     launch: function(){ 
     	
     	
     	 //this.getMain().setActiveItem(3);
        //this.getMain().add(Ext.create('yi18app.view.news.Index',{id:'searchPie',title:'12'}));
    		//var store= Ext.create('yi18app.store.NewsInfoStore',{autoLoad: true,storeId:'1'});
      	
     },
      onNewsListSelect: function(list, index, node, record) {
     
       		
        	var store= Ext.create('yi18app.store.NewsInfoStore',{autoLoad: true,storeId:record.get('id')});
			var newsshow = Ext.create('yi18app.view.news.NewsShow',{title:"综合信息正文",store:store});
    
        	this.getNews().push(newsshow);
         
        
    }
    ,
      onLoreListSelect: function(list, index, node, record) {
     
       		
        	var store= Ext.create('yi18app.store.LoreInfoStore',{autoLoad: true,storeId:record.get('id')});
			var loreshow = Ext.create('yi18app.view.lore.LoreShow',{title:"综合信息正文",store:store});
    
        	this.getLore().push(loreshow);
         
        
    }
    ,
      onMedicineListSelect: function(list, index, node, record) {
     
       		
        	
          var select = this.getSegmentedbutton().getPressedButtons()[0].getText();
          if(select=="药品信息")
          {
	          var store= Ext.create('yi18app.store.DrugListStore',{autoLoad: true,storeId:record.get('id')});
	           var 	druglist=Ext.create('yi18app.view.medicine.DrugList',{title:"药品列表",store:store});
	          this.getMedicine().push(druglist);
          }
          if(select=="病状信息")
          {
	          var store= Ext.create('yi18app.store.SymptomListStore',{autoLoad: true,storeId:record.get('id')});
	           var 	symptomlist=Ext.create('yi18app.view.medicine.SymptomList',{title:"病状列表",store:store});
	          this.getMedicine().push(symptomlist);
          }
          if(select=="疾病信息")
          {
	          var store= Ext.create('yi18app.store.DiseaseListStore',{autoLoad: true,storeId:record.get('id')});
	           var 	diseaselist=Ext.create('yi18app.view.medicine.DiseaseList',{title:"疾病列表",store:store});
	          this.getMedicine().push(diseaselist);
          }
            
            	
            	
            	
         
        
    }
    
      ,
      onDrugListSelect: function(list, index, node, record) {
     
      	var store= Ext.create('yi18app.store.DrugInfoStore',{autoLoad: true,storeId:record.get('id')});
		var drugshow = Ext.create('yi18app.view.medicine.DrugShow',{title:"药品详细信息",store:store});
    
       this.getMedicine().push(drugshow);
            	
            	
            	
         
        
    } ,
      onSymptomListSelect: function(list, index, node, record) {
     
      	var store= Ext.create('yi18app.store.SymptomInfoStore',{autoLoad: true,storeId:record.get('id')});
		var symptomshow = Ext.create('yi18app.view.medicine.SymptomShow',{title:"病状详细信息",store:store});
    
       this.getMedicine().push(symptomshow);
 
    }
    ,
      onDiseaseListSelect: function(list, index, node, record) {
     
      	var store= Ext.create('yi18app.store.DiseaseInfoStore',{autoLoad: true,storeId:record.get('id')});
		var diseaseshow = Ext.create('yi18app.view.medicine.DiseaseShow',{title:"疾病详细信息",store:store});
    
       this.getMedicine().push(diseaseshow);
 
    }
    
    ,
      onSearchListSelect: function(list, index, node, record) {
     
      		var select =this.getSegmentedsearch().getPressedButtons()[0].getText();
                	
                	if(select=="药品"){
                		var store= Ext.create('yi18app.store.DrugInfoStore',{autoLoad: true,storeId:record.get('id')});
						var drugshow = Ext.create('yi18app.view.medicine.DrugShow',{title:"药品详细信息",store:store});
				    
				       this.getHome().push(drugshow);
                	}
                	else if(select=="病状"){
                			var store= Ext.create('yi18app.store.SymptomInfoStore',{autoLoad: true,storeId:record.get('id')});
							var symptomshow = Ext.create('yi18app.view.medicine.SymptomShow',{title:"病状详细信息",store:store});
   						 	this.getHome().push(symptomshow);
                		
                	}
 					else if(select=="疾病"){
 					
 							var store= Ext.create('yi18app.store.DiseaseInfoStore',{autoLoad: true,storeId:record.get('id')});
							var diseaseshow = Ext.create('yi18app.view.medicine.DiseaseShow',{title:"疾病详细信息",store:store});
    						this.getHome().push(diseaseshow);
 					}
                	else if(select=="健康"){
                		var store= Ext.create('yi18app.store.LoreInfoStore',{autoLoad: true,storeId:record.get('id')});
						var loreshow = Ext.create('yi18app.view.lore.LoreShow',{title:"健康信息正文",store:store});
    					this.getHome().push(loreshow);
                		
                	}
                	else if(select=="新闻"){
                	
                		var store= Ext.create('yi18app.store.NewsInfoStore',{autoLoad: true,storeId:record.get('id')});
					var newsshow = Ext.create('yi18app.view.news.NewsShow',{title:"综合信息正文",store:store});
   					 this.getHome().push(newsshow);
                	}
      	
 
    }
});