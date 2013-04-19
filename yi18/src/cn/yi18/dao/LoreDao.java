package cn.yi18.dao;

import java.util.List;


import cn.yi18.enums.LoreEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Lore;

public class LoreDao 
{
	/**
	 * 按照热门排序
	 * @param page
	 * @param size
	 * @param date 最近多少天
	 * @return
	 */
	public List<Lore> getHot(int page,int size,int date)
	{
		String sql ="SELECT * FROM yi18_lore WHERE DATE_SUB(CURDATE(),INTERVAL ? DAY) <=time AND "+" allow = "+LoreEnum.Check_Status.IsCheck.getValue()+" ORDER BY count DESC ";
		
		return QueryHelper.query_slice(Lore.class,sql, page,size, date);
	}
	public List<Lore> getHot(int page,int size)
	{
		String sql ="SELECT * FROM yi18_lore  "+" allow = "+LoreEnum.Check_Status.IsCheck.getValue()+" ORDER BY count DESC ";
		
		return QueryHelper.query_slice(Lore.class,sql, page,size);
	}
	public List<Lore> getHot(Long id, int page, int size, int datecount) {
		String sql ="SELECT * FROM yi18_lore WHERE DATE_SUB(CURDATE(),INTERVAL ? DAY) <=time AND "+" allow = "+LoreEnum.Check_Status.IsCheck.getValue()+" AND loreclass=? ORDER BY count DESC ";
		
		return QueryHelper.query_slice(Lore.class,sql, page,size, datecount,id);
	}
	
	
	
	
	
	
}
