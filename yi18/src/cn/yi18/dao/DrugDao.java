package cn.yi18.dao;

import java.util.List;

import cn.yi18.enums.DrugEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drug;

public class DrugDao 
{
	public List<Drug> getNoCheck()
	{
		String sql ="SELECT * FROM yi18_drug WHERE allow = ? ORDER BY id ASC LIMIT 0,20";
		return QueryHelper.query(Drug.class, sql, DrugEnum.Check_Status.NoCheck.getValue());
		
	}

}
