package cn.yi18.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import cn.yi18.app.entity.AskApp;
import cn.yi18.app.entity.Lore;
import cn.yi18.pojo.Loreclass;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.LoreService;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

public class LoreApp extends BaseApp
{

	
	
	
	public void classlist() 
	{
		AskApp ask = getAskApp();
		Loreclass loreclass = new Loreclass();
		@SuppressWarnings("unchecked")
		List< Loreclass> list = (List<Loreclass>) loreclass.list();
		Gson gson = new Gson();
		String json=gson.toJson(list);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);
	}
	
	public void list() 
	{
		AskApp ask = getAskApp();
		
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
		
		json=ask.getCallback()+"({\"success\": true,  \"total\":"+total+",\"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	
	
	
	
	
	public void show() 
	{
		AskApp ask = getAskApp();
		
		
		cn.yi18.pojo.Lore lorej = new cn.yi18.pojo.Lore();
		lorej = lorej.get(ask.getId());
		Lore lore = new Lore();
		lore.setAuthor(lorej.getAuthor());
		lore.setId(lorej.getId()+"");
		lore.setTime(lorej.getTime()+"");
		lore.setCount(lorej.getCount()+"");
		lore.setTitle(lorej.getTitle());
		lore.setMessage(lorej.getMessage());
		
		Gson gson = new Gson();
		String json=gson.toJson(lore);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	

	
	private LoreService loreService = new LoreService();
	
}
