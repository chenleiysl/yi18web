Ext.define('yi18app.view.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    requires: [
        'Ext.TitleBar',
        'Ext.Video'
    ],
    config: {
        tabBarPosition: 'bottom',

        items: [
            {
                title: '主页',
                iconCls: 'home',
                
                xtype: 'home'
               

               
            },
          
            
            {
                title: '医药',
                iconCls: 'info',
                xtype:'medicine'
     
            } ,
            {
                title: '健康',
                iconCls: 'tags',
                xtype:'lore'
     
            } ,
            {
                title: '新闻',
                iconCls: 'star',
                xtype:'news'
     
            },
            {
                title: '关于我们',
                iconCls: 'help',
                xtype: 'about'
     
            }
        ]
    }
});
