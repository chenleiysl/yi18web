package cn.yi18.app;


import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;

import cn.yi18.app.entity.AskApp;
import cn.yi18.app.entity.Lore;
import cn.yi18.cache.VisitLogEhCache;
import cn.yi18.pojo.Loreclass;
import cn.yi18.service.LoreService;
import cn.yi18.util.PageUtil;

/**
 * 健康知识信息
 * 主要接口
 * 分类信息 classlist
 * 信息列表 list
 * 显示信息 show
 * @author 陈磊
 *
 */
public class LoreApp extends BaseApp
{

	
	
	
	/**
	 * 显示健康知识分类
	 * 无参数
	 */
	public void classlist() 
	{
		AskApp ask = getAskApp();
		Loreclass loreclass = new Loreclass();
		@SuppressWarnings("unchecked")
		List< Loreclass> list = (List<Loreclass>) loreclass.list();
		Gson gson = new Gson();
		String json=gson.toJson(list);
		
		
		printJson(this.toJsonP(ask.getCallback(), json));
	}
	
	
	/**
	 * 按照分类显示健康知识
	 * 主要参数 分类的 id ：默认为0 也就不分类 
	 * 当前页数  page
	 * 显示条数 limit 
	 */
	public void list() 
	{
		AskApp ask = getAskApp();
		LoreService loreService = new LoreService();
		int total = 0;
		List<Lore> list = new ArrayList<Lore>();
		if(ask.getId()==0)//默认不分类
		{
			PageUtil page = loreService.getNews(ask.getPage(), ask.getLimit());
			total=page.getTotal();
			@SuppressWarnings("unchecked")
			List<cn.yi18.pojo.Lore> pagelist = (List<cn.yi18.pojo.Lore>) page.getList();
			for (cn.yi18.pojo.Lore pagelore : pagelist) 
			{
				Lore lore = new Lore();
				lore.setAuthor(pagelore.getAuthor());
				lore.setId(pagelore.getId()+"");
				lore.setTime(pagelore.getTitle()+"");
				lore.setCount(pagelore.getCount()+"");
				lore.setTitle(pagelore.getTitle());
				
				list.add(lore);
			}
			
		}else
		{
			PageUtil page = loreService.getNews(ask.getId(),ask.getPage(), ask.getLimit());
			total=page.getTotal();
			@SuppressWarnings("unchecked")
			List<cn.yi18.pojo.Lore> pagelist = (List<cn.yi18.pojo.Lore>) page.getList();
			for (cn.yi18.pojo.Lore pagelore : pagelist) 
			{
				Lore lore = new Lore();
				lore.setAuthor(pagelore.getAuthor());
				lore.setId(pagelore.getId()+"");
				lore.setTime(pagelore.getTime()+"");
				lore.setCount(pagelore.getCount()+"");
				lore.setTitle(pagelore.getTitle());
				
				list.add(lore);
			}
			
		}

		Gson gson = new Gson();
		
		String json=gson.toJson(list);
		printJson(this.toJsonP(ask.getCallback(), json,total));

		
	}
	
	
	
	
	
	/**
	 * 显示健康知识
	 * 参数 id 
	 */
	public void show() 
	{
		AskApp ask = getAskApp();
		
		
		cn.yi18.pojo.Lore lorej = new cn.yi18.pojo.Lore();
		lorej = lorej.get(ask.getId());
		if(lorej==null){
			run_false();
			return;
		}//如果不存在就返回404页面
	
		VisitLogEhCache.Add(lorej .getId(), "yi18_lore");//更新阅读数
		Lore lore = new Lore();
		lore.setAuthor(lorej.getAuthor());
		lore.setId(lorej.getId()+"");
		lore.setTime(lorej.getTime()+"");
		lore.setCount(lorej.getCount()+"");
		lore.setTitle(lorej.getTitle());
		lore.setMessage(lorej.getMessage());
		
		Gson gson = new Gson();
		String json=gson.toJson(lore);
		printJson(this.toJsonP(ask.getCallback(), json));
		

		
	}
	

	
}
