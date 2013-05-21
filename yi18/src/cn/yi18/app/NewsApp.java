package cn.yi18.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

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
		List<News> list = (List<News>) page.getList();
		Gson gson = new Gson();
		gson.toJson(list);
		
		printJson(gson.toString());
		 
		 
		
		
		
	}
	
	private NewsService newsService = new NewsService();
	
	
}
