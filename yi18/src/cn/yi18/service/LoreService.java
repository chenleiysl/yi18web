package cn.yi18.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yi18.cache.EhCacheEngine;
import cn.yi18.dao.LoreDao;
import cn.yi18.enums.LoreEnum;
import cn.yi18.enums.LoreEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Lore;
import cn.yi18.pojo.Lore;
import cn.yi18.pojo.POJO;
import cn.yi18.util.PageUtil;

public class LoreService
{
	/**
	 * 取得热门新闻
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Lore> getHot(int page ,int size)
	{
		String fullyQualifiedName = "Lores";
		Serializable key="hot_"+page+"and"+size;
		List<Lore> list = (List<Lore>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list=LoreDao.getHot(page, size);
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
	public List<Lore> getHot(int page ,int size,int datecount)
	{
		String fullyQualifiedName = "Lores";
		Serializable key="hot_"+page+"and"+size+"and"+datecount;
		List<Lore> list = (List<Lore>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			list=LoreDao.getHot(page, size,datecount);
			EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		//return LoreDao.getHot(page, size,datecount);
	}
	
	
	/**
	 * 取得新闻，分页显示 按照ID排序
	 * @param page
	 * @param size
	 * @return
	 */
	public PageUtil getNews(int page,int size)
	{
		String fullyQualifiedName = "Lores";
		Serializable key="pagenew_"+page+"and"+size;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Lore bean = new Lore();
			String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue();
			@SuppressWarnings("unchecked")
			List<Lore> list =(List<Lore>) bean.filter(filter, page, size) ;
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
	public List<Lore> getNews(int size) 
	{
		String fullyQualifiedName = "Lores";
		Serializable key="new_"+size;
		List<Lore> list = (List<Lore>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
			Lore bean = new Lore();
			
			String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue();
			 list=(List<Lore>) bean.filter(filter ,1, size) ;
			 EhCacheEngine.add(fullyQualifiedName, key, list);
		}
		return list;
		
	}
	
	
	
	

	public List<Lore> getHot(Long id, int page, int size, int datecount) {
		// TODO Auto-generated method stub
		String fullyQualifiedName = "Lores";
		Serializable key="hot_"+page+"and"+size+"and"+datecount+"and"+id;
		List<Lore> list = (List<Lore>) EhCacheEngine.get(fullyQualifiedName, key);
		if(list==null)
		{
		  list=LoreDao.getHot(id,page, size,datecount);
		}
		return list;
	}

	
	
	


	public PageUtil getNews(Long id, int page, int size) 
	{
		String fullyQualifiedName = "Lores";
		Serializable key="pagenew_"+page+"and"+size+"and"+id;
		PageUtil pageUtil = (PageUtil) EhCacheEngine.get(fullyQualifiedName, key);
		if(pageUtil==null)
		{
			Lore bean = new Lore();
			String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue() +" AND loreclass="+id;
			List<Lore> list =(List<Lore>) bean.filter(filter, page, size) ;
	
			 int total = bean.totalCount(filter);
			 pageUtil = new PageUtil(list , page, size, total);
			 EhCacheEngine.add(fullyQualifiedName, key, pageUtil);
		}
		
		return pageUtil;
	}
	
	private LoreDao LoreDao = new LoreDao();
	/**
	 * 取得新闻总数
	 * @return
	 */
	private int _getTotalCount()
	{
		Lore bean = new Lore();
		
		String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue();
		return bean.totalCount(filter);
	}
	
	public List<Lore> getNoCheck() 
	{
		Lore bean = new Lore();
		String filter = " allow = "+LoreEnum.Check_Status.NoCheck.getValue();
		return (List<Lore>) bean.filter(filter , 1, 20);
	}

}
