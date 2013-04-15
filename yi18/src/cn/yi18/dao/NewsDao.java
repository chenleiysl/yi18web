package cn.yi18.dao;

import java.util.List;


import cn.yi18.enums.NewsEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.News;

public class NewsDao 
{
	/**
	 * 按照热门排序
	 * @param page
	 * @param size
	 * @param date 最近多少天
	 * @return
	 */
	public List<News> getHot(int page,int size,int date)
	{
		String sql ="SELECT * FROM yi18_news WHERE DATE_SUB(CURDATE(),INTERVAL ? DAY) <=time AND "+" allow = "+NewsEnum.Check_Status.IsCheck.getValue()+" ORDER BY count DESC ";
		
		return QueryHelper.query_slice(News.class,sql, page,size, date);
	}
	public List<News> getHot(int page,int size)
	{
		String sql ="SELECT * FROM yi18_news AND "+" allow = "+NewsEnum.Check_Status.IsCheck.getValue()+" ORDER BY count DESC ";
		
		return QueryHelper.query_slice(News.class,sql, page,size);
	}
	
	
	
	
	
	
}
