package cn.yi18.service;

import java.io.Serializable;

import java.util.List;


import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.NewsDao;
import cn.yi18.enums.NewsEnum;

import cn.yi18.pojo.News;

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
		String fullyQualifiedName = "Newses";
		Serializable key="hot_"+page+"and"+size;
		@SuppressWarnings("unchecked")
		List<News> list = (List<News>) EhCacheEngine.get(fullyQualifiedName, key);
		if (list==null) {
			list=newsDao.getHot(page, size);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
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
		String fullyQualifiedName = "Newses";
		Serializable key="hot_"+page+"and"+size+"and"+datecount;
		@SuppressWarnings("unchecked")
		List<News> list = (List<News>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list ==null)
		{
			list = newsDao.getHot(page, size,datecount);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
	}
	
	
	/**
	 * 取得新闻，分页显示 按照ID排序
	 * @param page
	 * @param size
	 * @return
	 */
	public PageUtil getNews(int page,int size)
	{
		String fullyQualifiedName = "Newses";
		Serializable key="pagenew_"+page+"and"+size;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			News bean = new News();
			String filter=" allow = "+NewsEnum.Check_Status.IsCheck.getValue();
			@SuppressWarnings("unchecked")
			List<News> list =(List<News>) bean.filter(filter, page, size) ;
			pageUtil = new PageUtil(list , page, size, _getTotalCount());
			EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		
		return pageUtil;
	}
	
	
	/**
	 * 返回多少条
	 * @param size
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> getNews(int size) 
	{
		String fullyQualifiedName = "Newses";
		Serializable key="new_"+size;
		@SuppressWarnings("unchecked")
		List<News> list = (List<News>) EhCacheEngine.get(fullyQualifiedName, key);
		{
			News bean = new News();
		
			String filter=" allow = "+NewsEnum.Check_Status.IsCheck.getValue();
			list =(List<News>) bean.filter(filter ,1, size) ;
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		
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
	@SuppressWarnings("unchecked")
	public List<News> getNoCheck() 
	{
		News bean = new News();
		String filter = " allow = "+NewsEnum.Check_Status.NoCheck.getValue();
		return (List<News>) bean.filter(filter , 1, 20);
	}
	
	

	
	private NewsDao newsDao = new NewsDao();
}
