package cn.yi18.dao;

import java.util.List;

import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Links;
import cn.yi18.pojo.Partner;

public class PartnerDao 
{
	public List<Partner> getAll() 
	{
		String sql ="SELECT * FROM yi18.yi18_partner ORDER BY sequence ASC";
		return QueryHelper.query(Partner.class, sql);
	}
}
