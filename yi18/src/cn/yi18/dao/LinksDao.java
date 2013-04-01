package cn.yi18.dao;

import java.util.List;

import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Links;

public class LinksDao 
{
	public List<Links> getAll() 
	{
		String sql ="SELECT * FROM yi18.yi18_links ORDER BY sequence ASC";
		return QueryHelper.query(Links.class, sql);
	}
}
