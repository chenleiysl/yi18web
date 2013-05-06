package cn.yi18.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.yi18.enums.NewsEnum;
import cn.yi18.lucene.IndexFiles;
import cn.yi18.lucene.NewsLucene;
import cn.yi18.lucene.PageInfo;
import cn.yi18.pojo.News;
import cn.yi18.service.NewsService;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;



/**
 * 
 * @author 陈磊，综合资讯
 *
 */
public class NewsAction extends BaseAction
{
	
	/*
	 * 添加新闻，综合资讯
	 */
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
			root.put("title", "添加综合新闻|医药吧  ");
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
		root.put("title", news.getTitle()+"|综合信息_医药吧");
		root.put("keywords", news.getTitle());
		root.put("description",news.subMessage(80));
		printFreemarker("default/news.ftl", root);
		
	}
	
	/**
	 * 更新综合信息
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void update() throws IllegalAccessException, InvocationTargetException {
		News bean = new News();
		Map map = request.getParameterMap();
		BeanUtils.populate(bean, map);
		
		Map<String, Object> vmap = new HashMap<String, Object>();
		vmap.put("title", bean.getTitle());
		vmap.put("message", bean.getMessage());
		vmap.put("author", bean.getAuthor());
		vmap.put("allow", NewsEnum.Check_Status.IsCheck.getValue());
		bean.update(vmap , bean.getId());
		
		IndexFiles indexFiles = new NewsLucene();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTitle( bean.getTitle());
		pageInfo.setId(bean.getId());
		pageInfo.setUrl("news/show/"+bean.getId());
		String content = JsoupUtil.Text(bean.getMessage());
		pageInfo.setContent(content);
		indexFiles.create(pageInfo );
		
		sendRedirect(request.basePath()+"admin/news");
	}
	private NewsService newsService = new NewsService();
	
	

}
