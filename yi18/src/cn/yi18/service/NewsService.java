package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.dao.NewsDao;
import cn.yi18.enums.NewsEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.News;
import cn.yi18.pojo.POJO;
import cn.yi18.util.PageUtil;

public class NewsService
{
	/**
	 * 取得热门新闻
	 * @param page
	 * @param size
	 * @return
	 */
	public List<News> getHot(int page ,int size)
	{
		return newsDao.getHot(page, size);
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param datecount 最近多少天
	 * @return
	 */
	public List<News> getHot(int page ,int size,int datecount)
	{
		return newsDao.getHot(page, size,datecount);
	}
	
	
	/**
	 * 取得新闻，分页显示 按照ID排序
	 * @param page
	 * @param size
	 * @return
	 */
	public PageUtil getNews(int page,int size)
	{
		News bean = new News();
		String filter=" allow = "+NewsEnum.Check_Status.IsCheck.getValue();
		List<News> list =(List<News>) bean.filter(filter, page, size) ;
		PageUtil pageUtil = new PageUtil(list , page, size, _getTotalCount());
		
		return pageUtil;
	}
	
	
	/**
	 * 返回多少条
	 * @param size
	 * @return
	 */
	public List<News> getNews(int size) 
	{
		News bean = new News();
		
		String filter=" allow = "+NewsEnum.Check_Status.IsCheck.getValue();
		return (List<News>) bean.filter(filter ,1, size) ;
		
	}
	
	/**
	 * 取得新闻总数
	 * @return
	 */
	public int _getTotalCount()
	{
		News bean = new News();
		
		String filter=" allow = "+NewsEnum.Check_Status.IsCheck.getValue();
		return bean.totalCount(filter);
	}
	
	/**
	 * 取得默认新闻投递，默认是20条数据
	 * @return
	 */
	public List<News> getNoCheck() 
	{
		News bean = new News();
		String filter = " allow = "+NewsEnum.Check_Status.NoCheck.getValue();
		return (List<News>) bean.filter(filter , 1, 20);
	}
	
	private NewsDao newsDao = new NewsDao();
}
