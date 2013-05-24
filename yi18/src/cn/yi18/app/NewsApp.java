package cn.yi18.app;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import cn.yi18.app.entity.AskApp;
import cn.yi18.pojo.News;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

public class NewsApp extends BaseApp
{

	
	
	
	public void list() 
	{
		AskApp ask = getAskApp();
		
		PageUtil page = newsService.getNews(ask.getPage(), ask.getLimit());
		
		int total=page.getTotal();
		@SuppressWarnings("unchecked")
		List<News> pagelist = (List<News>) page.getList();
		
		List<cn.yi18.app.entity.News> list = new ArrayList<cn.yi18.app.entity.News>();
		
		for (News pagenews : pagelist)
		{
			cn.yi18.app.entity.News news = new cn.yi18.app.entity.News();
			news.setId(pagenews.getId()+"");
			news.setTime(pagenews.getTime()+"");
			news.setTitle(pagenews.getTitle());
			news.setAuthor(pagenews.getAuthor());
			list.add(news);
		}
		
		
		Gson gson = new Gson();
		String json=gson.toJson(list);
		json=ask.getCallback()+"({\"success\": true,  \"total\":"+total+",\"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	
	public void show() 
	{
		AskApp ask = getAskApp();
		
		
		News newsj= new News(); 
		newsj=newsj.get(ask.getId());
		cn.yi18.app.entity.News news = new cn.yi18.app.entity.News();
		news.setId(newsj.getId()+"");
		news.setTime(newsj.getTime()+"");
		news.setTitle(newsj.getTitle());
		news.setAuthor(newsj.getAuthor());
		news.setMessage(newsj.getMessage());
		
		Gson gson = new Gson();
		String json=gson.toJson(news);
		json=ask.getCallback()+"({\"success\": true, \"yi18\":"+json+"})";
		printJson(json);

		
	}
	
	
	
	
	
	private NewsService newsService = new NewsService();
	
	
}
