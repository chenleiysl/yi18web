package cn.yi18.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return LoreDao.getHot(page, size);
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
		return LoreDao.getHot(page, size,datecount);
	}
	
	
	/**
	 * 取得新闻，分页显示 按照ID排序
	 * @param page
	 * @param size
	 * @return
	 */
	public PageUtil getNews(int page,int size)
	{
		Lore bean = new Lore();
		String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue();
		List<Lore> list =(List<Lore>) bean.filter(filter, page, size) ;
		PageUtil pageUtil = new PageUtil(list , page, size, _getTotalCount());
		
		return pageUtil;
	}
	
	
	/**
	 * 返回多少条
	 * @param size
	 * @return
	 */
	public List<Lore> getNews(int size) 
	{
		Lore bean = new Lore();
		
		String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue();
		return (List<Lore>) bean.filter(filter ,1, size) ;
		
	}
	
	/**
	 * 取得新闻总数
	 * @return
	 */
	public int _getTotalCount()
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
	
	

	public List<Lore> getHot(Long id, int page, int size, int datecount) {
		// TODO Auto-generated method stub
		return LoreDao.getHot(id,page, size,datecount);
	}

	
	
	private LoreDao LoreDao = new LoreDao();



	public PageUtil getNews(Long id, int page, int size) {
		Lore bean = new Lore();
		String filter=" allow = "+LoreEnum.Check_Status.IsCheck.getValue() +" AND loreclass="+id;
		List<Lore> list =(List<Lore>) bean.filter(filter, page, size) ;
		
		
		 int total = bean.totalCount(filter);
		PageUtil pageUtil = new PageUtil(list , page, size, total);
		
		return pageUtil;
	}
}
