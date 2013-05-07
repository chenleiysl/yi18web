



package cn.yi18.dao;

import java.util.List;


import cn.yi18.entity.SymptomInfo;
import cn.yi18.jdbc.QueryHelper;

public class SymptomInfoDao 
{
	/**
	 * 取得病状信息
	 * @param id
	 * @return
	 */
	public List<SymptomInfo> getSymptomInfo(long id)
	{
		
		String sql = "SELECT directory.title,symptominfo.message,symptominfo .id FROM "+
				"yi18_directory directory JOIN yi18_symptominfo symptominfo  "+
				"ON symptominfo .directory =directory.id "+
				"WHERE symptominfo.symptoms=? ORDER BY directory.sequence ASC";
		List<SymptomInfo> list = QueryHelper.query(SymptomInfo.class, sql, id);
		return list;
		
	}
	
	public static void main(String[] args) {
		
	}

	
}