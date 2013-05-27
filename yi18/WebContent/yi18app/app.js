/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when it performs code generation tasks such as generating new
    models, controllers or views and when running "sencha app upgrade".

    Ideally changes to this file would be limited and most work would be done
    in other places (such as Controllers). If Sencha Cmd cannot merge your
    changes and its generated code, it will produce a "merge conflict" that you
    will need to resolve manually.
*/

// DO NOT DELETE - this directive is required for Sencha Cmd packages to work.
//@require @packageOverrides

//<debug>
//Ext.Loader.setPath({
//    'Ext': 'touch/src',
//    'yi18app': 'app'
//});
//</debug>

//全局变量
var Global = {
	Author : '陈磊',
	Website : 'http://yi18.cloudfoundry.com/app/',
	//	Website : 'http://localhost:8080/yi18/app/',
	Title : '医药吧'
};


Ext.application({
    name: 'yi18app',
	
 
    views: [
        'Main',
        'home.Index',
        'about.Index',
        'news.Index','news.NewsList','news.NewsShow',
        'lore.Index','lore.LoreList','lore.LoreShow',
        'medicine.Index','medicine.DrugList','medicine.MedicineList','medicine.DrugShow','medicine.SymptomList','medicine.SymptomShow','medicine.DiseaseList','medicine.DiseaseShow',
        'search.Index'
    ],
    controllers: ['Main'],
    
    models: [
    		'NewsModel',
    		'LoreClassModel','LoreModel',
    		'MedicineClassModel',
    		'DrugModel',
    		'SymptomModel',
    		'DiseaseModel',
    		'SearchModel'
    		],
    stores: [
    		'NewsListStore','NewsInfoStore',
    		'LoreClassStore','LoreListStore','LoreInfoStore',
    		'MedicineClassStore',
    		'DrugListStore','DrugInfoStore',
    		'SymptomListStore','SymptomInfoStore',
    		'DiseaseListStore','DiseaseInfoStore',
    		'SearchListStore'
    		],
    profiles: [],
    launch: function() {
        // Destroy the #appLoadingIndicator element
        Ext.fly('appLoadingIndicator').destroy();

        // Initialize the main view
     //Ext.Viewport.add(Ext.create('yi18app.view.medicine.DrugList'));
      Ext.Viewport.add(Ext.create('yi18app.view.Main'));
 
        //main.add(Ext.create('yi18app.view.news.Index',{id:'searchPie',title:'12'}));
    },

    onUpdated: function() {
        Ext.Msg.confirm(
            "Application Update",
            "This application has just successfully been updated to the latest version. Reload now?",
            function(buttonId) {
                if (buttonId === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
