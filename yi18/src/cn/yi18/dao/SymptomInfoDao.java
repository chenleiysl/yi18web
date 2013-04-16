



package cn.yi18.dao;

import java.util.List;


import cn.yi18.entity.SymptomInfo;
import cn.yi18.jdbc.QueryHelper;

public class SymptomInfoDao 
{
	public List<SymptomInfo> getSymptomInfo(long id)
	{
		
		String sql = "SELECT directory.title,symptominfo.message,symptominfo .id FROM "+
				"yi18_directory directory JOIN yi18_symptominfo symptominfo  "+
				"ON symptominfo .directory =directory.id "+
				"WHERE symptominfo.symptoms=?";
		List<SymptomInfo> list = QueryHelper.query(SymptomInfo.class, sql, id);
		return list;
		
	}
	
	public static void main(String[] args) {
		
	}

	
}