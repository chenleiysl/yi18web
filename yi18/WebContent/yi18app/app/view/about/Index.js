

/**
 * 关于
 */
Ext.define('yi18app.view.about.Index', 
{
	extend: 'Ext.Panel',
	xtype: 'about',
	
    config: {
        
        items:[
        	{
              docked: 'top',
              xtype: 'titlebar',
              title: '医药吧开发者',
              items:[
               {
		            iconCls: 'user',
		            align: 'right'
		        }
              ]
              
          },
          { 
          	html: '<br>'
          },
           { 
          	html: '<b>医药吧：</b><br>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是由个人工作室2013年3月所创建，主要是一个医药信息的综合信息平台。医药吧网站的目标就是以医药数据的中心的一个数据服务网站，同时开放数据提供接口和移动终端服务。医药吧开发者是一个记录医药吧网站的开发技术平台和信息分享平台，医药吧开发者是项目开发的记录平台和文档提供平台（医药吧的博客社区）。<br><b>成员联系信息：</b><p>电话（成都）13880334484（陈先生）<p>邮箱: mail.yi18.cn#gmail.com <p>QQ：397713572    <p>新浪博客：@医药吧'
          }
        ]
        }
	
});