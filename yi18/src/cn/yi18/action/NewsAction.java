package cn.yi18.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.yi18.pojo.News;
import cn.yi18.service.NewsService;
import cn.yi18.util.PageUtil;



/**
 * 
 * @author 陈磊，综合资讯
 *
 */
public class NewsAction extends BaseAction
{
	public void add() throws IllegalAccessException, InvocationTargetException
	{
		if (request.isSubmit()) 
		{
			News bean = new News();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			bean.save();
			
			//sendRedirect(request.basePath()+"news/list");
			root.put("message", "添加成功！等待审核！");
			printFreemarker("default/message.ftl", root);
		}
		else 
		{
			printFreemarker("default/add_news.ftl", root);
			
		}
		
	}
	
	public void list() {
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		
		List<News> week = newsService.getHot(1, 10, 7);//最近7天
		List<News> month = newsService.getHot(1, 10, 30);//最近30天
		 PageUtil news = newsService.getNews(page, 15);
		 root.put("week", week);
		 root.put("month", month);
		 root.put("page", news);
		printFreemarker("default/news_list.ftl", root);
	}
	
	public void show()
	{
		String sid= request.getParams()[0];
		News news = new News();
		news = news.get(Long.parseLong(sid));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", news.getCount()+1);
		news.update(map , news.getId()); //更新阅读次数
		
		root.put("news", news);
		printFreemarker("default/news.ftl", root);
	}
	
	private NewsService newsService = new NewsService();
	
	

}
