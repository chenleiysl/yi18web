package cn.yi18.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.cache.VisitLogEhCache;
import cn.yi18.enums.NewsEnum;
import cn.yi18.lucene.IndexFiles;
import cn.yi18.lucene.NewsLucene;
import cn.yi18.lucene.PageInfo;
import cn.yi18.pojo.News;
import cn.yi18.service.NewsService;
import cn.yi18.util.Base64Coder;
import cn.yi18.util.JsoupUtil;
import cn.yi18.util.PageUtil;



/**
 * 
 * @author 陈磊，综合资讯
 *
 */
public class NewsAction extends BaseAction
{
	private static final Logger log= LoggerFactory.getLogger(NewsAction.class);
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
			log.debug("添加综合信息");
			printFreemarker("default/message.ftl", root);
		}
		else 
		{
			root.put("title", "添加综合新闻|医药吧  ");
			printFreemarker("default/add_news.ftl", root);
			
		}
		
	}
	
	/**
	 * 显示综合信息列表
	 */
	public void list() {
		int page= request.getParameter("p")==null?1:Integer.parseInt(request.getParameter("p"));
		// 取得页面，如果没有默认为1
		
		List<News> week = newsService.getHot(1, 10, 7);//最近7天
		List<News> month = newsService.getHot(1, 10, 30);//最近30天
		 PageUtil news = newsService.getNews(page, 15);
		 root.put("week", week);
		 root.put("month", month);
		 root.put("page", news);
		 root.put("title", "热门综合信息|综合信息_医药吧");
		printFreemarker("default/news_list.ftl", root);
	}
	
	public void show()
	{
		String sid= request.getParams()[0];
		News news = new News();
		news = news.get(Long.parseLong(sid));
		if(news==null){
			run_404();
			return;
		}//如果不存在就返回404页面
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("count", news.getCount()+1);
//		news.update(map , news.getId()); //更新阅读次数
		VisitLogEhCache.Add(news.getId(), "yi18_news");//更新阅读数
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
		Map<?,?> map = request.getParameterMap();
		BeanUtils.populate(bean, map);
		
		Map<String, Object> vmap = new HashMap<String, Object>();
		vmap.put("title", bean.getTitle());
		vmap.put("message", bean.getMessage());
		vmap.put("author", bean.getAuthor());
		vmap.put("allow", NewsEnum.Check_Status.IsCheck.getValue());
		vmap.put("time", new Timestamp(new Date().getTime()));//同时也更新时间
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
	
	
	/**
	 * 删除综合信息
	 */
	public void delete()
	{
		long id= Long.parseLong(request.getParams()[0]);
		String url =Base64Coder.decodeBase64( request.getParameter("returnUrl")); //取得返回的URL
		News news = new News();
		news.delete(id);
		EhCacheEngine.remove("Newses");//移除缓存
		
		sendRedirect(url);//
		
	}
	
	
	private NewsService newsService = new NewsService();
	
	

}
