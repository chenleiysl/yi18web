package cn.yi18.app;


import java.util.ArrayList;
import java.util.List;






import com.google.gson.Gson;

import cn.yi18.app.entity.AskApp;
import cn.yi18.cache.VisitLogEhCache;
import cn.yi18.pojo.News;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;

/**
 * 综合信息对应的接口
 * 列表
 * 显示
 * @author 陈磊
 *
 */
public class NewsApp extends BaseApp
{

	
	
	/**
	 * 显示综合信息列表
	 * 主要参数 
	 * page： 当前页面
	 * limit：每页显示数据 
	 */
	public void list() 
	{
		AskApp ask = getAskApp();
		NewsService newsService = new NewsService();
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
			news.setCount(pagenews.getCount()+"");
			list.add(news);
		}
		
		
		Gson gson = new Gson();
		String json=gson.toJson(list);
		
		
		printJson(this.toJsonP(ask.getCallback(), json, total));

		
	}
	
	
	/**
	 * 用于显示综合新闻的详情信息
	 * 主要参数  id
	 */
	public void show() 
	{
		AskApp ask = getAskApp();
		
		News newsj= new News(); 
		newsj=newsj.get(ask.getId());
		if(newsj==null){
			run_false();
			return;
		}//如果不存在就返回404页面
	
		VisitLogEhCache.Add(newsj.getId(), "yi18_news");//更新阅读数
		cn.yi18.app.entity.News news = new cn.yi18.app.entity.News();
		news.setId(newsj.getId()+"");
		news.setTime(newsj.getTime()+"");
		news.setTitle(newsj.getTitle());
		news.setAuthor(newsj.getAuthor());
		news.setMessage(newsj.getMessage());
		news.setCount(newsj.getCount()+"");
		Gson gson = new Gson();
		String json=gson.toJson(news);
		printJson(this.toJsonP(ask.getCallback(), json));

		
	}
	
	
	
	
	
	
	
}
