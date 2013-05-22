package cn.yi18.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import cn.yi18.app.entity.AskApp;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

public class NewsApp extends BaseApp
{

	
	@Override
	public void execute() throws ServletException, IOException {
		
		String json = "{\"id\":\"1\",\"title\":\"医药吧简介\"}";
		printJson(json);
	}
	
	public void list() throws IllegalAccessException, InvocationTargetException
	{
		Map<?, ?> map = request.getParameterMap();
		AskApp bean = new AskApp();
		BeanUtils.populate(bean, map);
		PageUtil page = newsService.getNews(bean.getPage(), bean.getLimit());
		
		int total=page.getTotal();
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
		json=bean.getCallback()+"({\"success\": true,  \"total\":"+total+",\"news\":"+json+"})";
		printJson(json);

		
	}
	
	
	public void show() throws IllegalAccessException, InvocationTargetException
	{
		Map<?, ?> map = request.getParameterMap();
		AskApp bean = new AskApp();
		BeanUtils.populate(bean, map);
		
		
		News newsj= new News(); 
		newsj=newsj.get(bean.getId());
		cn.yi18.app.entity.News news = new cn.yi18.app.entity.News();
		news.setId(newsj.getId()+"");
		news.setTime(newsj.getTime()+"");
		news.setTitle(newsj.getTitle());
		news.setAuthor(newsj.getAuthor());
		news.setMessage(newsj.getMessage());
		
		Gson gson = new Gson();
		String json=gson.toJson(news);
		json=bean.getCallback()+"({\"success\": true, \"news\":"+json+"})";
		printJson(json);

		
	}
	
	
	
	
	
	private NewsService newsService = new NewsService();
	
	
}
