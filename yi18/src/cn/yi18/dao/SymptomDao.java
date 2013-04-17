package cn.yi18.dao;

import java.util.List;

import cn.yi18.enums.DrugEnum;
import cn.yi18.enums.SymptomEnum;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.pojo.Drug;
import cn.yi18.pojo.Symptoms;

public class SymptomDao 
{
	
	
	
	/**
	 * 取得热门药品
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Symptoms> getHot(int page ,int size ) 
	{
		String sql ="SELECT * FROM yi18_symptoms WHERE allow = ?  ORDER BY count DESC";
		
		return QueryHelper.query_slice(Symptoms.class,sql, page,size, SymptomEnum.Check_Status.IsCheck.getValue());
	}

	public List<Symptoms> getHot(int page, int size, long id) {
			String sql ="SELECT * FROM yi18_symptoms WHERE allow = ? AND symptomsclass=? ORDER BY count DESC";
		
		return QueryHelper.query_slice(Symptoms.class,sql, page,size, SymptomEnum.Check_Status.IsCheck.getValue(),id);
	}
	
	

}
