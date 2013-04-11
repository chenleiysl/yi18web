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
	
	public int  getTotal() 
	{
		
		return 0;
		
	}
	
	/**
	 * 取得热门药品
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Drug> getHot(int page ,int size ) 
	{
		String sql ="SELECT * FROM yi18_drug WHERE allow = ?  ORDER BY count DESC";
		
		return QueryHelper.query_slice(Drug.class,sql, page,size, DrugEnum.Check_Status.IsCheck.getValue());
	}

	public List<Drug> getHot(int page, int size, long id) {
			String sql ="SELECT * FROM yi18_drug WHERE allow = ? AND drugclass=? ORDER BY count DESC";
		
		return QueryHelper.query_slice(Drug.class,sql, page,size, DrugEnum.Check_Status.IsCheck.getValue(),id);
	}
	
	

}
